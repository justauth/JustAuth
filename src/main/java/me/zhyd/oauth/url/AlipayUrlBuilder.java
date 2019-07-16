package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * Baidu相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AlipayUrlBuilder extends AbstractUrlBuilder {

    private static final String ALIPAY_AUTHORIZE_PATTERN = "{0}?app_id={1}&scope=auth_user&redirect_uri={2}&state={3}";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        return null;
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return null;
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(ALIPAY_AUTHORIZE_PATTERN, AuthSource.ALIPAY.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(AuthRefreshTokenEntity refreshTokenEntity) {
        return null;
    }

    @Override
    public String getRevokeUrl(AuthRevokeEntity revokeEntity) {
        return null;
    }
}
