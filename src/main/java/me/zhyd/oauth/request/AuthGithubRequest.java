package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthConfigChecker;
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
public class AuthGithubRequest extends BaseAuthRequest {

    public AuthGithubRequest(AuthConfig config) {
        super(config);
    }

    @Override
    public void authorize(HttpServletResponse response) {
        String authorizeUrl = UrlBuilder.getGithubAuthorizeUrl(config.getClientId(), config.getRedirectUri());
        try {
            response.sendRedirect(authorizeUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String authorize() {
        return UrlBuilder.getGithubAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }

    @Override
    public AuthResponse login(String code) {
        String accessTokenUrl = UrlBuilder.getGithubAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        String accessTokenStr = response.body();
        String accessToken = accessTokenStr.split("&")[0];
        response = HttpRequest.get(UrlBuilder.getGithubUserInfoUrl(accessToken)).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        return AuthResponse.builder()
                .data(AuthUser.builder()
                        .username(object.getString("login"))
                        .avatar(object.getString("avatar_url"))
                        .blog(object.getString("blog"))
                        .nickname(object.getString("name"))
                        .company(object.getString("company"))
                        .location(object.getString("location"))
                        .email(object.getString("email"))
                        .remark(object.getString("bio"))
                        .source(AuthSource.GITHUB)
                        .build())
                .build();
    }
}
