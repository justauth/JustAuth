package me.zhyd.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 授权回调时的参数类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.8.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCallback implements Serializable {

    /**
     * 访问AuthorizeUrl后回调时带的参数code
     */
    private String code;

    /**
     * 访问AuthorizeUrl后回调时带的参数auth_code，该参数目前只使用于支付宝登录
     */
    private String auth_code;

    /**
     * 访问AuthorizeUrl后回调时带的参数state，用于和请求AuthorizeUrl前的state比较，防止CSRF攻击
     */
    private String state;

    /**
     * 华为授权登录接受code的参数名
     *
     * @since 1.10.0
     */
    private String authorization_code;

    /**
     * Twitter回调后返回的oauth_token
     *
     * @since 1.13.0
     */
    private String oauth_token;

    /**
     * Twitter回调后返回的oauth_verifier
     *
     * @since 1.13.0
     */
    private String oauth_verifier;

}
