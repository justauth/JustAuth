package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/23 15:48
 * @since 1.8
 */
public class AuthOschinaRequest extends BaseAuthRequest {

    public AuthOschinaRequest(AuthConfig config) {
        super(config, AuthSource.OSCHINA);
    }

    @Override
    protected String getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getOschinaAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (accessTokenObject.containsKey("error")) {
            throw new AuthException("Unable to get token from oschina using code [" + code + "]");
        }
        return accessTokenObject.getString("access_token");
    }

    @Override
    protected AuthUser getUserInfo(String accessToken) {
        HttpResponse response = HttpRequest.get(UrlBuilder.getOschinaUserInfoUrl(accessToken)).execute();
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }
        return AuthUser.builder()
                .username(object.getString("name"))
                .nickname(object.getString("name"))
                .avatar(object.getString("avatar"))
                .blog(object.getString("url"))
                .location(object.getString("location"))
                .gender(AuthUserGender.getRealGender(object.getString("gender")))
                .email(object.getString("email"))
                .accessToken(accessToken)
                .source(AuthSource.OSCHINA)
                .build();
    }
}
