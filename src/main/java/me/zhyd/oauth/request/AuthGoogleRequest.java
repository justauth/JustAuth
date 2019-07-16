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
import me.zhyd.oauth.url.GoogleUrlBuilder;
import me.zhyd.oauth.url.entity.AuthAccessTokenEntity;
import me.zhyd.oauth.url.entity.AuthAuthorizeEntity;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

/**
 * Google登录
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @version 1.3
 * @since 1.3
 */
public class AuthGoogleRequest extends BaseAuthRequest {

    public AuthGoogleRequest(AuthConfig config) {
        super(config, AuthSource.GOOGLE, new GoogleUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = this.urlBuilder.getAccessTokenUrl(AuthAccessTokenEntity.builder()
                .config(config)
                .code(authCallback.getCode())
                .build());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());

        if (accessTokenObject.containsKey("error") || accessTokenObject.containsKey("error_description")) {
            throw new AuthException("get google access_token has error:[" + accessTokenObject.getString("error") + "], error_description:[" + accessTokenObject
                    .getString("error_description") + "]");
        }

        return AuthToken.builder()
                .accessToken(accessTokenObject.getString("access_token"))
                .expireIn(accessTokenObject.getIntValue("expires_in"))
                .scope(accessTokenObject.getString("scope"))
                .tokenType(accessTokenObject.getString("token_type"))
                .idToken(accessTokenObject.getString("id_token"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getIdToken();
        HttpResponse response = HttpRequest.get(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder()
                .accessToken(accessToken)
                .build())).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        return AuthUser.builder()
                .uuid(object.getString("sub"))
                .username(object.getString("name"))
                .avatar(object.getString("picture"))
                .nickname(object.getString("name"))
                .location(object.getString("locale"))
                .email(object.getString("email"))
                .gender(AuthUserGender.UNKNOW)
                .token(authToken)
                .source(AuthSource.GOOGLE)
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
