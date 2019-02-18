package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.AuthConfigChecker;
import me.zhyd.oauth.utils.IpUtils;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 16:31
 * @since 1.8
 */
public class AuthWeiboRequest extends BaseAuthRequest implements AuthRequest {

    public AuthWeiboRequest(AuthConfig config) {
        super(config);
    }

    @Override
    public void authorize(HttpServletResponse response) {
        if (!AuthConfigChecker.isSupportedWeibo()) {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
        String authorizeUrl = UrlBuilder.getWeiboAuthorizeUrl(config.getClientId(), config.getRedirectUri());
        try {
            response.sendRedirect(authorizeUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String authorize() {
        if (!AuthConfigChecker.isSupportedWeibo()) {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
        return UrlBuilder.getWeiboAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }

    @Override
    public AuthResponse login(String code) {
        if (!AuthConfigChecker.isSupportedWeibo()) {
            return AuthResponse.builder()
                    .code(ResponseStatus.UNSUPPORTED.getCode())
                    .msg(ResponseStatus.UNSUPPORTED.getMsg())
                    .build();
        }
        String accessTokenUrl = UrlBuilder.getWeiboAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        String accessTokenStr = response.body();
        JSONObject accessTokenObject = JSONObject.parseObject(accessTokenStr);
        if (accessTokenObject.containsKey("error")) {
            return AuthResponse.builder()
                    .code(500)
                    .msg("Unable to get token from gitee using code [" + code + "]")
                    .build();
        }
        String accessToken = accessTokenObject.getString("access_token");
        String uid = accessTokenObject.getString("uid");
        response = HttpRequest.get(UrlBuilder.getWeiboUserInfoUrl(uid, accessToken))
                .header("Authorization", "OAuth2 " + accessToken)
                .header("API-RemoteIP", IpUtils.getIp())
                .execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        return AuthResponse.builder()
                .data(AuthUser.builder()
                        .username(object.getString("name"))
                        .avatar(object.getString("profile_image_url"))
                        .blog(StringUtils.isEmpty(object.getString("url")) ? "https://weibo.com/" + object.getString("profile_url") : object.getString("url"))
                        .nickname(object.getString("screen_name"))
                        .location(object.getString("location"))
                        .remark(object.getString("description"))
                        .gender(AuthUserGender.getRealGender(object.getString("gender")))
                        .source(AuthSource.GITHUB)
                        .build())
                .build();
    }
}
