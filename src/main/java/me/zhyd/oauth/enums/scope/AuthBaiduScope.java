package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 边度平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthBaiduScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    BASIC("basic", "用户基本权限，可以获取用户的基本信息 。", true),
    SUPER_MSG("super_msg", "往用户的百度首页上发送消息提醒，相关API任何应用都能使用，但要想将消息提醒在百度首页显示，需要第三方在注册应用时额外填写相关信息。", false),
    NETDISK("netdisk", "获取用户在个人云存储中存放的数据。", false),
    PUBLIC("public", "可以访问公共的开放API。", false),
    HAO123("hao123", "可以访问Hao123 提供的开放API接口。该权限需要申请开通，请将具体的理由和用途发邮件给tuangou@baidu.com。", false);

    private String scope;
    private String description;
    private boolean isDefault;

    public static List<AuthScope> getDefaultScopes() {
        AuthBaiduScope[] scopes = AuthBaiduScope.values();
        List<AuthScope> defaultScopes = new ArrayList<>();
        for (AuthBaiduScope scope : scopes) {
            if (scope.isDefault()) {
                defaultScopes.add(scope);
            }
        }
        return defaultScopes;
    }

    public static List<String> listScope() {
        return Arrays.stream(AuthBaiduScope.values()).map(AuthBaiduScope::getScope).collect(Collectors.toList());
    }
}
