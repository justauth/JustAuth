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
public class AuthUserInfoEntity {
    /**
     * 授权返回的token
     */
    private String accessToken;
    /**
     * 用户openId
     */
    private String openId;
    /**
     * 额外的属性
     */
    private String extra;
    /**
     * client key
     */
    private String clientId;
}
