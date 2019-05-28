package me.zhyd.oauth.authorization;

import me.zhyd.oauth.config.AuthConfig;

/**
 * 授权接口，用来获取具体第三方平台的授权地址
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public interface Authorization {

    /**
     * 获取授权页面地址
     *
     * @param config 授权基础配置
     * @return 授权页面地址
     */
    String getAuthorizeUrl(AuthConfig config);
}
