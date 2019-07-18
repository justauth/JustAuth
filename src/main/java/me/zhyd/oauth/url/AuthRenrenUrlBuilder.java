package me.zhyd.oauth.url;

import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

import static me.zhyd.oauth.config.AuthSource.RENREN;

/**
 * 人人网相关的URL构建类
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @version 1.8.1
 * @since 1.8.1
 */
public class AuthRenrenUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String RENREN_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String RENREN_USER_INFO_PATTERN = "{0}?access_token={1}&userId={2}";
    private static final String RENREN_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}";
    private static final String RENREN_REFRESH_PATTERN = "{0}?refresh_token={1}&client_id={2}&client_secret={3}&grant_type=refresh_token";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(RENREN_ACCESS_TOKEN_PATTERN, RENREN.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(RENREN_USER_INFO_PATTERN, RENREN.userInfo(), userInfoEntity.getAccessToken(), userInfoEntity.getOpenId());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(RENREN_AUTHORIZE_PATTERN, RENREN.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        return MessageFormat.format(RENREN_REFRESH_PATTERN, RENREN.refresh(), refreshToken, config.getClientId(), config.getClientSecret());
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        throw new AuthException(AuthResponseStatus.UNSUPPORTED);
    }
}
