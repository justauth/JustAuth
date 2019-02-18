package me.zhyd.oauth.request;

import me.zhyd.oauth.config.AuthConfig;
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
     * @param config   授权的配置，对应不同平台
     * @param response response
     */
    default void authorize(AuthConfig config, HttpServletResponse response) {
        throw new AuthException(ResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @param config 授权的配置，对应不同平台
     */
    default String authorize(AuthConfig config) {
        throw new AuthException(ResponseStatus.NOT_IMPLEMENTED);
    }

    /**
     * 第三方登录
     *
     * @param config 授权的配置，对应不同平台
     * @param code   通过authorize换回的code
     * @return 返回登陆成功后的用户信息
     */
    default AuthResponse login(AuthConfig config, String code) {
        throw new AuthException(ResponseStatus.NOT_IMPLEMENTED);
    }
}
