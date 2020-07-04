package me.zhyd.oauth.enums.scope;

/**
 * 各个平台 scope 类的统一接口
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.15.7
 */
public interface AuthScope {

    /**
     * 获取字符串 {@code scope}，对应为各平台实际使用的 {@code scope}
     *
     * @return String
     */
    String getScope();

    /**
     * 判断当前 {@code scope} 是否为各平台默认启用的
     *
     * @return boolean
     */
    boolean isDefault();
}
