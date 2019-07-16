package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.url.entity.*;

import java.text.MessageFormat;

/**
 * Gitee相关的URL构建类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class GiteeUrlBuilder extends AbstractUrlBuilder {

    private static final String GITEE_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String GITEE_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String GITEE_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}";

    @Override
    public String getAccessTokenUrl(String code) {
        return MessageFormat.format(GITEE_ACCESS_TOKEN_PATTERN, AuthSource.GITEE.accessToken(), config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
    }

    @Override
    public String getUserInfoUrl(AuthUserInfoEntity userInfoEntity) {
        return MessageFormat.format(GITEE_USER_INFO_PATTERN, AuthSource.GITEE.userInfo(), userInfoEntity.getAccessToken());
    }

    @Override
    public String getAuthorizeUrl() {
        return MessageFormat.format(GITEE_AUTHORIZE_PATTERN, AuthSource.GITEE.authorize(), config.getClientId(), config.getRedirectUri(), this.getRealState(config.getState()));
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
