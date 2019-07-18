package me.zhyd.oauth.request;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * Facebook登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthFacebookRequest extends AuthDefaultRequest {

    public AuthFacebookRequest(AuthConfig config) {
        super(config, AuthSource.FACEBOOK);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doPostAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());

        if (accessTokenObject.containsKey("error")) {
            throw new AuthException(accessTokenObject.getJSONObject("error").getString("message"));
        }

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .tokenType(accessTokenObject.getString("token_type"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = doGetUserInfo(authToken);
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        if (object.containsKey("error")) {
            throw new AuthException(object.getJSONObject("error").getString("message"));
        }
        String picture = null;
        if (object.containsKey("picture")) {
            JSONObject pictureObj = object.getJSONObject("picture");
            pictureObj = pictureObj.getJSONObject("data");
            if (null != pictureObj) {
                picture = pictureObj.getString("url");
            }
        }
        return AuthUser.builder()
            .uuid(object.getString("id"))
            .username(object.getString("name"))
            .nickname(object.getString("name"))
            .avatar(picture)
            .location(object.getString("locale"))
            .email(object.getString("email"))
            .gender(AuthUserGender.getRealGender(object.getString("gender")))
            .token(authToken)
            .source(AuthSource.FACEBOOK)
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
            .queryParam("access_token", authToken.getAccessToken())
            .queryParam("fields", "id,name,birthday,gender,hometown,email,devices,picture.width(400)")
            .build();
    }
}
