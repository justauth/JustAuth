package me.zhyd.oauth.request;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.GlobalAuthUtil;

import java.util.Map;

/**
 * Github登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.0.0
 */
public class AuthGithubRequest extends AuthDefaultRequest {

    public AuthGithubRequest(AuthConfig config) {
        super(config, AuthDefaultSource.GITHUB);
    }

    public AuthGithubRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.GITHUB, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doPostAuthorizationCode(authCallback.getCode());
        Map<String, String> res = GlobalAuthUtil.parseStringToMap(response.body());

        this.checkResponse(res.containsKey("error"), res.get("error_description"));

        return AuthToken.builder()
            .accessToken(res.get("access_token"))
            .scope(res.get("scope"))
            .tokenType(res.get("token_type"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = doGetUserInfo(authToken);
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object.containsKey("error"), object.getString("error_description"));

        return AuthUser.builder()
            .uuid(object.getString("id"))
            .username(object.getString("login"))
            .avatar(object.getString("avatar_url"))
            .blog(object.getString("blog"))
            .nickname(object.getString("name"))
            .company(object.getString("company"))
            .location(object.getString("location"))
            .email(object.getString("email"))
            .remark(object.getString("bio"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(source.toString())
            .build();
    }

    private void checkResponse(boolean error, String error_description) {
        if (error) {
            throw new AuthException(error_description);
        }
    }

}
