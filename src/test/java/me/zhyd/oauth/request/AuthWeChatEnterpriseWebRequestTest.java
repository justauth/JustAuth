package me.zhyd.oauth.request;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthWeChatEnterpriseWebRequestTest {

    @Test
    public void authorize() {
        AuthRequest request = new AuthWeChatEnterpriseWebRequest(AuthConfig.builder()
            .clientId("a")
            .clientSecret("a")
            .redirectUri("https://www.justauth.cn")
            .build());
        System.out.println(request.authorize(AuthStateUtils.createState()));
    }
}
