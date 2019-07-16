package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.text.MessageFormat;

/**
 * Coding相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class CodingUrlBuilder extends AbstractUrlBuilder {

    private static final String CODING_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}";
    private static final String CODING_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String CODING_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&scope=user&state={3}";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(CODING_ACCESS_TOKEN_PATTERN, AuthSource.CODING.accessToken(), config.getClientId(), config.getClientSecret(), code);
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(CODING_USER_INFO_PATTERN, AuthSource.CODING.userInfo(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(CODING_AUTHORIZE_PATTERN, AuthSource.CODING.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
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
