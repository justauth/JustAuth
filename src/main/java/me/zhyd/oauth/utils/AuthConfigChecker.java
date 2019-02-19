package me.zhyd.oauth.utils;

import me.zhyd.oauth.config.AuthConfig;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 15:46
 * @since 1.8
 */
public class AuthConfigChecker {

    /**
     * 是否支持第三方登录
     *
     * @return true or false
     */
    public static boolean isSupportedAuth(AuthConfig config) {
        return StringUtils.isNotEmpty(config.getClientId()) && StringUtils.isNotEmpty(config.getClientSecret()) && StringUtils.isNotEmpty(config.getRedirectUri());
    }
}
