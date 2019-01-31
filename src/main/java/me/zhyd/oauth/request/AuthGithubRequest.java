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
public class AuthGithubRequest implements AuthRequest {

    @Override
    public void authorize(HttpServletResponse response) {
        if (!AuthConfigChecker.isSupportedGithub()) {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
        String authorizeUrl = UrlBuilder.getGithubAuthorizeUrl(AuthConfig.githubClientId, AuthConfig.githubRedirectUri);
        try {
            response.sendRedirect(authorizeUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String authorize() {
        if (!AuthConfigChecker.isSupportedGithub()) {
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
        return UrlBuilder.getGithubAuthorizeUrl(AuthConfig.githubClientId, AuthConfig.githubRedirectUri);
    }

    @Override
    public AuthResponse login(String code) {
        if (!AuthConfigChecker.isSupportedGithub()) {
            return AuthResponse.builder()
                    .code(ResponseStatus.UNSUPPORTED.getCode())
                    .msg(ResponseStatus.UNSUPPORTED.getMsg())
                    .build();
        }
        String accessTokenUrl = UrlBuilder.getGithubAccessTokenUrl(AuthConfig.githubClientId, AuthConfig.githubClientSecret, code, AuthConfig.githubRedirectUri);
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
