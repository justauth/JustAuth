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
 * 自定义扩展的第三方request的测试类，用于演示具体的用法
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.12.0
 */
public class AuthExtendRequestTest {

    @Test
    public void authorize() {
        AuthRequest request = new AuthExtendRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());
        String authorize = request.authorize(AuthStateUtils.createState());
        System.out.println(authorize);
        Assert.assertNotNull(authorize);
    }

    @Test
    public void login() {
        AuthRequest request = new AuthExtendRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());

        String state = AuthStateUtils.createState();
        request.authorize(state);
        AuthCallback callback = AuthCallback.builder()
            .code("code")
            .state(state)
            .build();
        AuthResponse response = request.login(callback);
        Assert.assertNotNull(response);

        AuthUser user = (AuthUser) response.getData();
        Assert.assertNotNull(user);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void revoke() {
        AuthRequest request = new AuthExtendRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());

        AuthResponse response = request.revoke(AuthToken.builder().build());
        Assert.assertNotNull(response);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void refresh() {
        AuthRequest request = new AuthExtendRequest(AuthConfig.builder()
            .clientId("clientId")
            .clientSecret("clientSecret")
            .redirectUri("http://redirectUri")
            .build());

        AuthResponse response = request.refresh(AuthToken.builder().build());
        Assert.assertNotNull(response);
        System.out.println(JSON.toJSONString(response.getData()));

    }
}
