package me.zhyd.oauth.url;

import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

import static me.zhyd.oauth.config.AuthSource.STACK_OVERFLOW;

/**
 * Stack Overflow相关的URL构建类
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @version 1.9.0
 * @since 1.9.0
 */
public class AuthStackOverflowUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String SO_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&redirect_uri={3}&code={4}";
    private static final String SO_USER_INFO_PATTERN = "{0}?access_token={1}&site=stackoverflow&key={2}";
    private static final String SO_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(SO_ACCESS_TOKEN_PATTERN, STACK_OVERFLOW.accessToken(), config.getClientId(), config.getClientSecret(), config.getRedirectUri(), code);
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(SO_USER_INFO_PATTERN, STACK_OVERFLOW.userInfo(), userInfoEntity.getAccessToken(), config.getStackOverflowKey());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(SO_AUTHORIZE_PATTERN, STACK_OVERFLOW.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
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
