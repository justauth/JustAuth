package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.utils.HttpUtils;
import com.xkcoding.http.constants.Constants;
import com.xkcoding.http.support.HttpHeader;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.Base64Utils;
import me.zhyd.oauth.utils.GlobalAuthUtils;
import me.zhyd.oauth.utils.UrlBuilder;
import me.zhyd.oauth.utils.UuidUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 饿了么
 * <p>
 * 注：集成的是正式环境，非沙箱环境
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.12.0
 */
public class AuthElemeRequest extends AuthDefaultRequest {

    private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=UTF-8";
    private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    public AuthElemeRequest(AuthConfig config) {
        super(config, AuthDefaultSource.ELEME);
    }

    public AuthElemeRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.ELEME, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        Map<String, String> form = new HashMap<>(7);
        form.put("client_id", config.getClientId());
        form.put("redirect_uri", config.getRedirectUri());
        form.put("code", authCallback.getCode());
        form.put("grant_type", "authorization_code");

        HttpHeader httpHeader = this.buildHeader(CONTENT_TYPE_FORM, this.getRequestId(), true);
        String response = new HttpUtils(config.getHttpConfig()).post(source.accessToken(), form, httpHeader, false);
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .refreshToken(object.getString("refresh_token"))
            .tokenType(object.getString("token_type"))
            .expireIn(object.getIntValue("expires_in"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        Map<String, Object> parameters = new HashMap<>(4);
        // 获取商户账号信息的API接口名称
        String action = "eleme.user.getUser";
        // 时间戳，单位秒。API服务端允许客户端请求最大时间误差为正负5分钟。
        final long timestamp = System.currentTimeMillis();
        // 公共参数
        Map<String, Object> metasHashMap = new HashMap<>(4);
        metasHashMap.put("app_key", config.getClientId());
        metasHashMap.put("timestamp", timestamp);
        String signature = GlobalAuthUtils.generateElemeSignature(config.getClientId(), config.getClientSecret(), timestamp, action, authToken
            .getAccessToken(), parameters);

        String requestId = this.getRequestId();

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("nop", "1.0.0");
        paramsMap.put("id", requestId);
        paramsMap.put("action", action);
        paramsMap.put("token", authToken.getAccessToken());
        paramsMap.put("metas", metasHashMap);
        paramsMap.put("params", parameters);
        paramsMap.put("signature", signature);

        HttpHeader httpHeader = this.buildHeader(CONTENT_TYPE_JSON, requestId, false);
        String response = new HttpUtils(config.getHttpConfig()).post(source.userInfo(), JSONObject.toJSONString(paramsMap), httpHeader);

        JSONObject object = JSONObject.parseObject(response);

        // 校验请求
        if (object.containsKey("name")) {
            throw new AuthException(object.getString("message"));
        }
        if (object.containsKey("error") && null != object.get("error")) {
            throw new AuthException(object.getJSONObject("error").getString("message"));
        }

        JSONObject result = object.getJSONObject("result");

        return AuthUser.builder()
            .rawUserInfo(result)
            .uuid(result.getString("userId"))
            .username(result.getString("userName"))
            .nickname(result.getString("userName"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(source.toString())
            .build();
    }

    @Override
    public AuthResponse refresh(AuthToken oldToken) {
        Map<String, String> form = new HashMap<>(4);
        form.put("refresh_token", oldToken.getRefreshToken());
        form.put("grant_type", "refresh_token");

        HttpHeader httpHeader = this.buildHeader(CONTENT_TYPE_FORM, this.getRequestId(), true);
        String response = new HttpUtils(config.getHttpConfig()).post(source.refresh(), form, httpHeader, false);

        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .refreshToken(object.getString("refresh_token"))
                .tokenType(object.getString("token_type"))
                .expireIn(object.getIntValue("expires_in"))
                .build())
            .build();
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state)).queryParam("scope", "all").build();
    }

    private String getBasic(String appKey, String appSecret) {
        StringBuilder sb = new StringBuilder();
        String encodeToString = Base64Utils.encode((appKey + ":" + appSecret).getBytes());
        sb.append("Basic").append(" ").append(encodeToString);
        return sb.toString();
    }

    private HttpHeader buildHeader(String contentType, String requestId, boolean auth) {
        HttpHeader httpHeader = new HttpHeader();
        httpHeader.add("Accept", "text/xml,text/javascript,text/html");
        httpHeader.add(Constants.CONTENT_TYPE, contentType);
        httpHeader.add("Accept-Encoding", "gzip");
        httpHeader.add("User-Agent", "eleme-openapi-java-sdk");
        httpHeader.add("x-eleme-requestid", requestId);
        if (auth) {
            httpHeader.add("Authorization", this.getBasic(config.getClientId(), config.getClientSecret()));
        }
        return httpHeader;
    }

    private String getRequestId() {
        return (UuidUtils.getUUID() + "|" + System.currentTimeMillis()).toUpperCase();
    }

    private void checkResponse(JSONObject object) {
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }
    }

}
