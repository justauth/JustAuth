package me.zhyd.oauth.url;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.ResponseStatus;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;
import me.zhyd.oauth.utils.StringUtils;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public abstract class AbstractUrlBuilder {

    protected AuthConfig config;

    /**
     * 获取AccessToken的URL
     *
     * @param code 第三方平台返回的code
     * @return AccessTokenUrl
     */
    public abstract String getAccessTokenUrl(String code);

    /**
     * 获取用户信息的URL
     *
     * @param userInfoEntity 传递生成 UserInfoUrl 必须的参数
     * @return UserInfoUrl
     */
    public abstract String getUserInfoUrl(AuthUserInfoEntity userInfoEntity);

    /**
     * 获取跳转授权页面的URL
     *
     * @return AuthorizeUrl
     */
    public abstract String getAuthorizeUrl();

    /**
     * 获取刷新token的URL
     *
     * @param refreshToken 授权后取得的refresh token
     * @return RefreshUrl
     */
    public abstract String getRefreshUrl(String refreshToken);

    /**
     * 获取取消授权的URL
     *
     * @param accessToken 授权后的token
     * @return RevokeUrl
     */
    public abstract String getRevokeUrl(String accessToken);

    /**
     * 获取openId的地址，目前只有qq平台需要，故不需要子类强制重写
     *
     * @param accessToken 用户授权后返回的accesstoken
     * @param unionid     是否需要申请unionid，目前只针对qq登录
     *                    注：qq授权登录时，获取unionid需要单独发送邮件申请权限。如果个人开发者账号中申请了该权限，可以将该值置为true，在获取openId时就会同步获取unionId
     *                    参考链接：http://wiki.connect.qq.com/unionid%E4%BB%8B%E7%BB%8D
     * @return openIdUrl
     */
    public String getOpenIdUrl(String accessToken, boolean unionid) {
        throw new AuthException(ResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 获取state，如果为空， 则默认去当前日期的时间戳
     *
     * @param state 原始的state
     * @return 返回不为null的state
     */
    protected String getRealState(String state) {
        return StringUtils.isEmpty(state) ? String.valueOf(System.currentTimeMillis()) : state;
    }

    public void setAuthConfig(AuthConfig config) {
        this.config = config;
    }
}
