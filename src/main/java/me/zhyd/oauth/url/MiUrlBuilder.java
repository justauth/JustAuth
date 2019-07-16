package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * 小米相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class MiUrlBuilder extends AbstractUrlBuilder {

    private static final String MI_AUTHORIZE_PATTERN = "{0}?client_id={1}&redirect_uri={2}&response_type=code&scope=1%203%204%206&state={3}&skip_confirm=false";
    private static final String MI_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&redirect_uri={3}&code={4}&grant_type=authorization_code";
    private static final String MI_USER_INFO_PATTERN = "{0}?clientId={1}&token={2}";
    private static final String MI_REFRESH_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&redirect_uri={3}&refresh_token={4}&grant_type=refresh_token";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        AuthConfig config = accessTokenEntity.getConfig();
        return MessageFormat.format(MI_ACCESS_TOKEN_PATTERN, AuthSource.MI.accessToken(), config.getClientId(), config.getClientSecret(), config.getRedirectUri(), accessTokenEntity.getCode());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(MI_USER_INFO_PATTERN, AuthSource.MI.userInfo(), userInfoEntity.getClientId(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(MI_AUTHORIZE_PATTERN, AuthSource.MI.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(AuthRefreshTokenEntity refreshTokenEntity) {
        AuthConfig config = refreshTokenEntity.getConfig();
        return MessageFormat.format(MI_REFRESH_TOKEN_PATTERN, AuthSource.MI.refresh(), config.getClientId(), config.getClientSecret(), config.getRedirectUri(), refreshTokenEntity.getRefreshToken());
    }

    @Override
    public String getRevokeUrl(AuthRevokeEntity revokeEntity) {
        return null;
    }
}
