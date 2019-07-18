package me.zhyd.oauth.url;

import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

import static me.zhyd.oauth.config.AuthSource.PINTEREST;

/**
 * Pinterest相关的URL构建类
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @version 1.9.0
 * @since 1.9.0
 */
public class AuthPinterestUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String PINTEREST_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}";
    private static final String PINTEREST_USER_INFO_PATTERN = "{0}?access_token={1}&fields=id,username,first_name,last_name,bio,image";
    private static final String PINTEREST_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}&scope=read_public";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(PINTEREST_ACCESS_TOKEN_PATTERN, PINTEREST.accessToken(), config.getClientId(), config.getClientSecret(), code);
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(PINTEREST_USER_INFO_PATTERN, PINTEREST.userInfo(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(PINTEREST_AUTHORIZE_PATTERN, PINTEREST.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        throw new AuthException(AuthResponseStatus.UNSUPPORTED);
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        throw new AuthException(AuthResponseStatus.UNSUPPORTED);
    }
}
