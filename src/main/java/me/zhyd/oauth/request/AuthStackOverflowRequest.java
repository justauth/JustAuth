package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.url.AuthStackOverflowUrlBuilder;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.util.HashMap;
import java.util.Map;

import static me.zhyd.oauth.config.AuthSource.STACK_OVERFLOW;

/**
 * Stack Overflow登录
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @version 1.9.0
 * @since 1.9.0
 */
public class AuthStackOverflowRequest extends AuthDefaultRequest {

    public AuthStackOverflowRequest(AuthConfig config) {
        super(config, STACK_OVERFLOW, new AuthStackOverflowUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = this.urlBuilder.getAccessTokenUrl(authCallback.getCode());
        HttpResponse response = HttpRequest.post(accessTokenUrl)
            .contentType("application/x-www-form-urlencoded")
            .form(buildBody(accessTokenUrl))
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
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder()
            .accessToken(accessToken)
            .build())).execute();
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

    private Map<String, Object> buildBody(String accessTokenUrl) {
        Map<String, Object> paramMap = new HashMap<>();
        HttpUtil.decodeParamMap(accessTokenUrl, "UTF-8").forEach(paramMap::put);
        return paramMap;
    }

}
