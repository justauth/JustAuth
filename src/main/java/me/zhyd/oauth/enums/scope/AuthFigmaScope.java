package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Figma OAuth 授权范围
 * <a href="https://www.figma.com/developers/api#authentication-scopes">...</a>
 *
 * @author xiangqian
 * @since 1.16.6
 */
@Getter
@AllArgsConstructor
public enum AuthFigmaScope implements AuthScope {

    FILE_CONTENT("files:read", "Read files, projects, users, versions, comments, components & styles, and webhooks", true),
    VARIABLES("file_variables:read,file_variables:write", "Read and write to variables in Figma file. Note: this is only available to members in Enterprise organizations", false),
    COMMENTS("file_comments:write", "Post and delete comments and comment reactions in files", false),
    DEV_RESOURCES("file_dev_resources:read,file_dev_resources:write", "Read and write to dev resources in files", false),
    LIBRARY_ANALYTICS("library_analytics:read", "Read your design system analytics", false),
    WEBHOOKS("webhooks:write", "Create and manage webhooks", false);

    private final String scope;
    private final String description;
    private final boolean isDefault;


}
