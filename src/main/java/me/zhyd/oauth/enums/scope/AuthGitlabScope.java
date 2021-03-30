package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Gitlab 平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthGitlabScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    READ_USER("read_user", "Grants read-only access to the authenticated user's profile through the /user API endpoint, which includes username, public email, and full name. Also grants access to read-only API endpoints under /users.", true),
    OPENID("openid", "Grants permission to authenticate with GitLab using OpenID Connect. Also gives read-only access to the user's profile and group memberships.", true),
    PROFILE("profile", "Grants read-only access to the user's profile data using OpenID Connect.", true),
    EMAIL("email", "Grants read-only access to the user's primary email address using OpenID Connect.", true),
    READ_API("read_api", "Grants read access to the API, including all groups and projects, the container registry, and the package registry.", false),
    READ_REPOSITORY("read_repository", "Grants read-only access to repositories on private projects using Git-over-HTTP or the Repository Files API.", false),
    WRITE_REPOSITORY("write_repository", "Grants read-write access to repositories on private projects using Git-over-HTTP (not using the API).", false),
    READ_REGISTRY("read_registry", "Grants read-only access to container registry images on private projects.", false),
    WRITE_REGISTRY("write_registry", "<span title=\"translation missing: en.doorkeeper.scope_desc.write_registry\">Write Registry</span>", false),
    SUDO("sudo", "Grants permission to perform API actions as any user in the system, when authenticated as an admin user.", false),
    API("api", "Grants complete read/write access to the API, including all groups and projects, the container registry, and the package registry.", false),
    ;

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
