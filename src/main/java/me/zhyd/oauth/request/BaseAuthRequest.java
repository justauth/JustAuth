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
        if (!AuthConfigChecker.isSupportedAuth(config)) {
            throw new AuthException(ResponseStatus.PARAMETER_INCOMPLETE);
        }
    }

    protected abstract AuthToken getAccessToken(String code);

    protected abstract AuthUser getUserInfo(AuthToken authToken);

    @Override
    public AuthResponse login(String code) {
        try {
            AuthUser user = this.getUserInfo(this.getAccessToken(code));
            return AuthResponse.builder().data(user).build();
        } catch (Exception e) {
            return AuthResponse.builder().code(500).msg(e.getMessage()).build();
        }
    }

    @Override
    public String authorize() {
        return AuthorizationFactory.getAuthorize(source).getAuthorizeUrl(config);
    }
}
