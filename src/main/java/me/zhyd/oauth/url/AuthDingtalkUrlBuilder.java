package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * 钉钉相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthDingtalkUrlBuilder extends AuthDefaultUrlBuilder {

    private static final String DING_TALK_QRCONNECT_PATTERN = "{0}?appid={1}&response_type=code&scope=snsapi_login&redirect_uri={2}&state={3}";
    private static final String DING_TALK_USER_INFO_PATTERN = "{0}?signature={1}&timestamp={2}&accessKey={3}";

    @Override
    public String getAccessTokenUrl(String code) {
        throw new AuthException(AuthResponseStatus.NOT_IMPLEMENTED);
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(DING_TALK_USER_INFO_PATTERN, AuthSource.DINGTALK.userInfo(), userInfoEntity.getSignature(), userInfoEntity.getTimestamp(), userInfoEntity.getClientId());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(DING_TALK_QRCONNECT_PATTERN, AuthSource.DINGTALK.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
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
