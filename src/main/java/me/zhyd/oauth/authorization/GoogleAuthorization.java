package me.zhyd.oauth.authorization;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * Google授权
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @version 1.3
 * @since 1.3
 */
public class GoogleAuthorization implements Authorization {

    @Override
    public String getAuthorizeUrl(AuthConfig config) {
        return UrlBuilder.getGoogleAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }
}
