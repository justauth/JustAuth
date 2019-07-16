package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * OSChina相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthOschinaUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String OSCHINA_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}&dataType=json";
    private static final String OSCHINA_USER_INFO_PATTERN = "{0}?access_token={1}&dataType=json";
    private static final String OSCHINA_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(OSCHINA_ACCESS_TOKEN_PATTERN, AuthSource.OSCHINA.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(OSCHINA_USER_INFO_PATTERN, AuthSource.OSCHINA.userInfo(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(OSCHINA_AUTHORIZE_PATTERN, AuthSource.OSCHINA.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }
}
