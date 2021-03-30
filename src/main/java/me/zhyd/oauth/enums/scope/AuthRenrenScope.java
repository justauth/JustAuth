package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 人人平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthRenrenScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    READ_USER_BLOG("read_user_blog", "获取用户日志时需要用户授予的权限。", false),
    READ_USER_CHECKIN("read_user_checkin", "获取用户报到信息时需要用户授予的权限。", false),
    READ_USER_FEED("read_user_feed", "获取用户新鲜事时需要用户授予的权限。", false),
    READ_USER_GUESTBOOK("read_user_guestbook", "获取用户留言板时需要用户授予的权限。", false),
    READ_USER_INVITATION("read_user_invitation", "获取用户被邀请的状况时需要用户授予的权限。", false),
    READ_USER_LIKE_HISTORY("read_user_like_history", "获取用户喜欢的历史信息时需要用户授予的权限。", false),
    READ_USER_MESSAGE("read_user_message", "获取用户站内信时需要用户授予的权限。", false),
    READ_USER_NOTIFICATION("read_user_notification", "获取用户已收到的通知时需要用户授予的权限。", false),
    READ_USER_PHOTO("read_user_photo", "获取用户相册相关信息时需要用户授予的权限。", false),
    READ_USER_STATUS("read_user_status", "获取用户状态相关信息时需要用户授予的权限。", false),
    READ_USER_ALBUM("read_user_album", "获取用户相册相关信息时需要用户授予的权限。", false),
    READ_USER_COMMENT("read_user_comment", "获取用户评论相关信息时需要用户授予的权限。", false),
    READ_USER_SHARE("read_user_share", "获取用户分享相关信息时需要用户授予的权限。", false),
    READ_USER_REQUEST("read_user_request", "获取用户好友请求、圈人请求等信息时需要用户授予的权限。", false),
    PUBLISH_BLOG("publish_blog", "以用户身份发布日志时需要用户授予的权限。", false),
    PUBLISH_CHECKIN("publish_checkin", "以用户身份发布报到时需要用户授予的权限。", false),
    PUBLISH_FEED("publish_feed", "以用户身份发送新鲜事时需要用户授予的权限。", false),
    PUBLISH_SHARE("publish_share", "以用户身份发送分享时需要用户授予的权限。", false),
    WRITE_GUESTBOOK("write_guestbook", "以用户身份进行留言时需要用户授予的权限。", false),
    SEND_INVITATION("send_invitation", "以用户身份发送邀请时需要用户授予的权限。", false),
    SEND_REQUEST("send_request", "以用户身份发送好友申请、圈人请求等时需要用户授予的权限。", false),
    SEND_MESSAGE("send_message", "以用户身份发送站内信时需要用户授予的权限。", false),
    SEND_NOTIFICATION("send_notification", "以用户身份发送通知（user_to_user）时需要用户授予的权限。", false),
    PHOTO_UPLOAD("photo_upload", "以用户身份上传照片时需要用户授予的权限。", false),
    STATUS_UPDATE("status_update", "以用户身份发布状态时需要用户授予的权限。", false),
    CREATE_ALBUM("create_album", "以用户身份发布相册时需要用户授予的权限。", false),
    PUBLISH_COMMENT("publish_comment", "以用户身份发布评论时需要用户授予的权限。", false),
    OPERATE_LIKE("operate_like", "以用户身份执行喜欢操作时需要用户授予的权限。", false),
    ADMIN_PAGE("admin_page", "以用户的身份，管理其可以管理的公共主页的权限。", false),
    ;

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
