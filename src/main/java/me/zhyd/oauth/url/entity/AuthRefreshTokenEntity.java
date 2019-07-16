package me.zhyd.oauth.url.entity;

import lombok.Builder;
import lombok.Getter;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Getter
@Builder
public class AuthRefreshTokenEntity {

    private String clientId;
    private String refreshToken;
}
