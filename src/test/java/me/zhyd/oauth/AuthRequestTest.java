package me.zhyd.oauth;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.*;
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
    public void giteeTest() {
        HttpServletResponse response = null;
        AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 自动跳转到授权页面
        authRequest.authorize(response);
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void githubTest() {
        HttpServletResponse response = null;
        AuthRequest authRequest = new AuthGithubRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 自动跳转到授权页面
        authRequest.authorize(response);
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void weiboTest() {
        HttpServletResponse response = null;
        AuthRequest authRequest = new AuthWeiboRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 自动跳转到授权页面
        authRequest.authorize(response);
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void dingdingTest() {
        HttpServletResponse response = null;
        AuthRequest authRequest = new AuthDingTalkRequest(AuthConfig.builder()
                .clientId("dingoa2q6o3fomfk6vdqzy")
                .clientSecret("d5w75-R-yNtQsq_Ya_r50gOsKOy9WlmrlUOJkUJXKvsQp7NDPRHsj0epJriiN3yO")
                .redirectUri("http://61.149.7.121:8443/oauth/dingtalk/callback")
                .build());
        // 自动跳转到授权页面
//        authRequest.authorize(response);
        // 返回授权页面，可自行调整
        String url = authRequest.authorize();
        System.out.println(url);
        // 授权登录后会返回一个code，用这个code进行登录
//        authRequest.login("code");
    }
}
