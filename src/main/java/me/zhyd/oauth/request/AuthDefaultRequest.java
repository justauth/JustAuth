package me.zhyd.oauth.request;

import lombok.Data;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.*;
import me.zhyd.oauth.url.AuthDefaultUrlBuilder;
import me.zhyd.oauth.utils.AuthChecker;

/**
 * 默认的request处理类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Data
public abstract class AuthDefaultRequest implements AuthRequest {
    protected AuthConfig config;
    protected AuthSource source;
    protected AuthDefaultUrlBuilder urlBuilder;

    public AuthDefaultRequest(AuthConfig config, AuthSource source) {
        this.config = config;
        this.source = source;
        if (!AuthChecker.isSupportedAuth(config, source)) {
            throw new AuthException(AuthResponseStatus.PARAMETER_INCOMPLETE);
        }
        // 校验配置合法性
        AuthChecker.checkConfig(config, source);
    }

    public AuthDefaultRequest(AuthConfig config, AuthSource source, AuthDefaultUrlBuilder urlBuilder) {
        this(config, source);
        this.urlBuilder = urlBuilder;
        this.urlBuilder.setAuthConfig(config);
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
        return this.urlBuilder.getAuthorizeUrl();
    }
}
