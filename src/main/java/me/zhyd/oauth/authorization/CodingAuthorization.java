package me.zhyd.oauth.authorization;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * Coding授权
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class CodingAuthorization implements Authorization {

    @Override
    public String getAuthorizeUrl(AuthConfig config) {
        return UrlBuilder.getCodingAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }
}
