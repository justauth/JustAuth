package me.zhyd.oauth.request;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 腾讯云登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthTencentCloudRequest extends AuthDefaultRequest {

    public AuthTencentCloudRequest(AuthConfig config) {
        super(config, AuthSource.TENCENT_CLOUD);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doGetAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (accessTokenObject.getIntValue("code") != 0) {
            throw new AuthException("Unable to get token from tencent cloud using code [" + authCallback.getCode() + "]: " + accessTokenObject
                .get("msg"));
        }
        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = doGetUserInfo(authToken);
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.getIntValue("code") != 0) {
            throw new AuthException(object.getString("msg"));
        }
        object = object.getJSONObject("data");
        return AuthUser.builder()
            .uuid(object.getString("id"))
            .username(object.getString("name"))
            .avatar("https://dev.tencent.com/" + object.getString("avatar"))
            .blog("https://dev.tencent.com/" + object.getString("path"))
            .nickname(object.getString("name"))
            .company(object.getString("company"))
            .location(object.getString("location"))
            .gender(AuthUserGender.getRealGender(object.getString("sex")))
            .email(object.getString("email"))
            .remark(object.getString("slogan"))
            .token(authToken)
            .source(AuthSource.TENCENT_CLOUD)
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
            .queryParam("client_id", config.getClientId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("scope", "user")
            .queryParam("state", getRealState(config.getState()))
            .build();
    }
}
