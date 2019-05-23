package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.IpUtils;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;


/**
 * 微博登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthWeiboRequest extends BaseAuthRequest {

    public AuthWeiboRequest(AuthConfig config) {
        super(config, AuthSource.WEIBO);
    }

    @Override
    protected AuthToken getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getWeiboAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        String accessTokenStr = response.body();
        JSONObject accessTokenObject = JSONObject.parseObject(accessTokenStr);
        if (accessTokenObject.containsKey("error")) {
            throw new AuthException("Unable to get token from weibo using code [" + code + "]:" + accessTokenObject.getString("error_description"));
        }
        return AuthToken.builder()
                .accessToken(accessTokenObject.getString("access_token"))
                .uid(accessTokenObject.getString("uid"))
                .expireIn(accessTokenObject.getIntValue("remind_in"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        String uid = authToken.getUid();
        String oauthParam = String.format("uid=%s&access_token=%s", uid, accessToken);
        HttpResponse response = HttpRequest.get(UrlBuilder.getWeiboUserInfoUrl(oauthParam))
                .header("Authorization", "OAuth2 " + oauthParam)
                .header("API-RemoteIP", IpUtils.getIp())
                .execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error"));
        }
        return AuthUser.builder()
                .uuid(object.getString("id"))
                .username(object.getString("name"))
                .avatar(object.getString("profile_image_url"))
                .blog(StringUtils.isEmpty(object.getString("url")) ? "https://weibo.com/" + object.getString("profile_url") : object.getString("url"))
                .nickname(object.getString("screen_name"))
                .location(object.getString("location"))
                .remark(object.getString("description"))
                .gender(AuthUserGender.getRealGender(object.getString("gender")))
                .token(authToken)
                .source(AuthSource.WEIBO)
                .build();
    }
}
