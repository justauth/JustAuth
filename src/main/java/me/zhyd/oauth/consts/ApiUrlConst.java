package me.zhyd.oauth.consts;

/**
 * 各api需要的url常量类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 12:26
 * @since 1.0
 */
public class ApiUrlConst {

    /**
     * 获取github access_token的地址
     */
    public static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    /**
     * 获取github用户信息的地址
     */
    public static final String GITHUB_USER_INFO_URL = "https://api.github.com/user";

    /**
     * 获取github授权地址
     */
    public static final String GITHUB_AUTHORIZE_URL = "https://github.com/login/oauth/authorize";

    /**
     * 获取weibo access_token的地址
     */
    public static final String WEIBO_ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";

    /**
     * 获取weibo用户信息的地址
     */
    public static final String WEIBO_USER_INFO_URL = "https://api.weibo.com/2/users/show.json";

    /**
     * 获取weibo授权地址
     */
    public static final String WEIBO_AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize";

    /**
     * 获取gitee access_token的地址
     */
    public static final String GITEE_ACCESS_TOKEN_URL = "https://gitee.com/oauth/token";

    /**
     * 获取gitee用户信息的地址
     */
    public static final String GITEE_USER_INFO_URL = "https://gitee.com/api/v5/user";

    /**
     * 获取gitee授权地址
     */
    public static final String GITEE_AUTHORIZE_URL = "https://gitee.com/oauth/authorize";

    /**
     * 获取钉钉登录二维码的地址
     */
    public static final String DING_TALK_QRCONNECT_URL = "https://oapi.dingtalk.com/connect/qrconnect";

    /**
     * 获取钉钉用户信息的地址
     */
    public static final String DING_TALK_USER_INFO_URL = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";

    /**
     * 获取baidu access_token的地址
     */
    public static final String BAIDU_ACCESS_TOKEN_URL = "https://openapi.baidu.com/oauth/2.0/token";

    /**
     * 获取baidu用户信息的地址
     */
    public static final String BAIDU_USER_INFO_URL = "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";

    /**
     * 获取baidu授权地址
     */
    public static final String BAIDU_AUTHORIZE_URL = "https://openapi.baidu.com/oauth/2.0/authorize";

    /**
     * 收回baidu授权的地址
     */
    public static final String BAIDU_REVOKE_URL = "https://openapi.baidu.com/rest/2.0/passport/auth/revokeAuthorization";

    /**
     * 获取csdn access_token的地址
     */
    public static final String CSDN_ACCESS_TOKEN_URL = "https://api.csdn.net/oauth2/access_token";

    /**
     * 获取csdn用户信息的地址
     */
    public static final String CSDN_USER_INFO_URL = "https://api.csdn.net/user/getinfo";

    /**
     * 获取csdn授权地址
     */
    public static final String CSDN_AUTHORIZE_URL = "https://api.csdn.net/oauth2/authorize";

    /**
     * 获取coding access_token的地址
     */
    public static final String CODING_ACCESS_TOKEN_URL = "https://coding.net/api/oauth/access_token";

    /**
     * 获取coding用户信息的地址
     */
    public static final String CODING_USER_INFO_URL = "https://coding.net/api/account/current_user";

    /**
     * 获取coding授权地址
     */
    public static final String CODING_AUTHORIZE_URL = "https://coding.net/oauth_authorize.html";

    /**
     * 获取腾讯云开发者平台 access_token的地址（coding升级后就变成腾讯云开发者平台了）
     */
    public static final String TENCENT_ACCESS_TOKEN_URL = "https://dev.tencent.com/api/oauth/access_token";

    /**
     * 获取腾讯云开发者平台用户信息的地址（coding升级后就变成腾讯云开发者平台了）
     */
    public static final String TENCENT_USER_INFO_URL = "https://dev.tencent.com/api/account/current_user";

    /**
     * 获取腾讯云开发者平台授权地址（coding升级后就变成腾讯云开发者平台了）
     */
    public static final String TENCENT_AUTHORIZE_URL = "https://dev.tencent.com/oauth_authorize.html";

    /**
     * 获取oschina access_token的地址
     */
    public static final String OSCHINA_ACCESS_TOKEN_URL = "https://www.oschina.net/action/openapi/token";

    /**
     * 获取oschina用户信息的地址
     */
    public static final String OSCHINA_USER_INFO_URL = "https://www.oschina.net/action/openapi/user";

    /**
     * 获取oschina授权地址
     */
    public static final String OSCHINA_AUTHORIZE_URL = "https://www.oschina.net/action/oauth2/authorize";

}
