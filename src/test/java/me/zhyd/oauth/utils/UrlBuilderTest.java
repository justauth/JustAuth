package me.zhyd.oauth.utils;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.request.AuthWeChatRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 * UrlBuilder测试类
 * </p>
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @date Created in 2019-07-18 16:36
 */
public class UrlBuilderTest {
    @Test
    public void testUrlBuilder() {
        AuthConfig config = AuthConfig.builder()
            .clientId("appid-110110110")
            .clientSecret("secret-110110110")
            .redirectUri("https://xkcoding.com")
            .state(AuthState.create(AuthSource.WECHAT))
            .build();
        String build = UrlBuilder.fromBaseUrl(AuthSource.WECHAT.authorize())
            .queryParam("appid", config.getClientId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("response_type", "code")
            .queryParam("scope", "snsapi_login")
            .queryParam("state", config.getState().concat("#wechat_redirect"))
            .build(false);
        AuthWeChatRequest request = new AuthWeChatRequest(config);
        String authorize = request.authorize();
        Assert.assertEquals(build, authorize);
        AuthState.delete(AuthSource.WECHAT);
    }
}
