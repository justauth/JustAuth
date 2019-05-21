package me.zhyd.oauth.authorization;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * Facebook授权
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.3
 * @since 1.3
 */
public class FacebookAuthorization implements Authorization {

    @Override
    public String getAuthorizeUrl(AuthConfig config) {
        return UrlBuilder.getFacebookAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }
}
