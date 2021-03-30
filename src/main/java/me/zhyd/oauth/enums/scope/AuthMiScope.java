package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 小米平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthMiScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    profile("user/profile", "获取用户的基本信息", true),
    OPENID("user/openIdV2", "获取用户的OpenID", true),
    PHONE_EMAIL("user/phoneAndEmail", "获取用户的手机号和邮箱", true);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
