package me.zhyd.oauth.config;

import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.exception.AuthException;

/**
 * JustAuth内置的各api需要的url， 用枚举类分平台类型管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.0
 */
public enum AuthDefaultSource implements AuthSource {
    /**
     * Github
     */
    GITHUB {
        @Override
        public String authorize() {
            return "https://github.com/login/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://github.com/login/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.github.com/user";
        }
    },
    /**
     * 新浪微博
     */
    WEIBO {
        @Override
        public String authorize() {
            return "https://api.weibo.com/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.weibo.com/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.weibo.com/2/users/show.json";
        }

        @Override
        public String revoke() {
            return "https://api.weibo.com/oauth2/revokeoauth2";
        }
    },
    /**
     * gitee
     */
    GITEE {
        @Override
        public String authorize() {
            return "https://gitee.com/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://gitee.com/oauth/token";
        }

        @Override
        public String userInfo() {
            return "https://gitee.com/api/v5/user";
        }
    },
    /**
     * 钉钉扫码登录
     */
    DINGTALK {
        @Override
        public String authorize() {
            return "https://oapi.dingtalk.com/connect/qrconnect";
        }

        @Override
        public String accessToken() {
            throw new AuthException(AuthResponseStatus.UNSUPPORTED);
        }

        @Override
        public String userInfo() {
            return "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
        }
    },
    /**
     * 钉钉账号登录
     */
    DINGTALK_ACCOUNT {
        @Override
        public String authorize() {
            return "https://oapi.dingtalk.com/connect/oauth2/sns_authorize";
        }

        @Override
        public String accessToken() {
            return DINGTALK.accessToken();
        }

        @Override
        public String userInfo() {
            return DINGTALK.userInfo();
        }
    },
    /**
     * 百度
     */
    BAIDU {
        @Override
        public String authorize() {
            return "https://openapi.baidu.com/oauth/2.0/authorize";
        }

        @Override
        public String accessToken() {
            return "https://openapi.baidu.com/oauth/2.0/token";
        }

        @Override
        public String userInfo() {
            return "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";
        }

        @Override
        public String revoke() {
            return "https://openapi.baidu.com/rest/2.0/passport/auth/revokeAuthorization";
        }

        @Override
        public String refresh() {
            return "https://openapi.baidu.com/oauth/2.0/token";
        }
    },
    /**
     * csdn
     */
    CSDN {
        @Override
        public String authorize() {
            return "https://api.csdn.net/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.csdn.net/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.csdn.net/user/getinfo";
        }
    },
    /**
     * Coding，
     * <p>
     * 参考 https://help.coding.net/docs/project/open/oauth.html#%E7%94%A8%E6%88%B7%E6%8E%88%E6%9D%83 中的说明，
     * 新版的 coding API 地址需要传入用户团队名，这儿使用动态参数，方便在 request 中使用
     */
    CODING {
        @Override
        public String authorize() {
            return "https://%s.coding.net/oauth_authorize.html";
        }

        @Override
        public String accessToken() {
            return "https://%s.coding.net/api/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://%s.coding.net/api/account/current_user";
        }
    },
    /**
     * oschina 开源中国
     */
    OSCHINA {
        @Override
        public String authorize() {
            return "https://www.oschina.net/action/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://www.oschina.net/action/openapi/token";
        }

        @Override
        public String userInfo() {
            return "https://www.oschina.net/action/openapi/user";
        }
    },
    /**
     * 支付宝
     */
    ALIPAY {
        @Override
        public String authorize() {
            return "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm";
        }

        @Override
        public String accessToken() {
            return "https://openapi.alipay.com/gateway.do";
        }

        @Override
        public String userInfo() {
            return "https://openapi.alipay.com/gateway.do";
        }
    },
    /**
     * QQ
     */
    QQ {
        @Override
        public String authorize() {
            return "https://graph.qq.com/oauth2.0/authorize";
        }

        @Override
        public String accessToken() {
            return "https://graph.qq.com/oauth2.0/token";
        }

        @Override
        public String userInfo() {
            return "https://graph.qq.com/user/get_user_info";
        }

        @Override
        public String refresh() {
            return "https://graph.qq.com/oauth2.0/token";
        }
    },
    /**
     * 微信开放平台
     */
    WECHAT_OPEN {
        @Override
        public String authorize() {
            return "https://open.weixin.qq.com/connect/qrconnect";
        }

        @Override
        public String accessToken() {
            return "https://api.weixin.qq.com/sns/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.weixin.qq.com/sns/userinfo";
        }

        @Override
        public String refresh() {
            return "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        }
    },
    /**
     * 微信公众平台
     */
    WECHAT_MP {
        @Override
        public String authorize() {
            return "https://open.weixin.qq.com/connect/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.weixin.qq.com/sns/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.weixin.qq.com/sns/userinfo";
        }

        @Override
        public String refresh() {
            return "https://api.weixin.qq.com/sns/oauth2/refresh_token";
        }
    },
    /**
     * 淘宝
     */
    TAOBAO {
        @Override
        public String authorize() {
            return "https://oauth.taobao.com/authorize";
        }

        @Override
        public String accessToken() {
            return "https://oauth.taobao.com/token";
        }

        @Override
        public String userInfo() {
            throw new AuthException(AuthResponseStatus.UNSUPPORTED);
        }
    },
    /**
     * Google
     */
    GOOGLE {
        @Override
        public String authorize() {
            return "https://accounts.google.com/o/oauth2/v2/auth";
        }

        @Override
        public String accessToken() {
            return "https://www.googleapis.com/oauth2/v4/token";
        }

        @Override
        public String userInfo() {
            return "https://www.googleapis.com/oauth2/v3/userinfo";
        }
    },
    /**
     * Facebook
     */
    FACEBOOK {
        @Override
        public String authorize() {
            return "https://www.facebook.com/v10.0/dialog/oauth";
        }

        @Override
        public String accessToken() {
            return "https://graph.facebook.com/v10.0/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://graph.facebook.com/v10.0/me";
        }
    },
    /**
     * 抖音
     */
    DOUYIN {
        @Override
        public String authorize() {
            return "https://open.douyin.com/platform/oauth/connect";
        }

        @Override
        public String accessToken() {
            return "https://open.douyin.com/oauth/access_token/";
        }

        @Override
        public String userInfo() {
            return "https://open.douyin.com/oauth/userinfo/";
        }

        @Override
        public String refresh() {
            return "https://open.douyin.com/oauth/refresh_token/";
        }
    },
    /**
     * 领英
     */
    LINKEDIN {
        @Override
        public String authorize() {
            return "https://www.linkedin.com/oauth/v2/authorization";
        }

        @Override
        public String accessToken() {
            return "https://www.linkedin.com/oauth/v2/accessToken";
        }

        @Override
        public String userInfo() {
            return "https://api.linkedin.com/v2/me";
        }

        @Override
        public String refresh() {
            return "https://www.linkedin.com/oauth/v2/accessToken";
        }
    },
    /**
     * 微软
     */
    MICROSOFT {
        @Override
        public String authorize() {
            return "https://login.microsoftonline.com/common/oauth2/v2.0/authorize";
        }

        @Override
        public String accessToken() {
            return "https://login.microsoftonline.com/common/oauth2/v2.0/token";
        }

        @Override
        public String userInfo() {
            return "https://graph.microsoft.com/v1.0/me";
        }

        @Override
        public String refresh() {
            return "https://login.microsoftonline.com/common/oauth2/v2.0/token";
        }
    },
    /**
     * 小米
     */
    MI {
        @Override
        public String authorize() {
            return "https://account.xiaomi.com/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://account.xiaomi.com/oauth2/token";
        }

        @Override
        public String userInfo() {
            return "https://open.account.xiaomi.com/user/profile";
        }

        @Override
        public String refresh() {
            return "https://account.xiaomi.com/oauth2/token";
        }
    },
    /**
     * 今日头条
     */
    TOUTIAO {
        @Override
        public String authorize() {
            return "https://open.snssdk.com/auth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://open.snssdk.com/auth/token";
        }

        @Override
        public String userInfo() {
            return "https://open.snssdk.com/data/user_profile";
        }
    },
    /**
     * Teambition
     */
    TEAMBITION {
        @Override
        public String authorize() {
            return "https://account.teambition.com/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://account.teambition.com/oauth2/access_token";
        }

        @Override
        public String refresh() {
            return "https://account.teambition.com/oauth2/refresh_token";
        }

        @Override
        public String userInfo() {
            return "https://api.teambition.com/users/me";
        }
    },

    /**
     * 人人网
     */
    RENREN {
        @Override
        public String authorize() {
            return "https://graph.renren.com/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://graph.renren.com/oauth/token";
        }

        @Override
        public String refresh() {
            return "https://graph.renren.com/oauth/token";
        }

        @Override
        public String userInfo() {
            return "https://api.renren.com/v2/user/get";
        }
    },

    /**
     * Pinterest
     */
    PINTEREST {
        @Override
        public String authorize() {
            return "https://api.pinterest.com/oauth";
        }

        @Override
        public String accessToken() {
            return "https://api.pinterest.com/v1/oauth/token";
        }

        @Override
        public String userInfo() {
            return "https://api.pinterest.com/v1/me";
        }
    },

    /**
     * Stack Overflow
     */
    STACK_OVERFLOW {
        @Override
        public String authorize() {
            return "https://stackoverflow.com/oauth";
        }

        @Override
        public String accessToken() {
            return "https://stackoverflow.com/oauth/access_token/json";
        }

        @Override
        public String userInfo() {
            return "https://api.stackexchange.com/2.2/me";
        }
    },

    /**
     * 华为
     *
     * @since 1.10.0
     */
    HUAWEI {
        @Override
        public String authorize() {
            return "https://oauth-login.cloud.huawei.com/oauth2/v2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://oauth-login.cloud.huawei.com/oauth2/v2/token";
        }

        @Override
        public String userInfo() {
            return "https://api.vmall.com/rest.php";
        }

        @Override
        public String refresh() {
            return "https://oauth-login.cloud.huawei.com/oauth2/v2/token";
        }
    },

    /**
     * 企业微信二维码登录
     *
     * @since 1.10.0
     */
    WECHAT_ENTERPRISE {
        @Override
        public String authorize() {
            return "https://open.work.weixin.qq.com/wwopen/sso/qrConnect";
        }

        @Override
        public String accessToken() {
            return "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        }

        @Override
        public String userInfo() {
            return "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        }
    },

    /**
     * 企业微信网页登录
     */
    WECHAT_ENTERPRISE_WEB {
        @Override
        public String authorize() {
            return "https://open.weixin.qq.com/connect/oauth2/authorize";
        }

        @Override
        public String accessToken() {
            return "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        }

        @Override
        public String userInfo() {
            return "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
        }
    },

    /**
     * 酷家乐
     *
     * @since 1.11.0
     */
    KUJIALE {
        @Override
        public String authorize() {
            return "https://oauth.kujiale.com/oauth2/show";
        }

        @Override
        public String accessToken() {
            return "https://oauth.kujiale.com/oauth2/auth/token";
        }

        @Override
        public String userInfo() {
            return "https://oauth.kujiale.com/oauth2/openapi/user";
        }

        @Override
        public String refresh() {
            return "https://oauth.kujiale.com/oauth2/auth/token/refresh";
        }
    },

    /**
     * Gitlab
     *
     * @since 1.11.0
     */
    GITLAB {
        @Override
        public String authorize() {
            return "https://gitlab.com/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://gitlab.com/oauth/token";
        }

        @Override
        public String userInfo() {
            return "https://gitlab.com/api/v4/user";
        }
    },

    /**
     * 美团
     *
     * @since 1.12.0
     */
    MEITUAN {
        @Override
        public String authorize() {
            return "https://openapi.waimai.meituan.com/oauth/authorize";
        }

        @Override
        public String accessToken() {
            return "https://openapi.waimai.meituan.com/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://openapi.waimai.meituan.com/oauth/userinfo";
        }

        @Override
        public String refresh() {
            return "https://openapi.waimai.meituan.com/oauth/refresh_token";
        }
    },

    /**
     * 饿了么
     * <p>
     * 注：集成的是正式环境，非沙箱环境
     *
     * @since 1.12.0
     */
    ELEME {
        @Override
        public String authorize() {
            return "https://open-api.shop.ele.me/authorize";
        }

        @Override
        public String accessToken() {
            return "https://open-api.shop.ele.me/token";
        }

        @Override
        public String userInfo() {
            return "https://open-api.shop.ele.me/api/v1/";
        }

        @Override
        public String refresh() {
            return "https://open-api.shop.ele.me/token";
        }
    },

    /**
     * Twitter
     *
     * @since 1.13.0
     */
    TWITTER {
        @Override
        public String authorize() {
            return "https://api.twitter.com/oauth/authenticate";
        }

        @Override
        public String accessToken() {
            return "https://api.twitter.com/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.twitter.com/1.1/users/show.json";
        }
    },

    /**
     * 飞书平台，企业自建应用授权登录，原逻辑由 beacon 集成于 1.14.0 版，但最新的飞书 api 已修改，并且飞书平台一直为 {@code Deprecated} 状态
     * <p>
     * 所以，最终修改该平台的实际发布版本为 1.15.9
     *
     * @since 1.15.9
     */
    FEISHU {
        @Override
        public String authorize() {
            return "https://open.feishu.cn/open-apis/authen/v1/index";
        }

        @Override
        public String accessToken() {
            return "https://open.feishu.cn/open-apis/authen/v1/access_token";
        }

        @Override
        public String userInfo() {
            return "https://open.feishu.cn/open-apis/authen/v1/user_info";
        }

        @Override
        public String refresh() {
            return "https://open.feishu.cn/open-apis/authen/v1/refresh_access_token";
        }
    },
    /**
     * 京东
     *
     * @since 1.15.0
     */
    JD {
        @Override
        public String authorize() {
            return "https://open-oauth.jd.com/oauth2/to_login";
        }

        @Override
        public String accessToken() {
            return "https://open-oauth.jd.com/oauth2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.jd.com/routerjson";
        }

        @Override
        public String refresh() {
            return "https://open-oauth.jd.com/oauth2/refresh_token";
        }
    },

    /**
     * 阿里云
     */
    ALIYUN {
        @Override
        public String authorize() {
            return "https://signin.aliyun.com/oauth2/v1/auth";
        }

        @Override
        public String accessToken() {
            return "https://oauth.aliyun.com/v1/token";
        }

        @Override
        public String userInfo() {
            return "https://oauth.aliyun.com/v1/userinfo";
        }

        @Override
        public String refresh() {
            return "https://oauth.aliyun.com/v1/token";
        }
    },

    /**
     * 喜马拉雅
     */
    XMLY {
        @Override
        public String authorize() {
            return "https://api.ximalaya.com/oauth2/js/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.ximalaya.com/oauth2/v2/access_token";
        }

        @Override
        public String userInfo() {
            return "https://api.ximalaya.com/profile/user_info";
        }

        @Override
        public String refresh() {
            return "https://oauth.aliyun.com/v1/token";
        }
    },

    /**
     * Amazon
     *
     * @since 1.16.0
     */
    AMAZON {
        @Override
        public String authorize() {
            return "https://www.amazon.com/ap/oa";
        }

        @Override
        public String accessToken() {
            return "https://api.amazon.com/auth/o2/token";
        }

        @Override
        public String userInfo() {
            return "https://api.amazon.com/user/profile";
        }

        @Override
        public String refresh() {
            return "https://api.amazon.com/auth/o2/token";
        }
    },
    /**
     * Slack
     *
     * @since 1.16.0
     */
    SLACK {
        @Override
        public String authorize() {
            return "https://slack.com/oauth/v2/authorize";
        }

        /**
         * 该 API 获取到的是 access token
         *
         * https://slack.com/api/oauth.token 获取到的是 workspace token
         *
         * @return String
         */
        @Override
        public String accessToken() {
            return "https://slack.com/api/oauth.v2.access";
        }

        @Override
        public String userInfo() {
            return "https://slack.com/api/users.info";
        }

        @Override
        public String revoke() {
            return "https://slack.com/api/auth.revoke";
        }
    },
    /**
     * line
     *
     * @since 1.16.0
     */
    LINE {
        @Override
        public String authorize() {
            return "https://access.line.me/oauth2/v2.1/authorize";
        }

        @Override
        public String accessToken() {
            return "https://api.line.me/oauth2/v2.1/token";
        }

        @Override
        public String userInfo() {
            return "https://api.line.me/v2/profile";
        }

        @Override
        public String refresh() {
            return "https://api.line.me/oauth2/v2.1/token";
        }

        @Override
        public String revoke() {
            return "https://api.line.me/oauth2/v2.1/revoke";
        }
    },
    /**
     * Okta，
     * <p>
     * 团队/组织的域名不同，此处通过配置动态组装
     *
     * @since 1.16.0
     */
    OKTA {
        @Override
        public String authorize() {
            return "https://%s.okta.com/oauth2/%s/v1/authorize";
        }

        @Override
        public String accessToken() {
            return "https://%s.okta.com/oauth2/%s/v1/token";
        }

        @Override
        public String refresh() {
            return "https://%s.okta.com/oauth2/%s/v1/token";
        }

        @Override
        public String userInfo() {
            return "https://%s.okta.com/oauth2/%s/v1/userinfo";
        }

        @Override
        public String revoke() {
            return "https://%s.okta.com/oauth2/%s/v1/revoke";
        }
    },
}
