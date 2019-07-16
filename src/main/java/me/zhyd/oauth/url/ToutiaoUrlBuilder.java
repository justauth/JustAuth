package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * 今日头条相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class ToutiaoUrlBuilder extends AbstractUrlBuilder {

    private static final String TOUTIAO_ACCESS_TOKEN_PATTERN = "{0}?client_key={1}&client_secret={2}&code={3}&grant_type=authorize_code";
    private static final String TOUTIAO_USER_INFO_PATTERN = "{0}?client_key={1}&access_token={2}";
    private static final String TOUTIAO_AUTHORIZE_PATTERN = "{0}?client_key={1}&redirect_uri={2}&state={3}&response_type=code&auth_only=1&display=0";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        AuthConfig config = accessTokenEntity.getConfig();
        return MessageFormat.format(TOUTIAO_ACCESS_TOKEN_PATTERN, AuthSource.TOUTIAO.accessToken(), config.getClientId(),
                config.getClientSecret(), accessTokenEntity.getCode());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(TOUTIAO_USER_INFO_PATTERN, AuthSource.TOUTIAO.userInfo(), userInfoEntity.getClientId(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(TOUTIAO_AUTHORIZE_PATTERN, AuthSource.TOUTIAO.authorize(), config.getClientId(),
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
