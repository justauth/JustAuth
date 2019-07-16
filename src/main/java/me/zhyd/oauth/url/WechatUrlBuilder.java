package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * 微信相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class WechatUrlBuilder extends AbstractUrlBuilder {

    private static final String WECHAT_AUTHORIZE_PATTERN = "{0}?appid={1}&redirect_uri={2}&response_type=code&scope=snsapi_login&state={3}#wechat_redirect";
    private static final String WECHAT_ACCESS_TOKEN_PATTERN = "{0}?appid={1}&secret={2}&code={3}&grant_type=authorization_code";
    private static final String WECHAT_REFRESH_TOKEN_PATTERN = "{0}?appid={1}&grant_type=refresh_token&refresh_token={2}";
    private static final String WECHAT_USER_INFO_PATTERN = "{0}?access_token={1}&openid={2}&lang=zh_CN";

    @Override
    public String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity) {
        AuthConfig config = accessTokenEntity.getConfig();
        return MessageFormat.format(WECHAT_ACCESS_TOKEN_PATTERN, AuthSource.WECHAT.accessToken(), config.getClientId(), config.getClientSecret(), accessTokenEntity.getCode());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(WECHAT_USER_INFO_PATTERN, AuthSource.WECHAT.userInfo(), userInfoEntity.getAccessToken(), userInfoEntity.getOpenId());
    }

    @Override
    public String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity) {
        AuthConfig config = authorizeEntity.getConfig();
        return MessageFormat.format(WECHAT_AUTHORIZE_PATTERN, AuthSource.WECHAT.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
    }

    @Override
    public String getRefreshUrl(AuthRefreshTokenEntity refreshTokenEntity) {
        return MessageFormat.format(WECHAT_REFRESH_TOKEN_PATTERN, AuthSource.WECHAT.refresh(), refreshTokenEntity.getClientId(), refreshTokenEntity.getRefreshToken());
    }

    @Override
    public String getRevokeUrl(AuthRevokeEntity revokeEntity) {
        return null;
    }
}
