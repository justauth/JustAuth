package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * Microsoft相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class MicrosoftUrlBuilder extends AbstractUrlBuilder {

    private static final String MICROSOFT_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&response_mode=query&scope=offline_access%20user.read%20mail.read&state={3}";
    private static final String MICROSOFT_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&scope=user.read%20mail.read&redirect_uri={3}&code={4}&grant_type=authorization_code";
    private static final String MICROSOFT_USER_INFO_PATTERN = "{0}";
    private static final String MICROSOFT_REFRESH_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&scope=user.read%20mail.read&redirect_uri={3}&refresh_token={4}&grant_type=refresh_token";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        AuthConfig config = accessTokenEntity.getConfig();
        return MessageFormat.format(MICROSOFT_ACCESS_TOKEN_PATTERN, AuthSource.MICROSOFT.accessToken(), config.getClientId(), config.getClientSecret(), config.getRedirectUri(), accessTokenEntity.getCode());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(MICROSOFT_USER_INFO_PATTERN, AuthSource.MICROSOFT.userInfo());
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(MICROSOFT_AUTHORIZE_PATTERN, AuthSource.MICROSOFT.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(AuthRefreshTokenEntity refreshTokenEntity) {
        AuthConfig config = refreshTokenEntity.getConfig();
        return MessageFormat.format(MICROSOFT_REFRESH_TOKEN_PATTERN, AuthSource.MICROSOFT.refresh(), config.getClientId(), config.getClientSecret(), config.getRedirectUri(), refreshTokenEntity.getRefreshToken());
    }

    @Override
    public String getRevokeUrl(AuthRevokeEntity revokeEntity) {
        return null;
    }
}
