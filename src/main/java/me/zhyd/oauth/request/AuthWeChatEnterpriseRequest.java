package me.zhyd.oauth.request;

import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * <p>
 * 企业微信二维码登录
 * </p>
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @since 1.10.0
 */
public class AuthWeChatEnterpriseRequest extends AbstractAuthWeChatEnterpriseRequest {
    public AuthWeChatEnterpriseRequest(AuthConfig config) {
        super(config, AuthDefaultSource.WECHAT_ENTERPRISE);
    }

    public AuthWeChatEnterpriseRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.WECHAT_ENTERPRISE, authStateCache);
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("appid", config.getClientId())
            .queryParam("agentid", config.getAgentId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("state", getRealState(state))
            .build();
    }
}
