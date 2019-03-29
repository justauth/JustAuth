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
 * @since 1.8
 */
public class AuthCodingRequest extends BaseAuthRequest {

    public AuthCodingRequest(AuthConfig config) {
        super(config, AuthSource.CODING);
    }

    @Override
    protected String getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getCodingAccessTokenUrl(config.getClientId(), config.getClientSecret(), code);
        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (accessTokenObject.getIntValue("code") != 0) {
            throw new AuthException("Unable to get token from coding using code [" + code + "]");
        }
        return accessTokenObject.getString("access_token");
    }

    @Override
    protected AuthUser getUserInfo(String accessToken) {
        HttpResponse response = HttpRequest.get(UrlBuilder.getCodingUserInfoUrl(accessToken)).execute();
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.getIntValue("code") != 0) {
            throw new AuthException(object.getString("msg"));
        }
        object = object.getJSONObject("data");
        return AuthUser.builder()
                .username(object.getString("name"))
                .avatar("https://coding.net/" + object.getString("avatar"))
                .blog("https://coding.net/" + object.getString("path"))
                .nickname(object.getString("name"))
                .company(object.getString("company"))
                .location(object.getString("location"))
                .gender(AuthUserGender.getRealGender(object.getString("sex")))
                .email(object.getString("email"))
                .remark(object.getString("slogan"))
                .accessToken(accessToken)
                .source(AuthSource.CODING)
                .build();
    }
}
