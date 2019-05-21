package me.zhyd.oauth.consts;

import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.ResponseStatus;

/**
 * 各api需要的url， 用枚举类分平台类型管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.0
 */
public enum ApiUrl {
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

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
    },
    /**
     * 钉钉
     */
    DINGTALK {
        @Override
        public String authorize() {
            return "https://oapi.dingtalk.com/connect/qrconnect";
        }

        @Override
        public String accessToken() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String userInfo() {
            return "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
        }

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
    },
    /**
     * Coding
     */
    CODING {
        @Override
        public String authorize() {
            return "https://coding.net/oauth_authorize.html";
        }

        @Override
        public String accessToken() {
            return "https://coding.net/api/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://coding.net/api/account/current_user";
        }

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
    },
    /**
     * 腾讯云开发者平台（coding升级后就变成腾讯云开发者平台了）
     */
    TENCENTCLOUD {
        @Override
        public String authorize() {
            return "https://dev.tencent.com/oauth_authorize.html";
        }

        @Override
        public String accessToken() {
            return "https://dev.tencent.com/api/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://dev.tencent.com/api/account/current_user";
        }

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
    },
    /**
     * 微信
     */
    WECHAT {
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
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
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
            return "https://oauth2.googleapis.com/tokeninfo";
        }

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
    },
    /**
     * Facebook
     */
    FACEBOOK {
        @Override
        public String authorize() {
            return "https://www.facebook.com/v3.3/dialog/oauth";
        }

        @Override
        public String accessToken() {
            return "https://graph.facebook.com/v3.3/oauth/access_token";
        }

        @Override
        public String userInfo() {
            return "https://graph.facebook.com/v3.3/me";
        }

        @Override
        public String revoke() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }

        @Override
        public String refresh() {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
    };

    /**
     * 授权的api
     *
     * @return url
     */
    public abstract String authorize();

    /**
     * 获取accessToken的api
     *
     * @return url
     */
    public abstract String accessToken();

    /**
     * 获取用户信息的api
     *
     * @return url
     */
    public abstract String userInfo();

    /**
     * 取消授权的api
     *
     * @return url
     */
    public abstract String revoke();

    /**
     * 刷新授权的api
     *
     * @return url
     */
    public abstract String refresh();

}
