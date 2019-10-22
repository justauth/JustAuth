package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.GlobalAuthUtil;
import me.zhyd.oauth.utils.UrlBuilder;

import java.util.HashMap;
import java.util.Map;

import static me.zhyd.oauth.config.AuthDefaultSource.TWITTER;
import static me.zhyd.oauth.utils.GlobalAuthUtil.generateTwitterSignature;
import static me.zhyd.oauth.utils.GlobalAuthUtil.urlEncode;

/**
 * Twitter登录
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @since 1.13.0
 */
public class AuthTwitterRequest extends AuthDefaultRequest {

    private static final String PREAMBLE = "OAuth";

    public AuthTwitterRequest(AuthConfig config) {
        super(config, TWITTER);
    }

    public AuthTwitterRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, TWITTER, authStateCache);
    }

    /**
     * Obtaining a request token
     * https://developer.twitter.com/en/docs/twitter-for-websites/log-in-with-twitter/guides/implementing-sign-in-with-twitter
     *
     * @return request token
     */
    public AuthToken getRequestToken() {
        String baseUrl = "https://api.twitter.com/oauth/request_token";

        Map<String, Object> oauthParams = buildOauthParams();
        oauthParams.put("oauth_callback", config.getRedirectUri());
        oauthParams.put("oauth_signature", generateTwitterSignature(oauthParams, "POST", baseUrl, config.getClientSecret(), null));
        String header = buildHeader(oauthParams);
        HttpResponse requestToken = HttpRequest.post(baseUrl).header("Authorization", header).execute();
        checkResponse(requestToken);

        Map<String, Object> res = GlobalAuthUtil.parseQueryToMap(requestToken.body());

        return AuthToken.builder()
            .oauthToken(res.get("oauth_token").toString())
            .oauthTokenSecret(res.get("oauth_token_secret").toString())
            .oauthCallbackConfirmed(Boolean.valueOf(res.get("oauth_callback_confirmed").toString()))
            .build();
    }

    /**
     * Convert request token to access token
     * https://developer.twitter.com/en/docs/twitter-for-websites/log-in-with-twitter/guides/implementing-sign-in-with-twitter
     *
     * @return access token
     */
    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        Map<String, Object> oauthParams = buildOauthParams();
        oauthParams.put("oauth_token", authCallback.getOauthToken());
        oauthParams.put("oauth_verifier", authCallback.getOauthVerifier());
        oauthParams.put("oauth_signature", generateTwitterSignature(oauthParams, "POST", source.accessToken(), config.getClientSecret(), authCallback.getOauthToken()));
        String header = buildHeader(oauthParams);
        HttpResponse response = HttpRequest.post(source.accessToken())
            .header("Authorization", header)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .form("oauth_verifier", authCallback.getOauthVerifier())
            .execute();
        checkResponse(response);

        Map<String, Object> requestToken = GlobalAuthUtil.parseQueryToMap(response.body());

        return AuthToken.builder()
            .oauthToken(requestToken.get("oauth_token").toString())
            .oauthTokenSecret(requestToken.get("oauth_token_secret").toString())
            .userId(requestToken.get("user_id").toString())
            .screenName(requestToken.get("screen_name").toString())
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("user_id", authToken.getUserId());
        queryParams.put("screen_name", authToken.getScreenName());
        queryParams.put("include_entities", true);

        Map<String, Object> oauthParams = buildOauthParams();
        oauthParams.put("oauth_token", authToken.getOauthToken());

        Map<String, Object> params = new HashMap<>(oauthParams);
        params.putAll(queryParams);
        oauthParams.put("oauth_signature", generateTwitterSignature(params, "GET", source.userInfo(), config.getClientSecret(), authToken.getOauthTokenSecret()));
        String header = buildHeader(oauthParams);
        HttpResponse response = HttpRequest.get(userInfoUrl(authToken)).header("Authorization", header).execute();
        checkResponse(response);
        JSONObject userInfo = JSONObject.parseObject(response.body());

        return AuthUser.builder()
            .uuid(userInfo.getString("id_str"))
            .username(userInfo.getString("screen_name"))
            .nickname(userInfo.getString("name"))
            .remark(userInfo.getString("description"))
            .avatar(userInfo.getString("profile_image_url_https"))
            .blog(userInfo.getString("url"))
            .location(userInfo.getString("location"))
            .source(source.toString())
            .token(authToken)
            .build();
    }

    @Override
    protected String userInfoUrl(AuthToken authToken) {
        return UrlBuilder.fromBaseUrl(source.userInfo())
            .queryParam("user_id", authToken.getUserId())
            .queryParam("screen_name", authToken.getScreenName())
            .queryParam("include_entities", true)
            .build();
    }

    private Map<String, Object> buildOauthParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("oauth_consumer_key", config.getClientId());
        params.put("oauth_nonce", GlobalAuthUtil.generateNonce(32));
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_timestamp", GlobalAuthUtil.getTimestamp());
        params.put("oauth_version", "1.0");
        return params;
    }

    private String buildHeader(Map<String, Object> oauthParams) {
        final StringBuilder sb = new StringBuilder(PREAMBLE);

        for (Map.Entry<String, Object> param : oauthParams.entrySet()) {
            if (sb.length() > PREAMBLE.length()) {
                sb.append(", ");
            }
            sb.append(param.getKey())
                .append("=\"")
                .append(urlEncode(param.getValue().toString()))
                .append('"');
        }

        return sb.toString();
    }

    private void checkResponse(HttpResponse response) {
        if (!response.isOk()) {
            throw new AuthException(response.body());
        }
    }
}
