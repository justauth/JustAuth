package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Okta 平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.16.0
 */
@Getter
@AllArgsConstructor
public enum AuthOktaScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    OPENID("openid", "Signals that a request is an OpenID request.", true),
    PROFILE("profile", "The exact data varies based on what profile information you have provided, such as: name, time zone, picture, or birthday.", true),
    EMAIL("email", "This allows the app to view your email address.", true),
    ADDRESS("address", "This allows the app to view your address, such as: street address, city, state, and zip code.", true),
    PHONE("phone", "This allows the app to view your phone number.", true),
    OFFLINE_ACCESS("offline_access", "This keeps you signed in to the app, even when you are not using it.", true),
    OKTA_USERS_MANAGE("okta.users.manage", "Allows the app to create and manage users and read all profile and credential information for users", false),
    OKTA_USERS_READ("okta.users.read", "Allows the app to read any user's profile and credential information", false),
    OKTA_USERS_MANAGE_SELF("okta.users.manage.self", "Allows the app to manage the currently signed-in user's profile. Currently only supports user profile attribute updates.", false),
    OKTA_USERS_READ_SELF("okta.users.read.self", "Allows the app to read the currently signed-in user's profile and credential information", false),
    OKTA_APPS_MANAGE("okta.apps.manage", "Allows the app to create and manage Apps in your Okta organization", false),
    OKTA_APPS_READ("okta.apps.read", "Allows the app to read information about Apps in your Okta organization", false),
    OKTA_AUTHORIZATIONSERVERS_MANAGE("okta.authorizationServers.manage", "Allows the app to manage authorization servers", false),
    OKTA_AUTHORIZATIONSERVERS_READ("okta.authorizationServers.read", "Allows the app to read authorization server information", false),
    OKTA_CLIENTS_MANAGE("okta.clients.manage", "Allows the app to manage all OAuth/OIDC clients and to create new clients", false),
    OKTA_CLIENTS_READ("okta.clients.read", "Allows the app to read information for all OAuth/OIDC clients", false),
    OKTA_CLIENTS_REGISTER("okta.clients.register", "Allows the app to register (create) new OAuth/OIDC clients (but not read information about existing clients)", false),
    OKTA_EVENTHOOKS_MANAGE("okta.eventHooks.manage", "Allows the app to create and manage Event Hooks in your Okta organization", false),
    OKTA_EVENTHOOKS_READ("okta.eventHooks.read", "Allows the app to read information about Event Hooks in your Okta organization", false),
    OKTA_FACTORS_MANAGE("okta.factors.manage", "Allows the app to manage all admin operations for org factors (for example, activate, deactive, read)", false),
    OKTA_FACTORS_READ("okta.factors.read", "Allows the app to read org factors information", false),
    OKTA_GROUPS_MANAGE("okta.groups.manage", "Allows the app to manage groups in your Okta organization", false),
    OKTA_GROUPS_READ("okta.groups.read", "Allows the app to read information about groups and their members in your Okta organization", false),
    OKTA_IDPS_MANAGE("okta.idps.manage", "Allows the app to create and manage Identity Providers in your Okta organization", false),
    OKTA_IDPS_READ("okta.idps.read", "Allows the app to read information about Identity Providers in your Okta organization", false),
    OKTA_INLINEHOOKS_MANAGE("okta.inlineHooks.manage", "Allows the app to create and manage Inline Hooks in your Okta organization.", false),
    OKTA_INLINEHOOKS_READ("okta.inlineHooks.read", "Allows the app to read information about Inline Hooks in your Okta organization.", false),
    OKTA_LINKEDOBJECTS_MANAGE("okta.linkedObjects.manage", "Allows the app to manage Linked Object definitions in your Okta organization.", false),
    OKTA_LINKEDOBJECTS_READ("okta.linkedObjects.read", "Allows the app to read Linked Object definitions in your Okta organization.", false),
    OKTA_LOGS_READ("okta.logs.read", "Allows the app to read information about System Log entries in your Okta organization", false),
    OKTA_ROLES_MANAGE("okta.roles.manage", "Allows the app to create and manage Administrator Roles in your Okta organization", false),
    OKTA_ROLES_READ("okta.roles.read", "Allows the app to read information about Administrator Roles in your Okta organization", false),
    OKTA_SCHEMAS_MANAGE("okta.schemas.manage", "Allows the app to create and manage Schemas in your Okta organization", false),
    OKTA_SCHEMAS_READ("okta.schemas.read", "Allows the app to read information about Schemas in your Okta organization", false),
    OKTA_SESSIONS_MANAGE("okta.sessions.manage", "Allows the app to manage all sessions in your Okta organization", false),
    OKTA_SESSIONS_READ("okta.sessions.read", "Allows the app to read all sessions in your Okta organization", false),
    OKTA_TEMPLATES_MANAGE("okta.templates.manage", "Allows the app to manage all custom templates in your Okta organization", false),
    OKTA_TEMPLATES_READ("okta.templates.read", "Allows the app to read all custom templates in your Okta organization", false),
    OKTA_TRUSTEDORIGINS_MANAGE("okta.trustedOrigins.manage", "Allows the app to manage all Trusted Origins in your Okta organization", false),
    OKTA_TRUSTEDORIGINS_READ("okta.trustedOrigins.read", "Allows the app to read all Trusted Origins in your Okta organization", false),
    OKTA_POLICIES_MANAGE("okta.policies.manage", "Allows the app to manage Policies in your Okta organization", false),
    OKTA_POLICIES_READ("okta.policies.read", "Allows the app to read information about Policies in your Okta organization", false),;

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
