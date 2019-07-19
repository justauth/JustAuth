package me.zhyd.oauth.request;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.enums.AuthToutiaoErrorCode;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 今日头条登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.5
 * @since 1.5
 */
public class AuthToutiaoRequest extends AuthDefaultRequest {

    public AuthToutiaoRequest(AuthConfig config) {
        super(config, AuthSource.TOUTIAO);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doGetAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());

        if (accessTokenObject.containsKey("error_code")) {
            throw new AuthException(AuthToutiaoErrorCode.getErrorCode(accessTokenObject.getIntValue("error_code"))
                .getDesc());
        }

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .openId(accessTokenObject.getString("open_id"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse userResponse = doGetUserInfo(authToken);

        JSONObject userProfile = JSONObject.parseObject(userResponse.body());

        if (userProfile.containsKey("error_code")) {
            throw new AuthException(AuthToutiaoErrorCode.getErrorCode(userProfile.getIntValue("error_code")).getDesc());
        }

        JSONObject user = userProfile.getJSONObject("data");

        boolean isAnonymousUser = user.getIntValue("uid_type") == 14;
        String anonymousUserName = "匿名用户";

        return AuthUser.builder()
            .uuid(user.getString("uid"))
            .username(isAnonymousUser ? anonymousUserName : user.getString("screen_name"))
            .nickname(isAnonymousUser ? anonymousUserName : user.getString("screen_name"))
            .avatar(user.getString("avatar_url"))
            .remark(user.getString("description"))
            .gender(AuthUserGender.getRealGender(user.getString("gender")))
            .token(authToken)
            .source(AuthSource.TOUTIAO)
            .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("response_type", "code")
            .queryParam("client_key", config.getClientId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("state", getRealState(config.getState()))
            .queryParam("auth_only", 1)
            .queryParam("display", 0)
            .build();
    }

    /**
     * 返回获取accessToken的url
     *
     * @param code
     * @return 返回获取accessToken的url
     */
    @Override
    protected String accessTokenUrl(String code) {
        return UrlBuilder.fromBaseUrl(source.accessToken())
            .queryParam("code", code)
            .queryParam("client_key", config.getClientId())
            .queryParam("client_secret", config.getClientSecret())
            .queryParam("grant_type", "authorization_code")
            .build();
    }

    /**
     * 返回获取userInfo的url
     *
     * @param authToken
     * @return 返回获取userInfo的url
     */
    @Override
    protected String userInfoUrl(AuthToken authToken) {
        return UrlBuilder.fromBaseUrl(source.userInfo())
            .queryParam("client_key", config.getClientId())
            .queryParam("access_token", authToken.getAccessToken())
            .build();
    }
}
