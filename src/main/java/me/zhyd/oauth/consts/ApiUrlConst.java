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

}
