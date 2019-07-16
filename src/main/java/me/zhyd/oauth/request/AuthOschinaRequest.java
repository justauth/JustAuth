package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.url.OschinaUrlBuilder;
import me.zhyd.oauth.url.entity.AuthAccessTokenEntity;
import me.zhyd.oauth.url.entity.AuthAuthorizeEntity;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

/**
 * oschina登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthOschinaRequest extends BaseAuthRequest {

    public AuthOschinaRequest(AuthConfig config) {
        super(config, AuthSource.OSCHINA, new OschinaUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = this.urlBuilder.getAccessTokenUrl(AuthAccessTokenEntity.builder()
                .config(config)
                .code(authCallback.getCode())
                .build());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (accessTokenObject.containsKey("error")) {
            throw new AuthException("Unable to get token from oschina using code [" + authCallback.getCode() + "]");
        }
        return AuthToken.builder()
                .accessToken(accessTokenObject.getString("access_token"))
                .refreshToken(accessTokenObject.getString("refresh_token"))
                .uid(accessTokenObject.getString("uid"))
                .expireIn(accessTokenObject.getIntValue("expires_in"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder()
                .accessToken(accessToken)
                .build())).execute();
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }
        return AuthUser.builder()
                .uuid(object.getString("id"))
                .username(object.getString("name"))
                .nickname(object.getString("name"))
                .avatar(object.getString("avatar"))
                .blog(object.getString("url"))
                .location(object.getString("location"))
                .gender(AuthUserGender.getRealGender(object.getString("gender")))
                .email(object.getString("email"))
                .token(authToken)
                .source(AuthSource.OSCHINA)
                .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return this.urlBuilder.getAuthorizeUrl(AuthAuthorizeEntity.builder()
                .config(config)
                .build());
    }
}
