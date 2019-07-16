package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class WeiboUrlBuilder extends AbstractUrlBuilder {

    private static final String WEIBO_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String WEIBO_USER_INFO_PATTERN = "{0}?{1}";
    private static final String WEIBO_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        AuthConfig config = accessTokenEntity.getConfig();
        return MessageFormat.format(WEIBO_ACCESS_TOKEN_PATTERN, AuthSource.WEIBO.accessToken(), config.getClientId(),
                config.getClientSecret(), accessTokenEntity.getCode(), config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(WEIBO_USER_INFO_PATTERN, AuthSource.WEIBO.userInfo(), userInfoEntity.getExtra());
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(WEIBO_AUTHORIZE_PATTERN, AuthSource.WEIBO.authorize(), config.getClientId(),
                config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(AuthRefreshTokenEntity refreshTokenEntity) {
        return null;
    }

    @Override
    public String getRevokeUrl(AuthRevokeEntity revokeEntity) {
        return null;
    }
}
