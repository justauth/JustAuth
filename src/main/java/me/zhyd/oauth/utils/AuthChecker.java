package me.zhyd.oauth.utils;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;

/**
 * 授权配置类的校验器
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthChecker {

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
    public static void checkConfig(AuthConfig config, AuthSource source) {
        String redirectUri = config.getRedirectUri();
        if (!GlobalAuthUtil.isHttpProtocol(redirectUri) && !GlobalAuthUtil.isHttpsProtocol(redirectUri)) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_REDIRECT_URI);
        }
        // facebook的回调地址必须为https的链接
        if (AuthSource.FACEBOOK == source && !GlobalAuthUtil.isHttpsProtocol(redirectUri)) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_REDIRECT_URI);
        }
        // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1
        if (AuthSource.ALIPAY == source && GlobalAuthUtil.isLocalHost(redirectUri)) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_REDIRECT_URI);
        }
    }

    /**
     * 校验回调传回的code
     *
     * @param code 回调时传回的code
     */
    public static void checkCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_CODE);
        }
    }

    /**
     * 校验state的合法性防止被CSRF
     *
     * @param newState      新的state，一般为回调时传回的state（可能被篡改）
     * @param originalState 原始的state，发起授权时向第三方平台传递的state
     */
    public static void checkState(String newState, String originalState) {
        // 如果原始state为空，表示当前平台未使用state
        if (StringUtils.isEmpty(originalState)) {
            return;
        }
        // 如果授权之前使用了state，但是回调时未返回state，则表示当前请求为非法的请求，可能正在被CSRF攻击
        if (StringUtils.isEmpty(newState)) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_REQUEST);
        }
        // 如果授权前后的state不一致，则表示当前请求为非法的请求，新的state可能为伪造
        if (!newState.equals(originalState)) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_REQUEST);
        }
    }
}
