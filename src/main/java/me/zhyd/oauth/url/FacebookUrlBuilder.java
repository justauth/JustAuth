package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * Facebook相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class FacebookUrlBuilder extends AbstractUrlBuilder {

    private static final String FACEBOOK_AUTHORIZE_PATTERN = "{0}?client_id={1}&redirect_uri={2}&state={3}&response_type=code&scope=";
    private static final String FACEBOOK_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&code={3}&redirect_uri={4}&grant_type=authorization_code";
    private static final String FACEBOOK_USER_INFO_PATTERN = "{0}?access_token={1}&fields=id,name,birthday,gender,hometown,email,devices,picture.width(400)";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(FACEBOOK_ACCESS_TOKEN_PATTERN, AuthSource.FACEBOOK.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(FACEBOOK_USER_INFO_PATTERN, AuthSource.FACEBOOK.userInfo(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(FACEBOOK_AUTHORIZE_PATTERN, AuthSource.FACEBOOK.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        return null;
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        return null;
    }
}
