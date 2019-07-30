package me.zhyd.oauth.model;

import lombok.Getter;
import lombok.Setter;
import me.zhyd.oauth.cache.AuthStateCache;

/**
 * 授权回调时的参数类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.8.0
 */
@Getter
@Setter
public class AuthCallback {

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
     * 内置的检验state合法性的方法
     *
     * @return true： state正常；false：state不正常，可能授权时间过长导致state失效
     * @since 1.9.3
     */
    public boolean checkState() {
        return AuthStateCache.containsKey(this.state);
    }
}
