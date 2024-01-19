package me.zhyd.oauth.request;

import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.utils.GlobalAuthUtils;

/**
 * 微软中国登录(世纪华联)
 *
 * @author mroldx (xzfqq5201314@gmail.com)
 * @since 1.16.4
 */
public class AuthMicrosoftCnRequest extends AbstractAuthMicrosoftRequest {

    public AuthMicrosoftCnRequest(AuthConfig config) {
        super(config, AuthDefaultSource.MICROSOFT_CN);
    }

    public AuthMicrosoftCnRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.MICROSOFT_CN, authStateCache);
    }

    @Override
    protected void checkConfig(AuthConfig config) {
        super.checkConfig(config);
        // 微软中国的回调地址必须为https的链接或者localhost,不允许使用http
        if (AuthDefaultSource.MICROSOFT_CN == source && !GlobalAuthUtils.isHttpsProtocolOrLocalHost(config.getRedirectUri())) {
            // Microsoft's redirect uri must use the HTTPS or localhost
            throw new AuthException(AuthResponseStatus.ILLEGAL_REDIRECT_URI, source);
        }
    }

}
