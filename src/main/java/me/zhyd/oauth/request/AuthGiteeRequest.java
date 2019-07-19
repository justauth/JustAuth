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

/**
 * Gitee登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthGiteeRequest extends AuthDefaultRequest {

    public AuthGiteeRequest(AuthConfig config) {
        super(config, AuthSource.GITEE);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doPostAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (accessTokenObject.containsKey("error")) {
            throw new AuthException("Unable to get token from gitee using code [" + authCallback.getCode() + "]: " + accessTokenObject);
        }
        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
            .scope(accessTokenObject.getString("scope"))
            .tokenType(accessTokenObject.getString("token_type"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = doGetUserInfo(authToken);
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        return AuthUser.builder()
            .uuid(object.getString("id"))
            .username(object.getString("login"))
            .avatar(object.getString("avatar_url"))
            .blog(object.getString("blog"))
            .nickname(object.getString("name"))
            .company(object.getString("company"))
            .location(object.getString("address"))
            .email(object.getString("email"))
            .remark(object.getString("bio"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(AuthSource.GITEE)
            .build();
    }
}
