package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.UrlBuilder;

import static me.zhyd.oauth.enums.AuthResponseStatus.SUCCESS;

/**
 * 华为授权登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.10.0
 */
public class AuthHuaweiRequest extends AuthDefaultRequest {

    public AuthHuaweiRequest(AuthConfig config) {
        super(config, AuthDefaultSource.HUAWEI);
    }

    public AuthHuaweiRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.HUAWEI, authStateCache);
    }

    /**
     * 获取access token
     *
     * @param authCallback 授权成功后的回调参数
     * @return token
     * @see AuthDefaultRequest#authorize()
     * @see AuthDefaultRequest#authorize(String)
     */
    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpRequest request = HttpRequest.post(source.accessToken())
            .form("grant_type", "authorization_code")
            .form("code", authCallback.getAuthorization_code())
            .form("client_id", config.getClientId())
            .form("client_secret", config.getClientSecret())
            .form("redirect_uri", config.getRedirectUri());
        return getAuthToken(request);
    }

    /**
     * 使用token换取用户信息
     *
     * @param authToken token信息
     * @return 用户信息
     * @see AuthDefaultRequest#getAccessToken(AuthCallback)
     */
    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = HttpRequest.post(source.userInfo())
            .form("nsp_ts", System.currentTimeMillis())
            .form("access_token", authToken.getAccessToken())
            .form("nsp_fmt", "JS")
            .form("nsp_svc", "OpenUP.User.getInfo")
            .execute();
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        AuthUserGender gender = getRealGender(object);

        return AuthUser.builder()
            .uuid(object.getString("userID"))
            .username(object.getString("userName"))
            .nickname(object.getString("userName"))
            .gender(gender)
            .avatar(object.getString("headPictureURL"))
            .token(authToken)
            .source(source.toString())
            .build();
    }

    /**
     * 刷新access token （续期）
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    @Override
    public AuthResponse refresh(AuthToken authToken) {
        HttpRequest request = HttpRequest.post(source.refresh())
            .form("client_id", config.getClientId())
            .form("client_secret", config.getClientSecret())
            .form("refresh_token", authToken.getRefreshToken())
            .form("grant_type", "refresh_token");
        return AuthResponse.builder()
            .code(SUCCESS.getCode())
            .data(getAuthToken(request))
            .build();
    }

    private AuthToken getAuthToken(HttpRequest request) {
        HttpResponse response = request.execute();
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .expireIn(object.getIntValue("expires_in"))
            .refreshToken(object.getString("refresh_token"))
            .build();
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     * @since 1.9.3
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("response_type", "code")
            .queryParam("client_id", config.getClientId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("access_type", "offline")
            .queryParam("scope", "https%3A%2F%2Fwww.huawei.com%2Fauth%2Faccount%2Fbase.profile")
            .queryParam("state", getRealState(state))
            .build();
    }

    /**
     * 返回获取accessToken的url
     *
     * @param code 授权码
     * @return 返回获取accessToken的url
     */
    @Override
    protected String accessTokenUrl(String code) {
        return UrlBuilder.fromBaseUrl(source.accessToken())
            .queryParam("grant_type", "authorization_code")
            .queryParam("code", code)
            .queryParam("client_id", config.getClientId())
            .queryParam("client_secret", config.getClientSecret())
            .queryParam("redirect_uri", config.getRedirectUri())
            .build();
    }

    /**
     * 返回获取userInfo的url
     *
     * @param authToken token
     * @return 返回获取userInfo的url
     */
    @Override
    protected String userInfoUrl(AuthToken authToken) {
        return UrlBuilder.fromBaseUrl(source.userInfo())
            .queryParam("nsp_ts", System.currentTimeMillis())
            .queryParam("access_token", authToken.getAccessToken())
            .queryParam("nsp_fmt", "JS")
            .queryParam("nsp_svc", "OpenUP.User.getInfo")
            .build();
    }

    /**
     * 获取用户的实际性别。华为系统中，用户的性别：1表示女，0表示男
     *
     * @param object obj
     * @return AuthUserGender
     */
    private AuthUserGender getRealGender(JSONObject object) {
        int genderCodeInt = object.getIntValue("gender");
        String genderCode = genderCodeInt == 1 ? "0" : (genderCodeInt == 0) ? "1" : genderCodeInt + "";
        return AuthUserGender.getRealGender(genderCode);
    }

    /**
     * 校验响应结果
     *
     * @param object 接口返回的结果
     */
    private void checkResponse(JSONObject object) {
        if (object.containsKey("NSP_STATUS")) {
            throw new AuthException(object.getString("error"));
        }
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("sub_error") + ":" + object.getString("error_description"));
        }
    }
}
