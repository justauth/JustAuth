package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * 淘宝相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthTaobaoUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String TAOBAO_AUTHORIZE_PATTERN = "{0}?response_type=code&client_id={1}&redirect_uri={2}&state={3}&view=web";
    private static final String TAOBAO_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&code={3}&redirect_uri={4}&grant_type=authorization_code";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(TAOBAO_ACCESS_TOKEN_PATTERN, AuthSource.TAOBAO.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(TAOBAO_AUTHORIZE_PATTERN, AuthSource.TAOBAO.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
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
