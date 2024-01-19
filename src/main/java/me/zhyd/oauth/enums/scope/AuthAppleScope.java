package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @see <a href="https://developer.apple.com/documentation/sign_in_with_apple/clientconfigi/3230955-scope/">scope</a>
 */
@Getter
@AllArgsConstructor
public enum AuthAppleScope implements AuthScope {
    EMAIL("email", "用户邮箱", true),
    NAME("name", "用户名", true),
    ;

    private final String scope;
    private final String description;
    private final boolean isDefault;
}
