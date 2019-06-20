package me.zhyd.oauth.utils;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.ResponseStatus;

/**
 * 授权配置类的校验器
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthConfigChecker {

    /**
     * 是否支持第三方登录
     *
     * @param config config
     * @param source source
     * @return true or false
     */
    public static boolean isSupportedAuth(AuthConfig config, AuthSource source) {
        boolean isSupported = StringUtils.isNotEmpty(config.getClientId()) && StringUtils.isNotEmpty(config.getClientSecret()) && StringUtils.isNotEmpty(config.getRedirectUri());
        if (isSupported && AuthSource.ALIPAY == source) {
            isSupported = StringUtils.isNotEmpty(config.getAlipayPublicKey());
        }
        return isSupported;
    }

    /**
     * 检查配置合法性。针对部分平台， 对redirect uri有特定要求。一般来说redirect uri都是http://，而对于facebook平台， redirect uri 必须是https的链接
     *
     * @param config config
     * @param source source
     */
    public static void check(AuthConfig config, AuthSource source) {
        String redirectUri = config.getRedirectUri();
        if (!GlobalAuthUtil.isHttpProtocol(redirectUri) && !GlobalAuthUtil.isHttpsProtocol(redirectUri)) {
            throw new AuthException(ResponseStatus.ILLEGAL_REDIRECT_URI);
        }
        // facebook的回调地址必须为https的链接
        if (AuthSource.FACEBOOK == source && !GlobalAuthUtil.isHttpsProtocol(redirectUri)) {
            throw new AuthException(ResponseStatus.ILLEGAL_REDIRECT_URI);
        }
        // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1
        if (AuthSource.ALIPAY == source && GlobalAuthUtil.isLocalHost(redirectUri)) {
            throw new AuthException(ResponseStatus.ILLEGAL_REDIRECT_URI);
        }
    }
}
