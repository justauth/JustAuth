package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.enums.AuthBaiduErrorCode;
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
        AuthBaiduErrorCode errorCode = AuthBaiduErrorCode.getErrorCode(accessTokenObject.getString("error"));
        if (AuthBaiduErrorCode.OK != errorCode) {
            throw new AuthException(errorCode.getDesc());
        }
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
        AuthBaiduErrorCode errorCode = AuthBaiduErrorCode.getErrorCode(object.getString("error"));
        if (AuthBaiduErrorCode.OK != errorCode) {
            throw new AuthException(errorCode.getDesc());
        }
        return AuthUser.builder()
            .uuid(object.getString("userid"))
            .username(object.getString("username"))
            .nickname(object.getString("username"))
            .gender(AuthUserGender.getRealGender(object.getString("sex")))
            .token(authToken)
            .source(AuthSource.BAIDU)
            .build();
    }

    @Override
    public AuthResponse revoke(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(UrlBuilder.fromBaseUrl(source.revoke())
            .queryParam("access_token", accessToken)
            .build()).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        if (object.containsKey("error_code")) {
            return AuthResponse.builder()
                .code(AuthResponseStatus.FAILURE.getCode())
                .msg(object.getString("error_msg"))
                .build();
        }
        AuthResponseStatus status = object.getIntValue("result") == 1 ? AuthResponseStatus.SUCCESS : AuthResponseStatus.FAILURE;
        return AuthResponse.builder().code(status.getCode()).msg(status.getMsg()).build();
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
}
