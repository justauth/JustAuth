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
     * 是否支持微博
     *
     * @return true or false
     */
    public static boolean isSupportedWeibo() {
        return StringUtils.isNotEmpty(AuthConfig.weiboClientId) && StringUtils.isNotEmpty(AuthConfig.weiboClientSecret) && StringUtils.isNotEmpty(AuthConfig.weiboRedirectUri);
    }

    /**
     * 是否支持Github
     *
     * @return true or false
     */
    public static boolean isSupportedGithub() {
        return StringUtils.isNotEmpty(AuthConfig.githubClientId) && StringUtils.isNotEmpty(AuthConfig.githubClientSecret) && StringUtils.isNotEmpty(AuthConfig.githubRedirectUri);
    }

    /**
     * 是否支持Gitee
     *
     * @return true or false
     */
    public static boolean isSupportedGitee() {
        return StringUtils.isNotEmpty(AuthConfig.giteeClientId) && StringUtils.isNotEmpty(AuthConfig.giteeClientSecret) && StringUtils.isNotEmpty(AuthConfig.giteeRedirectUri);
    }

    /**
     * 是否支持QQ
     *
     * @return true or false
     */
    @Deprecated
    public static boolean isSupportedQq() {
        return false;
    }

    /**
     * 是否支持微信
     *
     * @return true or false
     */
    @Deprecated
    public static boolean isSupportedWeixin() {
        return false;
    }
}
