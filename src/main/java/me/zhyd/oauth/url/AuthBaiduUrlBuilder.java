package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * Baidu相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthBaiduUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String BAIDU_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String BAIDU_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String BAIDU_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&display=popup&state={3}";
    private static final String BAIDU_REVOKE_PATTERN = "{0}?access_token={1}";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(BAIDU_ACCESS_TOKEN_PATTERN, AuthSource.BAIDU.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(BAIDU_USER_INFO_PATTERN, AuthSource.BAIDU.userInfo(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(BAIDU_AUTHORIZE_PATTERN, AuthSource.BAIDU.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        return MessageFormat.format(BAIDU_REVOKE_PATTERN, AuthSource.BAIDU.revoke(), accessToken);
    }
}
