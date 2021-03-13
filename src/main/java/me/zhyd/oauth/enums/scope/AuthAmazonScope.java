package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Amazon平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.16.0
 */
@Getter
@AllArgsConstructor
public enum AuthAmazonScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    R_LITEPROFILE("profile", "The profile scope includes a user's name and email address", true),
    R_EMAILADDRESS("profile:user_id", "The profile:user_id scope only includes the user_id field of the profile", true),
    W_MEMBER_SOCIAL("postal_code", "This includes the user's zip/postal code number from their primary shipping address", true);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
