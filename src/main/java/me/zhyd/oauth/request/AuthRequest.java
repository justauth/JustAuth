package me.zhyd.oauth.request;

import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 15:45
 * @since 1.8
 */
public interface AuthRequest {

    /**
     * 自动跳转到认证页面
     *
     * @param response response
     */
    default void authorize(HttpServletResponse response) {
        throw new AuthException(ResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 返回认证url，可自行跳转页面
     */
    default String authorize() {
        throw new AuthException(ResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 第三方登录
     *
     * @param code 通过authorize换回的code
     * @return 返回登录成功后的用户信息
     */
    default AuthResponse login(String code) {
        throw new AuthException(ResponseStatus.NOT_IMPLEMENTED);
    }
}
