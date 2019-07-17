package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/7/16 22:22
 * @since 1.8
 */
public class AuthTeambitionUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String TEAMBITION_AUTHORIZE_PATTERN = "{0}?client_id={1}&redirect_uri={2}&state={3}&response_type=code";

    @Override
    public String getAccessTokenUrl(String code) {
        return AuthSource.TEAMBITION.accessToken();
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return AuthSource.TEAMBITION.userInfo();
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(TEAMBITION_AUTHORIZE_PATTERN, AuthSource.TEAMBITION.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        return AuthSource.TEAMBITION.refresh();
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        return null;
    }
}
