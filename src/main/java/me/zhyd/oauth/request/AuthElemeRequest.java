package me.zhyd.oauth.request;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
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
import me.zhyd.oauth.utils.GlobalAuthUtil;
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

    public AuthElemeRequest(AuthConfig config) {
        super(config, AuthDefaultSource.ELEME);
    }

    public AuthElemeRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.ELEME, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {

        HttpRequest request = HttpRequest.post(source.accessToken())
            .form("client_id", config.getClientId())
            .form("redirect_uri", config.getRedirectUri())
            .form("code", authCallback.getCode())
            .form("grant_type", "authorization_code");

        // 设置header
        this.setHeader(request);

        HttpResponse response = request.execute();
        JSONObject object = JSONObject.parseObject(response.body());

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
        Map<String, Object> parameters = new HashMap<>();
        // 获取商户账号信息的API接口名称
        String action = "eleme.user.getUser";
        // 时间戳，单位秒。API服务端允许客户端请求最大时间误差为正负5分钟。
        final long timestamp = System.currentTimeMillis();
        // 公共参数
        Map<String, Object> metasHashMap = new HashMap<>();
        metasHashMap.put("app_key", config.getClientId());
        metasHashMap.put("timestamp", timestamp);
        String signature = GlobalAuthUtil.generateElemeSignature(config.getClientId(), config.getClientSecret(), timestamp, action, authToken.getAccessToken(), parameters);

        String requestId = this.getRequestId();


        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("nop", "1.0.0");
        paramsMap.put("id", requestId);
        paramsMap.put("action", action);
        paramsMap.put("token", authToken.getAccessToken());
        paramsMap.put("metas", metasHashMap);
        paramsMap.put("params", parameters);
        paramsMap.put("signature", signature);

        HttpRequest request = HttpRequest.post(source.userInfo())
            .body(JSONObject.toJSONBytes(paramsMap));

        // 设置header
        this.setHeader(request, "application/json; charset=utf-8", requestId);

        HttpResponse response = request.execute();

        JSONObject object = JSONObject.parseObject(response.body());

        // 校验请求
        if (object.containsKey("name")) {
            throw new AuthException(object.getString("message"));
        }
        if (object.containsKey("error") && null != object.get("error")) {
            throw new AuthException(object.getJSONObject("error").getString("message"));
        }

        JSONObject result = object.getJSONObject("result");

        return AuthUser.builder()
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
        HttpRequest request = HttpRequest.post(source.refresh())
            .form("refresh_token", oldToken.getRefreshToken())
            .form("grant_type", "refresh_token");

        // 设置header
        this.setHeader(request);

        HttpResponse response = request.execute();
        JSONObject object = JSONObject.parseObject(response.body());

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
        return UrlBuilder.fromBaseUrl(super.authorize(state))
            .queryParam("scope", "all")
            .build();
    }

    private String getBasic(String appKey, String appSecret) {
        StringBuilder sb = new StringBuilder();
        String encodeToString = Base64.encode((appKey + ":" + appSecret).getBytes());
        sb.append("Basic").append(" ").append(encodeToString);
        return sb.toString();
    }

    private void setHeader(HttpRequest request) {
        setHeader(request, "application/x-www-form-urlencoded;charset=UTF-8", getRequestId());
        request.header("Authorization", this.getBasic(config.getClientId(), config.getClientSecret()));
    }

    private void setHeader(HttpRequest request, String contentType, String requestId) {
        request.header("Accept", "text/xml,text/javascript,text/html")
            .header("Content-Type", contentType)
            .header("Accept-Encoding", "gzip")
            .header("User-Agent", "eleme-openapi-java-sdk")
            .header("x-eleme-requestid", requestId);
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
