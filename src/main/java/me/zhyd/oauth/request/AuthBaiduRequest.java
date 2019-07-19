package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.*;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 百度账号登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthBaiduRequest extends AuthDefaultRequest {

    public AuthBaiduRequest(AuthConfig config) {
        super(config, AuthSource.BAIDU);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doPostAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        this.checkResponse(accessTokenObject);
        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
            .scope(accessTokenObject.getString("scope"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = doGetUserInfo(authToken);
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        this.checkResponse(object);
        return AuthUser.builder()
            .uuid(object.getString("userid"))
            .username(object.getString("username"))
            .nickname(object.getString("username"))
            .avatar(object.getString("portrait"))
            .remark(object.getString("userdetail"))
            .gender(AuthUserGender.getRealGender(object.getString("sex")))
            .token(authToken)
            .source(AuthSource.BAIDU)
            .build();
    }

    @Override
    public AuthResponse revoke(AuthToken authToken) {
        HttpResponse response = doGetRevoke(authToken);
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.containsKey("error_code")) {
            return AuthResponse.builder()
                .code(AuthResponseStatus.FAILURE.getCode())
                .msg(object.getString("error_msg"))
                .build();
        }
        // 返回1表示取消授权成功，否则失败
        AuthResponseStatus status = object.getIntValue("result") == 1 ? AuthResponseStatus.SUCCESS : AuthResponseStatus.FAILURE;
        return AuthResponse.builder().code(status.getCode()).msg(status.getMsg()).build();
    }

    @Override
    public AuthResponse refresh(AuthToken authToken) {
        String refreshUrl = UrlBuilder.fromBaseUrl(this.source.refresh())
            .queryParam("grant_type", "refresh_token")
            .queryParam("refresh_token", authToken.getRefreshToken())
            .queryParam("client_id", this.config.getClientId())
            .queryParam("client_secret", this.config.getClientSecret())
            .build();
        HttpResponse response = HttpRequest.get(refreshUrl).execute();
        JSONObject object = JSONObject.parseObject(response.body());
        this.checkResponse(object);
        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .refreshToken(object.getString("refresh_token"))
                .scope(object.getString("scope"))
                .expireIn(object.getIntValue("expires_in"))
                .build())
            .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("response_type", "code")
            .queryParam("client_id", config.getClientId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("display", "popup")
            .queryParam("state", getRealState(config.getState()))
            .build();
    }

    private void checkResponse(JSONObject object) {
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }
    }
}
