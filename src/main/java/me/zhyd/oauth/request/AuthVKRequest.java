package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.support.HttpHeader;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.scope.AuthVKScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * VK 登录请求
 * https://id.vk.com/about/business/go/docs/en/vkid/latest/vk-id/connection/api-integration/api-description
 */
public class AuthVKRequest extends AuthDefaultRequest {

    public AuthVKRequest(AuthConfig config) {
        super(config, AuthDefaultSource.VK);
    }

    public AuthVKRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.VK, authStateCache);
    }

    /**
     * 获取授权 URL，附带 state 参数，防止 CSRF 攻击
     *
     * @param state 用于验证授权流程的参数
     * @return 授权 URL
     */
    @Override
    public String authorize(String state) {
        String realState = getRealState(state);

        UrlBuilder builder = UrlBuilder.fromBaseUrl(super.authorize(state))
            .queryParam("scope", this.getScopes(" ", false, AuthScopeUtils.getDefaultScopes(AuthVKScope.values())));
        if (config.isPkce()) {
            String cacheKey = this.source.getName().concat(":code_verifier:").concat(realState);
            String codeVerifier = PkceUtil.generateCodeVerifier();
            String codeChallengeMethod = "S256";
            String codeChallenge = PkceUtil.generateCodeChallenge(codeChallengeMethod, codeVerifier);
            builder.queryParam("code_challenge", codeChallenge)
                .queryParam("code_challenge_method", codeChallengeMethod);
            // 缓存 codeVerifier 十分钟
            this.authStateCache.cache(cacheKey, codeVerifier, TimeUnit.MINUTES.toMillis(10));
        }

        return builder.build();
    }

    /**
     * 获取授权后的 access token
     */
    @Override
    public AuthToken getAccessToken(AuthCallback authCallback) {
        // 使用授权码获取access_token
        String response = doPostAuthorizationCode(authCallback);
        JSONObject object = JSONObject.parseObject(response);
        // 验证响应结果
        this.checkResponse(object);

        // 返回 token
        return AuthToken.builder()
            .idToken(object.getString("id_token"))
            .accessToken(object.getString("access_token"))
            .refreshToken(object.getString("refresh_token"))
            .tokenType(object.getString("token_type"))
            .scope(object.getString("scope"))
            .deviceId(authCallback.getDevice_id())
            .userId(object.getString("user_id")).build();
    }

    /**
     * 使用授权码获取 access_token 的 POST 请求
     *
     * @return 获取的响应体
     */
    protected String doPostAuthorizationCode(AuthCallback authCallback) {
        Map<String, String> form = new HashMap<>(7);
        form.put("grant_type", "authorization_code");
        form.put("redirect_uri", config.getRedirectUri());
        form.put("client_id", config.getClientId());
        form.put("code", authCallback.getCode());
        form.put("state", authCallback.getState());
        form.put("device_id", authCallback.getDevice_id());

        if (config.isPkce()) {
            String cacheKey = this.source.getName().concat(":code_verifier:").concat(authCallback.getState());
            String codeVerifier = this.authStateCache.get(cacheKey);
            form.put("code_verifier", codeVerifier);
        }

        return new HttpUtils(config.getHttpConfig()).post(this.source.accessToken(), form, this.buildHeader(), false).getBody();
    }

    @Override
    public AuthResponse<AuthToken> refresh(AuthToken authToken) {
        Map<String, String> form = new HashMap<>(7);
        form.put("grant_type", "refresh_token");
        form.put("refresh_token", authToken.getRefreshToken());
        form.put("state", AuthStateUtils.createState());
        form.put("device_id", authToken.getDeviceId());
        form.put("client_id", config.getClientId());
        form.put("ip", "10.10.10.10");
        return AuthResponse.<AuthToken>builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(getToken(form, this.source.refresh()))
            .build();

    }

    private AuthToken getToken(Map<String, String> param, String url) {
        String response = new HttpUtils(config.getHttpConfig()).post(url, param, this.buildHeader(), false).getBody();
        JSONObject jsonObject = JSONObject.parseObject(response);
        this.checkResponse(jsonObject);
        return AuthToken.builder()
            .accessToken(jsonObject.getString("access_token"))
            .tokenType(jsonObject.getString("token_type"))
            .expireIn(jsonObject.getIntValue("expires_in"))
            .refreshToken(jsonObject.getString("refresh_token"))
            .deviceId(param.get("device_id"))
            .build();
    }

    @Override
    public AuthResponse revoke(AuthToken authToken) {
        String response = doPostRevoke(authToken);
        JSONObject object = JSONObject.parseObject(response);
        this.checkResponse(object);
        // 返回1表示取消授权成功，否则失败
        AuthResponseStatus status = object.getIntValue("response") == 1 ? AuthResponseStatus.SUCCESS : AuthResponseStatus.FAILURE;
        return AuthResponse.builder().code(status.getCode()).msg(status.getMsg()).build();
    }

    protected String doPostRevoke(AuthToken authToken) {
        Map<String, String> form = new HashMap<>(7);
        form.put("access_token", authToken.getAccessToken());
        form.put("client_id", config.getClientId());

        return new HttpUtils(config.getHttpConfig()).post(this.source.revoke(), form, this.buildHeader(), false).getBody();

    }

    /**
     * 获取用户信息
     */
    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
        String body = doGetUserInfo(authToken);
        JSONObject object = JSONObject.parseObject(body);

        // 验证响应结果
        this.checkResponse(object);

        // 提取嵌套的user对象
        JSONObject userObj = object.getJSONObject("user");

        // 提取用户信息
        return AuthUser.builder()
            .uuid(userObj.getString("user_id"))
            .username(userObj.getString("first_name"))
            .nickname(userObj.getString("first_name") + " " + userObj.getString("last_name"))
            .avatar(userObj.getString("avatar"))
            .email(userObj.getString("email"))
            .token(authToken)
            .rawUserInfo(userObj)
            .source(source.toString())
            .build();
    }


    /**
     * 获取用户信息的 POST 请求
     *
     * @param authToken access token
     * @return 获取的响应体
     */
    protected String doGetUserInfo(AuthToken authToken) {
        Map<String, String> form = new HashMap<>(7);
        form.put("access_token", authToken.getAccessToken());
        form.put("client_id", config.getClientId());
        return new HttpUtils(config.getHttpConfig()).post(this.source.userInfo(), form, this.buildHeader(), false).getBody();
    }

    private void checkResponse(JSONObject object) {
        // 如果响应包含 error，说明出现问题
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }
        // 如果响应包含 message，说明用户信息获取失败
        if (object.containsKey("message")) {
            throw new AuthException(object.getString("message"));
        }
    }

    private HttpHeader buildHeader() {
        HttpHeader httpHeader = new HttpHeader();
        httpHeader.add("Content-Type", "application/x-www-form-urlencoded");
        return httpHeader;
    }

}
