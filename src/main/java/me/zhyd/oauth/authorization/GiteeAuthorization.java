package me.zhyd.oauth.authorization;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 码云授权
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class GiteeAuthorization implements Authorization {

    @Override
    public String getAuthorizeUrl(AuthConfig config) {
        return UrlBuilder.getGiteeAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }
}
