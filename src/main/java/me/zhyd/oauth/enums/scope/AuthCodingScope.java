package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Coding平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthCodingScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    USER("user", "读取用户的基本信息", false),
    USER_EMAIL("user:email", "读取用户的邮件", false),
    USER_PHONE("user:phone", "读取用户的手机号", false),
    PROJECT("project", "授权项目信息、项目列表，仓库信息，公钥列表、成员", false),
    PROJECT_DEPOT("project:depot", "完整的仓库控制权限", false),
    PROJECT_WIKI("project:wiki", "授权读取与操作 wiki", false),
    ;
    private final String scope;
    private final String description;
    private final boolean isDefault;

}
