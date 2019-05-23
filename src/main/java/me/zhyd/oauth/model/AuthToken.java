package me.zhyd.oauth.model;

import lombok.Builder;
import lombok.Data;

/**
 * 授权所需的token
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Data
@Builder
public class AuthToken {
    private String accessToken;
    private int expireIn;
    private String refreshToken;
    private String uid;
    private String openId;
    private String accessCode;

    /**
     * Google附带属性
     */
    private String scope;
    private String tokenType;
    private String idToken;

}
