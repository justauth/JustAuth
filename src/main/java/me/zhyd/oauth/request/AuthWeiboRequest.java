package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.GlobalAuthUtil;
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
    protected String getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getWeiboAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        String accessTokenStr = response.body();
        JSONObject accessTokenObject = JSONObject.parseObject(accessTokenStr);
        if (accessTokenObject.containsKey("error")) {
            throw new AuthException("Unable to get token from gitee using code [" + code + "]");
        }
        String accessToken = accessTokenObject.getString("access_token");
        String uid = accessTokenObject.getString("uid");
        return String.format("uid=%s&access_token=%s", uid, accessToken);
    }

    @Override
    protected AuthUser getUserInfo(String accessToken) {
        HttpResponse response = HttpRequest.get(UrlBuilder.getWeiboUserInfoUrl(accessToken))
                .header("Authorization", "OAuth2 " + accessToken)
                .header("API-RemoteIP", IpUtils.getIp())
                .execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        return AuthUser.builder()
                .username(object.getString("name"))
                .avatar(object.getString("profile_image_url"))
                .blog(StringUtils.isEmpty(object.getString("url")) ? "https://weibo.com/" + object.getString("profile_url") : object.getString("url"))
                .nickname(object.getString("screen_name"))
                .location(object.getString("location"))
                .remark(object.getString("description"))
                .gender(AuthUserGender.getRealGender(object.getString("gender")))
                .accessToken(GlobalAuthUtil.parseStringToMap(accessToken).get("access_token"))
                .source(AuthSource.WEIBO)
                .build();
    }
}
