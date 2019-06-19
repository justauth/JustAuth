package me.zhyd.oauth.request;

import lombok.Data;
import me.zhyd.oauth.authorization.AuthorizationFactory;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthConfigChecker;

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
        if (!AuthConfigChecker.isSupportedAuth(config, source)) {
            throw new AuthException(ResponseStatus.PARAMETER_INCOMPLETE);
        }
        // 校验配置合法性
        AuthConfigChecker.check(config, source);
    }

    protected abstract AuthToken getAccessToken(String code);

    protected abstract AuthUser getUserInfo(AuthToken authToken);

    @Override
    public AuthResponse login(String code) {
        try {
            AuthToken authToken = this.getAccessToken(code);
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

    @Override
    public String authorize() {
        return AuthorizationFactory.getAuthorize(source).getAuthorizeUrl(config);
    }
}
