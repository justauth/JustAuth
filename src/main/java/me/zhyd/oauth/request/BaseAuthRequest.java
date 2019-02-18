package me.zhyd.oauth.request;

import lombok.Data;
import me.zhyd.oauth.config.AuthConfig;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/18 13:11
 * @since 1.8
 */
@Data
public abstract class BaseAuthRequest {
    protected AuthConfig config;

    public BaseAuthRequest(AuthConfig config) {
        this.config = config;
    }
}
