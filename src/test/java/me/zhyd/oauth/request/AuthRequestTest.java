package me.zhyd.oauth.request;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.*;
import org.junit.Test;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 */
public class AuthRequestTest {

    @Test
    public void giteeTest() {
        AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        authRequest.login(new AuthCallback());
    }

    @Test
    public void githubTest() {
        AuthRequest authRequest = new AuthGithubRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        authRequest.login(new AuthCallback());
    }

    @Test
    public void weiboTest() {
        AuthRequest authRequest = new AuthWeiboRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        authRequest.login(new AuthCallback());
    }

    @Test
    public void dingdingTest() {
        AuthRequest authRequest = new AuthDingTalkRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        authRequest.login(new AuthCallback());
    }

    @Test
    public void baiduTest() {
        AuthRequest authRequest = new AuthBaiduRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        authRequest.login(new AuthCallback());
    }

    @Test
    public void codingTest() {
        AuthRequest authRequest = new AuthCodingRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        authRequest.login(new AuthCallback());
    }

    @Test
    public void tencentCloudTest() {
        AuthRequest authRequest = new AuthTencentCloudRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        authRequest.login(new AuthCallback());
    }

    @Test
    public void oschinaTest() {
        AuthRequest authRequest = new AuthOschinaRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        authRequest.login(new AuthCallback());
    }

    @Test
    public void alipayTest() {
        AuthRequest authRequest = new AuthAlipayRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .alipayPublicKey("publicKey")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void qqTest() {
        AuthRequest authRequest = new AuthQqRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void wechatTest() {
        AuthRequest authRequest = new AuthWeChatRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void taobaoTest() {
        AuthRequest authRequest = new AuthTaobaoRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void googleTest() {
        AuthRequest authRequest = new AuthGoogleRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void facebookTest() {
        AuthRequest authRequest = new AuthFacebookRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("https://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void douyinTest() {
        AuthRequest authRequest = new AuthDouyinRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void linkedinTest() {
        AuthRequest authRequest = new AuthLinkedinRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void microsoftTest() {
        AuthRequest authRequest = new AuthMicrosoftRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void miTest() {
        AuthRequest authRequest = new AuthMiRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void toutiaoTest() {
        AuthRequest authRequest = new AuthToutiaoRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void huaweiTest() {
        AuthRequest authRequest = new AuthHuaweiRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }

    @Test
    public void weChatEnterpriseTest() {
        AuthRequest authRequest = new AuthWeChatEnterpriseRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .agentId("agentId")
            .build());
        // 返回授权页面，可自行跳转
        authRequest.authorize("state");
        // 授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的入参
        // 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
        AuthResponse login = authRequest.login(new AuthCallback());
    }
}
