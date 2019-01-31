package me.zhyd.oauth.utils;

import me.zhyd.oauth.consts.ApiUrlConst;

import java.text.MessageFormat;

/**
 * Url构建工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 12:26
 * @since 1.0
 */
public class UrlBuilder {

    private static final String GITHUB_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&code={3}&redirect_uri={4}";
    private static final String GITHUB_USER_INFO_PATTERN = "{0}?{1}";
    private static final String GITHUB_AUTHORIZE_PATTERN = "{0}?client_id={1}&state=1&redirect_uri={2}";

    private static final String WEIBO_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String WEIBO_USER_INFO_PATTERN = "{0}?uid={1}&access_token={2}";
    private static final String WEIBO_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}";

    private static final String GITEE_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String GITEE_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String GITEE_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}";

    /**
     * 获取githubtoken的接口地址
     *
     * @param clientId     github应用的Client ID
     * @param clientSecret github应用的Client Secret
     * @param code         github授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return 换取github授权token的真实地址
     */
    public static String getGithubAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(GITHUB_ACCESS_TOKEN_PATTERN, ApiUrlConst.GITHUB_ACCESS_TOKEN_URL, clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取github用户详情的接口地址
     *
     * @param token github 应用的token
     * @return json
     */
    public static String getGithubUserInfoUrl(String token) {
        return MessageFormat.format(GITHUB_USER_INFO_PATTERN, ApiUrlConst.GITHUB_USER_INFO_URL, token);
    }

    /**
     * 获取github授权地址
     *
     * @param clientId    github 应用的Client ID
     * @param redirectUrl github 应用授权成功后的回调地址
     * @return json
     */
    public static String getGithubAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(GITHUB_AUTHORIZE_PATTERN, ApiUrlConst.GITHUB_AUTHORIZE_URL, clientId, redirectUrl);
    }

    /**
     * 获取weibo token的接口地址
     *
     * @param clientId     weibo应用的App Key
     * @param clientSecret weibo应用的App Secret
     * @param code         weibo授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return 换取weibo授权token的真实地址
     */
    public static String getWeiboAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(WEIBO_ACCESS_TOKEN_PATTERN, ApiUrlConst.WEIBO_ACCESS_TOKEN_URL, clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取weibo用户详情的接口地址
     *
     * @param uid   用户id
     * @param token weibo 应用的token
     * @return json
     */
    public static String getWeiboUserInfoUrl(String uid, String token) {
        return MessageFormat.format(WEIBO_USER_INFO_PATTERN, ApiUrlConst.WEIBO_USER_INFO_URL, uid, token);
    }

    /**
     * 获取weibo授权地址
     *
     * @param clientId    weibo 应用的Client ID
     * @param redirectUrl weibo 应用授权成功后的回调地址
     * @return json
     */
    public static String getWeiboAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(WEIBO_AUTHORIZE_PATTERN, ApiUrlConst.WEIBO_AUTHORIZE_URL, clientId, redirectUrl);
    }

    /**
     * 获取gitee token的接口地址
     *
     * @param clientId     gitee应用的Client ID
     * @param clientSecret gitee应用的Client Secret
     * @param code         gitee授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return 换取gitee授权token的真实地址
     */
    public static String getGiteeAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(GITEE_ACCESS_TOKEN_PATTERN, ApiUrlConst.GITEE_ACCESS_TOKEN_URL, clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取gitee用户详情的接口地址
     *
     * @param token gitee 应用的token
     * @return json
     */
    public static String getGiteeUserInfoUrl(String token) {
        return MessageFormat.format(GITEE_USER_INFO_PATTERN, ApiUrlConst.GITEE_USER_INFO_URL, token);
    }

    /**
     * 获取gitee授权地址
     *
     * @param clientId    gitee 应用的Client ID
     * @param redirectUrl gitee 应用授权成功后的回调地址
     * @return json
     */
    public static String getGiteeAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(GITEE_AUTHORIZE_PATTERN, ApiUrlConst.GITEE_AUTHORIZE_URL, clientId, redirectUrl);
    }
}
