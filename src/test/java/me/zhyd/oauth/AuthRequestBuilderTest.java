package me.zhyd.oauth;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.config.AuthExtendSource;
import me.zhyd.oauth.request.*;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.junit.Assert;
import org.junit.Test;

public class AuthRequestBuilderTest {

    /**
     * 示例：一般场景下通过 AuthRequestBuilder 构建 AuthRequest
     */
    @Test
    public void build2() {
        AuthRequest authRequest = AuthRequestBuilder.builder()
            .source("github")
            .authConfig(AuthConfig.builder()
                .clientId("a")
                .clientSecret("a")
                .redirectUri("https://www.justauth.cn")
                .build())
            .build();
        Assert.assertTrue(authRequest instanceof AuthGithubRequest);
        System.out.println(authRequest.authorize(AuthStateUtils.createState()));
    }

    /**
     * 示例：AuthConfig 需要动态获取的场景下通过 AuthRequestBuilder 构建 AuthRequest
     */
    @Test
    public void build() {
        AuthRequest authRequest = AuthRequestBuilder.builder()
            .source("gitee")
            .authConfig((source) -> {
                // 通过 source 动态获取 AuthConfig
                // 此处可以灵活的从 sql 中取配置也可以从配置文件中取配置
                return AuthConfig.builder()
                    .clientId(source)
                    .clientSecret(source)
                    .redirectUri("https://www.justauth.cn/" + source)
                    .build();
            })
            .build();
        Assert.assertTrue(authRequest instanceof AuthGiteeRequest);
        System.out.println(authRequest.authorize(AuthStateUtils.createState()));
    }

    /**
     * 示例：自定义实现的 AuthRequest，通过 AuthRequestBuilder 构建 AuthRequest
     */
    @Test
    public void build3() {
        AuthRequest authRequest = AuthRequestBuilder.builder()
            // 关键点：将自定义的 AuthSource 配置上
            .extendSource(AuthExtendSource.values())
            .source("other")
            .authConfig(AuthConfig.builder()
                .clientId("a")
                .clientSecret("a")
                .redirectUri("https://www.justauth.cn")
                .build())
            .build();
        Assert.assertTrue(authRequest instanceof AuthExtendRequest);
        System.out.println(authRequest.authorize(AuthStateUtils.createState()));
    }

    /**
     * 测试不同平台
     */
    @Test
    public void build4() {
        AuthConfig config = AuthConfig.builder()
            .clientId("a")
            .clientSecret("a")
            .redirectUri("https://www.justauth.cn")
            .authServerId("asd")
            .agentId("asd")
            .domainPrefix("asd")
            .stackOverflowKey("asd")
            .deviceId("asd")
            .clientOsType(3)
            .kid("kid")
            .teamId("teamid")
            .ignoreCheckState(true)
            .ignoreCheckRedirectUri(true)
            .build();

        for (AuthDefaultSource value : AuthDefaultSource.values()) {
            switch (value) {
                case TWITTER:
                    System.out.println(value.getTargetClass());
                    System.out.println("忽略 twitter");
                    continue;
                case ALIPAY: {
                    // 单独给Alipay执行测试
                    AuthRequest authRequest = new AuthAlipayRequest(config, "asd");
                    System.out.println(value.getTargetClass());
                    System.out.println(authRequest.authorize(AuthStateUtils.createState()));
                    continue;
                }
                case WECHAT_MINI_PROGRAM: {
                    // 小程序不支持获取调用 authorize
                    AuthRequest authRequest = new AuthWechatMiniProgramRequest(config);
                    System.out.println(value.getTargetClass());
                    continue;
                }
                default:
                    AuthRequest authRequest = AuthRequestBuilder.builder()
                        .source(value.getName())
                        .authConfig(config)
                        .build();
                    System.out.println(value.getTargetClass());
                    System.out.println(authRequest.authorize(AuthStateUtils.createState()));
            }
        }
    }
}
