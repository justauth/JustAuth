package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * Microsoft相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthLinkedinUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String LINKEDIN_AUTHORIZE_PATTERN = "{0}?client_id={1}&redirect_uri={2}&state={3}&response_type=code&scope=r_liteprofile%20r_emailaddress%20w_member_social";
    private static final String LINKEDIN_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&code={3}&redirect_uri={4}&grant_type=authorization_code";
    private static final String LINKEDIN_USER_INFO_PATTERN = "{0}?projection=(id,firstName,lastName,profilePicture(displayImage~:playableStreams))";
    private static final String LINKEDIN_REFRESH_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&refresh_token={3}&grant_type=refresh_token";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(LINKEDIN_ACCESS_TOKEN_PATTERN, AuthSource.LINKEDIN.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(LINKEDIN_USER_INFO_PATTERN, AuthSource.LINKEDIN.userInfo());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(LINKEDIN_AUTHORIZE_PATTERN, AuthSource.LINKEDIN.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        return MessageFormat.format(LINKEDIN_REFRESH_TOKEN_PATTERN, AuthSource.LINKEDIN.refresh(), config.getClientId(), config.getClientSecret(), refreshToken);
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }
}
