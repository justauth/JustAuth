package me.zhyd.oauth.request;

import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;

/**
 * 钉钉账号登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.0.0
 */
public class AuthDingTalkAccountRequest extends AbstractAuthDingtalkRequest {

    public AuthDingTalkAccountRequest(AuthConfig config) {
        super(config, AuthDefaultSource.DINGTALK_ACCOUNT);
    }

    public AuthDingTalkAccountRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.DINGTALK_ACCOUNT, authStateCache);
    }
}
