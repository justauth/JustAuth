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
public class TencentCloudUrlBuilder extends AbstractUrlBuilder {

    private static final String TENCENT_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}";
    private static final String TENCENT_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String TENCENT_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&scope=user&state={3}";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        AuthConfig config = accessTokenEntity.getConfig();
        return MessageFormat.format(TENCENT_ACCESS_TOKEN_PATTERN, AuthSource.TENCENT_CLOUD.accessToken(), config.getClientId(),
                config.getClientSecret(), accessTokenEntity.getCode());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(TENCENT_USER_INFO_PATTERN, AuthSource.TENCENT_CLOUD.userInfo(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(TENCENT_AUTHORIZE_PATTERN, AuthSource.TENCENT_CLOUD.authorize(), config.getClientId(),
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
