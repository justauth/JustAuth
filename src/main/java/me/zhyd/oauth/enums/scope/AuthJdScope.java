package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 京东平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthJdScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    SNSAPI_BASE("snsapi_base", "基础授权", true);

    private String scope;
    private String description;
    private boolean isDefault;

    public static List<AuthScope> getDefaultScopes() {
        AuthJdScope[] scopes = AuthJdScope.values();
        List<AuthScope> defaultScopes = new ArrayList<>();
        for (AuthJdScope scope : scopes) {
            if (scope.isDefault()) {
                defaultScopes.add(scope);
            }
        }
        return defaultScopes;
    }

    public static List<String> listScope() {
        return Arrays.stream(AuthJdScope.values()).map(AuthJdScope::getScope).collect(Collectors.toList());
    }
}
