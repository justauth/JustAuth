package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Gitee 平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthProginnScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    BASIC("basic", "访问用户的基本信息", true),
    /**
     * 以上 scope 需要单独向程序员客栈平台申请，否则不可使用
     */
    email("email", "获取用户的邮箱", false),
    realname("realname", "获取用户的真实姓名", false),
    cellphone("cellphone", "获取用户的手机号码", false),
   ;

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
