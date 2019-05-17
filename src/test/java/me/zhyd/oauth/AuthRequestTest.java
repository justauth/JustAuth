package me.zhyd.oauth;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.*;
import org.junit.Test;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthRequestTest {

    @Test
    public void giteeTest() {
        AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void githubTest() {
        AuthRequest authRequest = new AuthGithubRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void weiboTest() {
        AuthRequest authRequest = new AuthWeiboRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void dingdingTest() {
        AuthRequest authRequest = new AuthDingTalkRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        String url = authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void baiduTest() {
        AuthRequest authRequest = new AuthBaiduRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        String url = authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void codingTest() {
        AuthRequest authRequest = new AuthCodingRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        String url = authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void tencentCloudTest() {
        AuthRequest authRequest = new AuthTencentCloudRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        String url = authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void oschinaTest() {
        AuthRequest authRequest = new AuthOschinaRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        String url = authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        authRequest.login("code");
    }

    @Test
    public void wechatTest() {
        AuthRequest authRequest = new AuthWeChatRequest(AuthConfig.builder()
                .clientId("clientId")
                .clientSecret("clientSecret")
                .redirectUri("redirectUri")
                .build());
        // 返回授权页面，可自行调整
        String url = authRequest.authorize();
        // 授权登录后会返回一个code，用这个code进行登录
        AuthResponse login = authRequest.login("code");
    }
}
