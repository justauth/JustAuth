package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * Google相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class GoogleUrlBuilder extends AbstractUrlBuilder {

    private static final String GOOGLE_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&scope=openid%20email%20profile&redirect_uri={2}&state={3}";
    private static final String GOOGLE_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&code={3}&redirect_uri={4}&grant_type=authorization_code";
    private static final String GOOGLE_USER_INFO_PATTERN = "{0}?id_token={1}";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(GOOGLE_ACCESS_TOKEN_PATTERN, AuthSource.GOOGLE.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(GOOGLE_USER_INFO_PATTERN, AuthSource.GOOGLE.userInfo(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(GOOGLE_AUTHORIZE_PATTERN, AuthSource.GOOGLE.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        return null;
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        return null;
    }
}
