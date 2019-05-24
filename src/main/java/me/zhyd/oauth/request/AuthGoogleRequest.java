package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * Google登录
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @version 1.3
 * @since 1.3
 */
public class AuthGoogleRequest extends BaseAuthRequest {

    public AuthGoogleRequest(AuthConfig config) {
        super(config, AuthSource.GOOGLE);
    }

    @Override
    protected AuthToken getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getGoogleAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config
                .getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        JSONObject object = JSONObject.parseObject(response.body());

        if (object.containsKey("error") || object.containsKey("error_description")) {
            throw new AuthException("get google access_token has error:[" + object.getString("error") + "], error_description:[" + object
                    .getString("error_description") + "]");
        }

        return AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .expireIn(object.getIntValue("expires_in"))
                .scope(object.getString("scope"))
                .tokenType(object.getString("token_type"))
                .idToken(object.getString("id_token"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getIdToken();
        HttpResponse response = HttpRequest.get(UrlBuilder.getGoogleUserInfoUrl(accessToken)).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        return AuthUser.builder()
                .uuid(object.getString("sub"))
                .username(object.getString("name"))
                .avatar(object.getString("picture"))
                .nickname(object.getString("name"))
                .location(object.getString("locale"))
                .email(object.getString("email"))
                .token(authToken)
                .source(AuthSource.GOOGLE)
                .build();
    }
}
