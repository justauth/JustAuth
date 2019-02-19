package me.zhyd.oauth.config;

import lombok.Builder;
import lombok.Getter;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 14:03
 * @since 1.8
 */
@Getter
@Builder
public class AuthConfig {
    private String clientId;
    private String clientSecret;
    /**
     * 登录成功后的回调地址
     */
    private String redirectUri;
}
