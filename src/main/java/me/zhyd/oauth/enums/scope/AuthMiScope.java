package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private String scope;
    private String description;
    private boolean isDefault;

    public static List<AuthScope> getDefaultScopes() {
        AuthMiScope[] scopes = AuthMiScope.values();
        List<AuthScope> defaultScopes = new ArrayList<>();
        for (AuthMiScope scope : scopes) {
            if (scope.isDefault()) {
                defaultScopes.add(scope);
            }
        }
        return defaultScopes;
    }

    public static List<String> listScope() {
        return Arrays.stream(AuthMiScope.values()).map(AuthMiScope::getScope).collect(Collectors.toList());
    }
}
