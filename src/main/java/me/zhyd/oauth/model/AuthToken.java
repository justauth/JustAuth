package me.zhyd.oauth.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 15:43
 * @since 1.8
 */
@Data
@Builder
public class AuthToken {
    private String accessToken;
    private String expireIn;
    private String refreshToken;
    private String uid;
}
