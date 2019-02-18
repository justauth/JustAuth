package me.zhyd.oauth;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeiboRequest;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/18 13:10
 * @since 1.8
 */
public class AuthRequestTest {

    @Test
    public void giteeTest(HttpServletResponse response) {
        AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 自动跳转到授权页面
        authRequest.authorize(response);
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登陆后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void githubTest(HttpServletResponse response) {
        AuthRequest authRequest = new AuthGithubRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 自动跳转到授权页面
        authRequest.authorize(response);
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登陆后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void weiboTest(HttpServletResponse response) {
        AuthRequest authRequest = new AuthWeiboRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 自动跳转到授权页面
        authRequest.authorize(response);
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登陆后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }
}
