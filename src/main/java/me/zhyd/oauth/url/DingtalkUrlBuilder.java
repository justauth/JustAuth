package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * 钉钉相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class DingtalkUrlBuilder extends AbstractUrlBuilder {

    private static final String DING_TALK_QRCONNECT_PATTERN = "{0}?appid={1}&response_type=code&scope=snsapi_login&redirect_uri={2}&state={3}";
    private static final String DING_TALK_USER_INFO_PATTERN = "{0}?signature={1}&timestamp={2}&accessKey={3}";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        return null;
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(DING_TALK_USER_INFO_PATTERN, AuthSource.DINGTALK.userInfo(), userInfoEntity.getSignature(), userInfoEntity.getTimestamp(), userInfoEntity.getClientId());
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(DING_TALK_QRCONNECT_PATTERN, AuthSource.DINGTALK.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
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
