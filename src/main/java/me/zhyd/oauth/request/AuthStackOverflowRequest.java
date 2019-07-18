package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.UrlBuilder;

import static me.zhyd.oauth.config.AuthSource.STACK_OVERFLOW;
import static me.zhyd.oauth.utils.GlobalAuthUtil.parseQueryToMap;

/**
 * Stack Overflow登录
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @version 1.9.0
 * @since 1.9.0
 */
public class AuthStackOverflowRequest extends AuthDefaultRequest {

    public AuthStackOverflowRequest(AuthConfig config) {
        super(config, STACK_OVERFLOW);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = accessTokenUrl(authCallback.getCode());
        HttpResponse response = HttpRequest.post(accessTokenUrl)
            .contentType("application/x-www-form-urlencoded")
            .form(parseQueryToMap(accessTokenUrl))
            .execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (!response.isOk()) {
            throw new AuthException("Unable to get token from Stack Overflow using code [" + authCallback.getCode() + "]: " + accessTokenObject);
        }

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .expireIn(accessTokenObject.getIntValue("expires"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String userInfoUrl = UrlBuilder.fromBaseUrl(userInfoUrl(authToken))
            .queryParam("site", "stackoverflow")
            .queryParam("key", this.config.getStackOverflowKey())
            .build();
        HttpResponse response = HttpRequest.get(userInfoUrl).execute();
        JSONObject userObj = JSONObject.parseObject(response.body()).getJSONArray("items").getJSONObject(0);

        return AuthUser.builder()
            .uuid(userObj.getString("user_id"))
            .avatar(userObj.getString("profile_image"))
            .location(userObj.getString("location"))
            .nickname(userObj.getString("display_name"))
            .blog(userObj.getString("website_url"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(STACK_OVERFLOW)
            .build();
    }
}
