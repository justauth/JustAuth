package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微软平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthMicrosoftScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    PROFILE("profile", "允许应用查看用户的基本个人资料（名称、图片、用户名称）", true),
    EMAIL("email", "允许应用读取用户的主电子邮件地址", true),
    OPENID("openid", "允许用户以其工作或学校帐户登录应用，并允许应用查看用户的基本个人资料信息", true),
    OFFLINE_ACCESS("offline_access", "允许应用读取和更新用户数据，即使用户当前没有在使用此应用，也不例外", true),

    USER_READ("User.Read", "登录并读取用户个人资料", false),
    USER_READWRITE("User.ReadWrite", "对用户个人资料的读写权限", false),
    USER_READBASIC_ALL("User.ReadBasic.All", "读取所有用户的基本个人资料", false),
    USER_READ_ALL("User.Read.All", "读取所有用户的完整个人资料", false),
    USER_READWRITE_ALL("User.ReadWrite.All", "读取和写入所有用户的完整个人资料", false),
    USER_INVITE_ALL("User.Invite.All", "将来宾用户邀请到组织", false),
    USER_EXPORT_ALL("User.Export.All", "导出用户数据", false),
    USER_MANAGEIDENTITIES_ALL("User.ManageIdentities.All", "管理所有用户标识", false),

    USERACTIVITY_READWRITE_CREATEDBYAPP("UserActivity.ReadWrite.CreatedByApp", "将应用活动读取和写入到用户的活动源", false),

    FILES_READ("Files.Read", "允许应用读取登录用户的文件", false),
    FILES_READ_ALL("Files.Read.All", "允许应用读取登录用户可以访问的所有文件", false),
    FILES_READWRITE("Files.ReadWrite", "允许应用读取、创建、更新和删除登录用户的文件", false),
    FILES_READWRITE_ALL("Files.ReadWrite.All", "允许应用读取、创建、更新和删除登录用户可以访问的所有文件", false),
    FILES_READWRITE_APPFOLDER("Files.ReadWrite.AppFolder", "允许应用读取、创建、更新和删除应用程序文件夹中的文件", false),
    FILES_READ_SELECTED("Files.Read.Selected", "允许应用读取用户选择的文件。在用户选择文件后，应用有几个小时的访问权限", false),
    FILES_READWRITE_SELECTED("Files.ReadWrite.Selected", "允许应用读取和写入用户选择的文件。在用户选择文件后，应用有几个小时的访问权限", false),

    ORGCONTACT_READ_ALL("OrgContact.Read.All", "允许应用代表已登录用户读取所有组织联系人。 这些联系人由组织管理，不同于用户的个人联系人", false),

    MAIL_READ("Mail.Read", "允许应用读取用户邮箱中的电子邮件", false),
    MAIL_READBASIC("Mail.ReadBasic", "允许应用读取已登录用户的邮箱，但不读取 body、bodyPreview、uniqueBody、attachments、extensions 和任何扩展属性。 不包含邮件搜索权限", false),
    MAIL_READWRITE("Mail.ReadWrite", "允许应用创建、读取、更新和删除用户邮箱中的电子邮件。不包括发送电子邮件的权限", false),
    MAIL_READ_SHARED("Mail.Read.Shared", "允许应用读取用户可以访问的邮件，包括用户个人邮件和共享邮件", false),
    MAIL_READWRITE_SHARED("Mail.ReadWrite.Shared", "允许应用创建、读取、更新和删除用户有权访问的邮件，包括用户个人邮件和共享邮件。不包括邮件发送权限", false),
    MAIL_SEND("Mail.Send", "允许应用以组织用户身份发送邮件", false),
    MAIL_SEND_SHARED("Mail.Send.Shared", "允许应用以登录用户身份发送邮件，包括代表他人发送邮件", false),
    MAILBOXSETTINGS_READ("MailboxSettings.Read", "允许应用读取用户的邮箱设置。不包括邮件发送权限", false),
    MAILBOXSETTINGS_READWRITE("MailboxSettings.ReadWrite", "允许应用创建、读取、更新和删除用户邮箱设置。 不包含直接发送邮件的权限，但允许应用创建能够转发或重定向邮件的规则", false),

    NOTES_READ("Notes.Read", "允许应用代表已登录用户读取 OneNote 笔记本和分区标题并创建新的页面、笔记本和分区", false),
    NOTES_CREATE("Notes.Create", "允许应用代创建用户 OneNote 笔记本", false),
    NOTES_READWRITE("Notes.ReadWrite", "允许应用代表已登录用户读取、共享和修改 OneNote 笔记本", false),
    NOTES_READ_ALL("Notes.Read.All", "允许应用读取登录用户在组织中有权访问的 OneNote 笔记本", false),
    NOTES_READWRITE_ALL("Notes.ReadWrite.All", "允许应用读取、共享和修改已登录用户在组织中有权访问的 OneNote 笔记本", false),
    ;

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
