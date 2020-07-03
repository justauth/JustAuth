package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 酷家乐平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthKujialeScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    GET_USER_INFO("get_user_info", "获取用户的基本信息", true),
    GET_DESIGN("get_design", "获取指定方案详情", false),
    GET_BUDGET_LIST("get_budget_list", "获取清单预算概览数据", false);

    private String scope;
    private String description;
    private boolean isDefault;

    public static List<AuthScope> getDefaultScopes() {
        AuthKujialeScope[] scopes = AuthKujialeScope.values();
        List<AuthScope> defaultScopes = new ArrayList<>();
        for (AuthKujialeScope scope : scopes) {
            if (scope.isDefault()) {
                defaultScopes.add(scope);
            }
        }
        return defaultScopes;
    }

    public static List<String> listScope() {
        return Arrays.stream(AuthKujialeScope.values()).map(AuthKujialeScope::getScope).collect(Collectors.toList());
    }
}
