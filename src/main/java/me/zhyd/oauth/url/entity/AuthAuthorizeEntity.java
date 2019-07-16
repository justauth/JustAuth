package me.zhyd.oauth.url.entity;

import lombok.Builder;
import lombok.Getter;
import me.zhyd.oauth.config.AuthConfig;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Getter
@Builder
public class AuthAuthorizeEntity {

    /**
     * JustAuth的配置类
     */
    private AuthConfig config;
}
