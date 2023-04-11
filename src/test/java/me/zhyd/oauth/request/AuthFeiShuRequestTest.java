package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSON;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName AuthFeiShuRequestTest
 * @Author jackcheng(chen781142032@gamil.com)
 * @version 1.0
 * @since 1.16.5
 * @Date 2022/10/1 11:23
 * @Description 飞书第三方登录测试类 先执行authorize()方法获取state以及authorizeUrl，
 * 然后在浏览器中打开authorizeUrl，登录成功后会跳转到redirectUri，并且会携带code和state参数
 **/
public class AuthFeiShuRequestTest {

    @Test
    public void authorize() {
        AuthRequest request = new AuthFeishuRequest(AuthConfig.builder()
            .clientId("your App ID")
            .clientSecret("your App Secret")
            .redirectUri("you set redirect uri")
            .build());
        String state = AuthStateUtils.createState();
        System.out.println("state==" + state);
        String authorize = request.authorize(state);
        System.out.println("authorize==" + authorize);
    }

    @Test
    public void getAccessTokenAndUserInfo() {
        AuthRequest request = new AuthFeishuRequest(AuthConfig.builder()
            .clientId("your App ID")
            .clientSecret("your App Secret")
            .redirectUri("you set redirect uri")
            .build());

        String state = "your state";

        AuthCallback callback = AuthCallback.builder()
            .code("your code")
            .state(state)
            .build();
        AuthToken accessToken = ((AuthFeishuRequest) request).getAccessToken(callback);
        System.out.println("token==" + accessToken.getAccessToken());

        AuthUser userInfo = ((AuthFeishuRequest) request).getUserInfo(accessToken);
        System.out.println("userInfo==" + JSON.toJSONString(userInfo));

    }

    @Test
    public void login() {
        AuthRequest request = new AuthFeishuRequest(AuthConfig.builder()
            .clientId("your App ID")
            .clientSecret("your App Secret")
            .redirectUri("you set redirect uri")
            .build());

        String state = "your state";
        request.authorize(state);
        AuthCallback callback = AuthCallback.builder()
            .code("your code")
            .state(state)
            .build();
        AuthResponse response = request.login(callback);
        Assert.assertNotNull(response);
        AuthUser user = (AuthUser) response.getData();
        Assert.assertNotNull(user);
        System.out.println(JSON.toJSONString(user));
    }

}

