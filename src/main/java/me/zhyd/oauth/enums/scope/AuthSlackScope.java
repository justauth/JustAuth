package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Slack 平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.16.0
 */
@Getter
@AllArgsConstructor
public enum AuthSlackScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    USERS_PROFILE_READ("users.profile:read", "View profile details about people in a workspace", true),
    USERS_READ("users:read", "View people in a workspace", true),
    USERS_READ_EMAIL("users:read.email", "View email addresses of people in a workspace", true),
    USERS_PROFILE_WRITE("users.profile:write", "Edit a user’s profile information and status", false),
    USERS_PROFILE_WRITE_USER("users.profile:write:user", "Change the user's profile fields", false),
    USERS_WRITE("users:write", "Set presence for your slack app", false),
    ADMIN("admin", "Administer a workspace", false),
    ADMIN_ANALYTICS_READ("admin.analytics:read", "Access analytics data about the organization", false),
    ADMIN_APPS_READ("admin.apps:read", "View apps and app requests in a workspace", false),
    ADMIN_APPS_WRITE("admin.apps:write", "Manage apps in a workspace", false),
    ADMIN_BARRIERS_READ("admin.barriers:read", "Read information barriers in the organization", false),
    ADMIN_BARRIERS_WRITE("admin.barriers:write", "Manage information barriers in the organization", false),
    ADMIN_CONVERSATIONS_READ("admin.conversations:read", "View the channel’s member list, topic, purpose and channel name", false),
    ADMIN_CONVERSATIONS_WRITE("admin.conversations:write", "Start a new conversation, modify a conversation and modify channel details", false),
    ADMIN_INVITES_READ("admin.invites:read", "Gain information about invite requests in a Grid organization.", false),
    ADMIN_INVITES_WRITE("admin.invites:write", "Approve or deny invite requests in a Grid organization.", false),
    ADMIN_TEAMS_READ("admin.teams:read", "Access information about a workspace", false),
    ADMIN_TEAMS_WRITE("admin.teams:write", "Make changes to a workspace", false),
    ADMIN_USERGROUPS_READ("admin.usergroups:read", "Access information about user groups", false),
    ADMIN_USERGROUPS_WRITE("admin.usergroups:write", "Make changes to your usergroups", false),
    ADMIN_USERS_READ("admin.users:read", "Access a workspace’s profile information", false),
    ADMIN_USERS_WRITE("admin.users:write", "Modify account information", false),
    APP_MENTIONS_READ("app_mentions:read", "View messages that directly mention @your_slack_app in conversations that the app is in", false),
    AUDITLOGS_READ("auditlogs:read", "View events from all workspaces, channels and users (Enterprise Grid only)", false),
    BOT("bot", "Add the ability for people to direct message or mention @your_slack_app", false),
    CALLS_READ("calls:read", "View information about ongoing and past calls", false),
    CALLS_WRITE("calls:write", "Start and manage calls in a workspace", false),
    CHANNELS_HISTORY("channels:history", "View messages and other content in public channels that your slack app has been added to", false),
    CHANNELS_JOIN("channels:join", "Join public channels in a workspace", false),
    CHANNELS_MANAGE("channels:manage", "Manage public channels that your slack app has been added to and create new ones", false),
    CHANNELS_READ("channels:read", "View basic information about public channels in a workspace", false),
    CHANNELS_WRITE("channels:write", "Manage a user’s public channels and create new ones on a user’s behalf", false),
    CHAT_WRITE("chat:write", "Post messages in approved channels & conversations", false),
    CHAT_WRITE_CUSTOMIZE("chat:write.customize", "Send messages as @your_slack_app with a customized username and avatar", false),
    CHAT_WRITE_PUBLIC("chat:write.public", "Send messages to channels @your_slack_app isn't a member of", false),
    CHAT_WRITE_BOT("chat:write:bot", "Send messages as your slack app", false),
    CHAT_WRITE_USER("chat:write:user", "Send messages on a user’s behalf", false),
    CLIENT("client", "Receive all events from a workspace in real time", false),
    COMMANDS("commands", "Add shortcuts and/or slash commands that people can use", false),
    CONVERSATIONS_HISTORY("conversations:history", "Deprecated: Retrieve conversation history for legacy workspace apps", false),
    CONVERSATIONS_READ("conversations:read", "Deprecated: Retrieve information on conversations for legacy workspace apps", false),
    CONVERSATIONS_WRITE("conversations:write", "Deprecated: Edit conversation attributes for legacy workspace apps", false),
    DND_READ("dnd:read", "View Do Not Disturb settings for people in a workspace", false),
    DND_WRITE("dnd:write", "Edit a user’s Do Not Disturb settings", false),
    DND_WRITE_USER("dnd:write:user", "Change the user's Do Not Disturb settings", false),
    EMOJI_READ("emoji:read", "View custom emoji in a workspace", false),
    FILES_READ("files:read", "View files shared in channels and conversations that your slack app has been added to", false),
    FILES_WRITE("files:write", "Upload, edit, and delete files as your slack app", false),
    FILES_WRITE_USER("files:write:user", "Upload, edit, and delete files as your slack app", false),
    GROUPS_HISTORY("groups:history", "View messages and other content in private channels that your slack app has been added to", false),
    GROUPS_READ("groups:read", "View basic information about private channels that your slack app has been added to", false),
    GROUPS_WRITE("groups:write", "Manage private channels that your slack app has been added to and create new ones", false),
    IDENTIFY("identify", "View information about a user’s identity", false),
    IDENTITY_AVATAR("identity.avatar", "View a user’s Slack avatar", false),
    IDENTITY_AVATAR_READ_USER("identity.avatar:read:user", "View the user's profile picture", false),
    IDENTITY_BASIC("identity.basic", "View information about a user’s identity", false),
    IDENTITY_EMAIL("identity.email", "View a user’s email address", false),
    IDENTITY_EMAIL_READ_USER("identity.email:read:user", "This scope is not yet described.", false),
    IDENTITY_TEAM("identity.team", "View a user’s Slack workspace name", false),
    IDENTITY_TEAM_READ_USER("identity.team:read:user", "View the workspace's name, domain, and icon", false),
    IDENTITY_READ_USER("identity:read:user", "This scope is not yet described.", false),
    IM_HISTORY("im:history", "View messages and other content in direct messages that your slack app has been added to", false),
    IM_READ("im:read", "View basic information about direct messages that your slack app has been added to", false),
    IM_WRITE("im:write", "Start direct messages with people", false),
    INCOMING_WEBHOOK("incoming-webhook", "Create one-way webhooks to post messages to a specific channel", false),
    LINKS_READ("links:read", "View  URLs in messages", false),
    LINKS_WRITE("links:write", "Show previews of  URLs in messages", false),
    MPIM_HISTORY("mpim:history", "View messages and other content in group direct messages that your slack app has been added to", false),
    MPIM_READ("mpim:read", "View basic information about group direct messages that your slack app has been added to", false),
    MPIM_WRITE("mpim:write", "Start group direct messages with people", false),
    NONE("none", "Execute methods without needing a scope", false),
    PINS_READ("pins:read", "View pinned content in channels and conversations that your slack app has been added to", false),
    PINS_WRITE("pins:write", "Add and remove pinned messages and files", false),
    POST("post", "Post messages to a workspace", false),
    REACTIONS_READ("reactions:read", "View emoji reactions and their associated content in channels and conversations that your slack app has been added to", false),
    REACTIONS_WRITE("reactions:write", "Add and edit emoji reactions", false),
    READ("read", "View all content in a workspace", false),
    REMINDERS_READ("reminders:read", "View reminders created by your slack app", false),
    REMINDERS_READ_USER("reminders:read:user", "Access reminders created by a user or for a user", false),
    REMINDERS_WRITE("reminders:write", "Add, remove, or mark reminders as complete", false),
    REMINDERS_WRITE_USER("reminders:write:user", "Add, remove, or complete reminders for the user", false),
    REMOTE_FILES_READ("remote_files:read", "View remote files added by the app in a workspace", false),
    REMOTE_FILES_SHARE("remote_files:share", "Share remote files on a user’s behalf", false),
    REMOTE_FILES_WRITE("remote_files:write", "Add, edit, and delete remote files on a user’s behalf", false),
    SEARCH_READ("search:read", "Search a workspace’s content", false),
    STARS_READ("stars:read", "View messages and files that your slack app has starred", false),
    STARS_WRITE("stars:write", "Add or remove stars", false),
    TEAM_READ("team:read", "View the name, email domain, and icon for workspaces your slack app is connected to", false),
    TOKENS_BASIC("tokens.basic", "Execute methods without needing a scope", false),
    USERGROUPS_READ("usergroups:read", "View user groups in a workspace", false),
    USERGROUPS_WRITE("usergroups:write", "Create and manage user groups", false),
    WORKFLOW_STEPS_EXECUTE("workflow.steps:execute", "Add steps that people can use in Workflow Builder", false);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
