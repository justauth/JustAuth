package me.zhyd.oauth.url;

import me.zhyd.oauth.url.entity.*;
import me.zhyd.oauth.utils.StringUtils;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public abstract class AbstractUrlBuilder {

    /**
     * 获取AccessToken的URL
     *
     * @param accessTokenEntity
     * @return AccessTokenUrl
     */
    public abstract String getAccessTokenUrl(AuthAccessTokenEntity accessTokenEntity);

    /**
     * 获取用户信息的URL
     *
     * @param userInfoEntity
     * @return UserInfoUrl
     */
    public abstract String getUserInfoUrl(AuthUserInfoEntity userInfoEntity);

    /**
     * 获取跳转授权页面的URL
     *
     * @param authorizeEntity
     * @return AuthorizeUrl
     */
    public abstract String getAuthorizeUrl(AuthAuthorizeEntity authorizeEntity);

    /**
     * 获取刷新token的URL
     *
     * @param refreshTokenEntity
     * @return RefreshUrl
     */
    public abstract String getRefreshUrl(AuthRefreshTokenEntity refreshTokenEntity);

    /**
     * 获取取消授权的URL
     *
     * @param revokeEntity
     * @return RevokeUrl
     */
    public abstract String getRevokeUrl(AuthRevokeEntity revokeEntity);

    /**
     * 获取state，如果为空， 则默认去当前日期的时间戳
     *
     * @param state state
     */
    protected String getRealState(String state) {
        return StringUtils.isEmpty(state) ? String.valueOf(System.currentTimeMillis()) : state;
    }
}
