package me.zhyd.oauth.request;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.junit.Assert;
import org.junit.Test;

public class AuthWeChatMpRequestTest {

    @Test
    public void authorize() {

        AuthRequest request = new AuthWeChatMpRequest(AuthConfig.builder()
            .clientId("a")
            .clientSecret("a")
            .redirectUri("https://www.justauth.cn")
            .build());
        System.out.println(request.authorize(AuthStateUtils.createState()));
    }

    @Test
    public void authorize1() {
        AuthRequest request = AuthDefaultSource.getAuthSource("wechat_mp").getAuthRequestInstance(AuthConfig.builder()
            .clientId("a")
            .clientSecret("a")
            .redirectUri("https://www.justauth.cn")
            .build());
        Assert.assertTrue(request instanceof AuthWeChatMpRequest);
        System.out.println(request.authorize(AuthStateUtils.createState()));
    }
}
