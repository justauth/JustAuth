package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * 抖音相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class DouyinUrlBuilder extends AbstractUrlBuilder {

    private static final String DOUYIN_AUTHORIZE_PATTERN = "{0}?client_key={1}&redirect_uri={2}&state={3}&response_type=code&scope=user_info";
    private static final String DOUYIN_ACCESS_TOKEN_PATTERN = "{0}?client_key={1}&client_secret={2}&code={3}&grant_type=authorization_code";
    private static final String DOUYIN_USER_INFO_PATTERN = "{0}?access_token={1}&open_id={2}";
    private static final String DOUYIN_REFRESH_TOKEN_PATTERN = "{0}?client_key={1}&refresh_token={2}&grant_type=refresh_token";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        AuthConfig config = accessTokenEntity.getConfig();
        return MessageFormat.format(DOUYIN_ACCESS_TOKEN_PATTERN, AuthSource.DOUYIN.accessToken(), config.getClientId(), config.getClientSecret(), accessTokenEntity.getCode());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(DOUYIN_USER_INFO_PATTERN, AuthSource.DOUYIN.userInfo(), userInfoEntity.getAccessToken(), userInfoEntity.getOpenId());
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(DOUYIN_AUTHORIZE_PATTERN, AuthSource.DOUYIN.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(AuthRefreshTokenEntity refreshTokenEntity) {
        AuthConfig config = refreshTokenEntity.getConfig();
        return MessageFormat.format(DOUYIN_REFRESH_TOKEN_PATTERN, AuthSource.DOUYIN.refresh(), config.getClientId(), refreshTokenEntity.getRefreshToken());
    }

    @Override
    public String getRevokeUrl(AuthRevokeEntity revokeEntity) {
        return null;
    }
}
