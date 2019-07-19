package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.*;
import me.zhyd.oauth.utils.AuthChecker;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 默认的request处理类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @author yangkai.shen (https://xkcoding.com)
 * @version 1.0
 * @since 1.8
 */
@Slf4j
public abstract class AuthDefaultRequest implements AuthRequest {
    protected AuthConfig config;
    protected AuthSource source;

    public AuthDefaultRequest(AuthConfig config, AuthSource source) {
        this.config = config;
        this.source = source;
        if (!AuthChecker.isSupportedAuth(config, source)) {
            throw new AuthException(AuthResponseStatus.PARAMETER_INCOMPLETE);
        }
        // 校验配置合法性
        AuthChecker.checkConfig(config, source);
    }

    protected abstract AuthToken getAccessToken(AuthCallback authCallback);

    protected abstract AuthUser getUserInfo(AuthToken authToken);

    @Override
    public AuthResponse login(AuthCallback authCallback) {
        try {
            AuthChecker.checkCode(source == AuthSource.ALIPAY ? authCallback.getAuth_code() : authCallback.getCode());
            AuthChecker.checkState(authCallback.getState(), config.getState());

            AuthToken authToken = this.getAccessToken(authCallback);
            AuthUser user = this.getUserInfo(authToken);
            return AuthResponse.builder().code(AuthResponseStatus.SUCCESS.getCode()).data(user).build();
        } catch (Exception e) {
            log.error("Failed to login with oauth authorization.", e);
            return this.responseError(e);
        }
    }

    private AuthResponse responseError(Exception e) {
        int errorCode = AuthResponseStatus.FAILURE.getCode();
        if (e instanceof AuthException) {
            errorCode = ((AuthException) e).getErrorCode();
        }
        return AuthResponse.builder().code(errorCode).msg(e.getMessage()).build();
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
            .queryParam("state", getRealState(config.getState()))
            .build();
    }

    /**
     * 返回获取accessToken的url
     *
     * @return 返回获取accessToken的url
     */
    protected String accessTokenUrl(String code) {
        return UrlBuilder.fromBaseUrl(source.accessToken())
            .queryParam("code", code)
            .queryParam("client_id", config.getClientId())
            .queryParam("client_secret", config.getClientSecret())
            .queryParam("grant_type", "authorization_code")
            .queryParam("redirect_uri", config.getRedirectUri())
            .build();
    }

    /**
     * 返回获取accessToken的url
     *
     * @return 返回获取accessToken的url
     */
    protected String refreshTokenUrl(String refreshToken) {
        return UrlBuilder.fromBaseUrl(source.refresh())
            .queryParam("client_id", config.getClientId())
            .queryParam("client_secret", config.getClientSecret())
            .queryParam("refresh_token", refreshToken)
            .queryParam("grant_type", "refresh_token")
            .queryParam("redirect_uri", config.getRedirectUri())
            .build();
    }

    /**
     * 返回获取userInfo的url
     *
     * @return 返回获取userInfo的url
     */
    protected String userInfoUrl(AuthToken authToken) {
        return UrlBuilder.fromBaseUrl(source.userInfo()).queryParam("access_token", authToken.getAccessToken()).build();
    }

    /**
     * 返回获取revoke authorization的url
     *
     * @return 返回获取revoke authorization的url
     */
    protected String revokeUrl(AuthToken authToken) {
        return UrlBuilder.fromBaseUrl(source.revoke()).queryParam("access_token", authToken.getAccessToken()).build();
    }

    /**
     * 获取state，如果为空， 则默认去当前日期的时间戳
     *
     * @param state 原始的state
     * @return 返回不为null的state
     */
    protected String getRealState(String state) {
        return StringUtils.isEmpty(state) ? String.valueOf(System.currentTimeMillis()) : state;
    }

    /**
     * 通用的 authorizationCode 协议
     *
     * @param code code码
     * @return HttpResponse
     */
    protected HttpResponse doPostAuthorizationCode(String code) {
        return HttpRequest.post(accessTokenUrl(code)).execute();
    }

    /**
     * 通用的 authorizationCode 协议
     *
     * @param code code码
     * @return HttpResponse
     */
    protected HttpResponse doGetAuthorizationCode(String code) {
        return HttpRequest.get(accessTokenUrl(code)).execute();
    }

    /**
     * 通用的 用户信息
     *
     * @param authToken token封装
     * @return HttpResponse
     */
    protected HttpResponse doPostUserInfo(AuthToken authToken) {
        return HttpRequest.post(userInfoUrl(authToken)).execute();
    }

    /**
     * 通用的 用户信息
     *
     * @param authToken token封装
     * @return HttpResponse
     */
    protected HttpResponse doGetUserInfo(AuthToken authToken) {
        return HttpRequest.get(userInfoUrl(authToken)).execute();
    }

    /**
     * 通用的post形式的取消授权方法
     *
     * @param authToken token封装
     * @return HttpResponse
     */
    protected HttpResponse doPostRevoke(AuthToken authToken) {
        return HttpRequest.post(revokeUrl(authToken)).execute();
    }

    /**
     * 通用的post形式的取消授权方法
     *
     * @param authToken token封装
     * @return HttpResponse
     */
    protected HttpResponse doGetRevoke(AuthToken authToken) {
        return HttpRequest.get(revokeUrl(authToken)).execute();
    }
}
