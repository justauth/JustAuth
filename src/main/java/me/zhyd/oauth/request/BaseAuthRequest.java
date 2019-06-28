package me.zhyd.oauth.request;

import lombok.Data;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthChecker;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Data
public abstract class BaseAuthRequest implements AuthRequest {
    protected AuthConfig config;
    protected AuthSource source;

    public BaseAuthRequest(AuthConfig config, AuthSource source) {
        this.config = config;
        this.source = source;
        if (!AuthChecker.isSupportedAuth(config, source)) {
            throw new AuthException(ResponseStatus.PARAMETER_INCOMPLETE);
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
            return AuthResponse.builder().code(ResponseStatus.SUCCESS.getCode()).data(user).build();
        } catch (Exception e) {
            return this.responseError(e);
        }
    }

    private AuthResponse responseError(Exception e) {
        int errorCode = ResponseStatus.FAILURE.getCode();
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
    public abstract String authorize();
}
