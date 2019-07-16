package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * QQ相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class QqUrlBuilder extends AbstractUrlBuilder {

    private static final String QQ_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String QQ_USER_INFO_PATTERN = "{0}?oauth_consumer_key={1}&access_token={2}&openid={3}";
    private static final String QQ_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}";
    private static final String QQ_OPENID_PATTERN = "{0}?access_token={1}&unionid={2}";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(QQ_ACCESS_TOKEN_PATTERN, AuthSource.QQ.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(QQ_USER_INFO_PATTERN, AuthSource.QQ.userInfo(), userInfoEntity.getClientId(), userInfoEntity.getAccessToken(), userInfoEntity.getOpenId());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(QQ_AUTHORIZE_PATTERN, AuthSource.QQ.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(String refreshToken) {
        return null;
    }

    @Override
    public String getRevokeUrl(String accessToken) {
        return null;
    }

    @Override
    public String getOpenIdUrl(String accessToken, boolean unionid) {
        return MessageFormat.format(QQ_OPENID_PATTERN, "https://graph.qq.com/oauth2.0/me", accessToken, unionid ? 1 : 0);
    }
}
