package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.enums.scope.AuthJdScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthScopeUtils;
import me.zhyd.oauth.utils.GlobalAuthUtils;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 京东登录
 *
 * @author harry.lee (harryleexyz@qq.com)
 * @since 1.15.0
 */
public class AuthJdRequest extends AuthDefaultRequest {

    public AuthJdRequest(AuthConfig config) {
        super(config, AuthDefaultSource.JD);
    }

    public AuthJdRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.JD, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {

        Map<String, String> params = new HashMap<>(7);
        params.put("app_key", config.getClientId());
        params.put("app_secret", config.getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("code", authCallback.getCode());
        String response = new HttpUtils(config.getHttpConfig()).post(source.accessToken(), params, false);
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .expireIn(object.getIntValue("expires_in"))
            .refreshToken(object.getString("refresh_token"))
            .scope(object.getString("scope"))
            .openId(object.getString("open_id"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        UrlBuilder urlBuilder = UrlBuilder.fromBaseUrl(source.userInfo())
            .queryParam("access_token", authToken.getAccessToken())
            .queryParam("app_key", config.getClientId())
            .queryParam("method", "jingdong.user.getUserInfoByOpenId")
            .queryParam("360buy_param_json", "{\"openId\":\"" + authToken.getOpenId() + "\"}")
            .queryParam("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .queryParam("v", "2.0");
        urlBuilder.queryParam("sign", GlobalAuthUtils.generateJdSignature(config.getClientSecret(), urlBuilder.getReadOnlyParams()));
        String response = new HttpUtils(config.getHttpConfig()).post(urlBuilder.build(true));
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        JSONObject data = this.getUserDataJsonObject(object);

        return AuthUser.builder()
            .rawUserInfo(data)
            .uuid(authToken.getOpenId())
            .username(data.getString("nickName"))
            .nickname(data.getString("nickName"))
            .avatar(data.getString("imageUrl"))
            .gender(AuthUserGender.getRealGender(data.getString("gendar")))
            .token(authToken)
            .source(source.toString())
            .build();
    }

    /**
     * 个人用户无法申请应用
     * 暂时只能参考官网给出的返回结果解析
     *
     * @param object 请求返回结果
     * @return data JSONObject
     */
    private JSONObject getUserDataJsonObject(JSONObject object) {
        return object.getJSONObject("jingdong_user_getUserInfoByOpenId_response")
            .getJSONObject("getuserinfobyappidandopenid_result")
            .getJSONObject("data");
    }

    @Override
    public AuthResponse refresh(AuthToken oldToken) {
        Map<String, String> params = new HashMap<>(7);
        params.put("app_key", config.getClientId());
        params.put("app_secret", config.getClientSecret());
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", oldToken.getRefreshToken());
        String response = new HttpUtils(config.getHttpConfig()).post(source.refresh(), params, false);
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .expireIn(object.getIntValue("expires_in"))
                .refreshToken(object.getString("refresh_token"))
                .scope(object.getString("scope"))
                .openId(object.getString("open_id"))
                .build())
            .build();
    }

    private void checkResponse(JSONObject object) {
        if (object.containsKey("error_response")) {
            throw new AuthException(object.getJSONObject("error_response").getString("zh_desc"));
        }
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("app_key", config.getClientId())
            .queryParam("response_type", "code")
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("scope", this.getScopes(" ", true, AuthScopeUtils.getDefaultScopes(AuthJdScope.values())))
            .queryParam("state", getRealState(state))
            .build();
    }

}
