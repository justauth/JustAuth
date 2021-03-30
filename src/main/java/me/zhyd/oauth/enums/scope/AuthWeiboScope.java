package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微博平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthWeiboScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    ALL("all", "获取所有权限", true),
    EMAIL("email", "用户的联系邮箱，<a rel=\"nofollow\" href=\"http://open.weibo.com/wiki/2/account/profile/email\">接口文档</a>", false),
    DIRECT_MESSAGES_WRITE("direct_messages_write", "私信发送接口，<a rel=\"nofollow\" href=\"http://open.weibo.com/wiki/C/2/direct_messages/send\">接口文档</a>", false),
    DIRECT_MESSAGES_READ("direct_messages_read", "私信读取接口，<a rel=\"nofollow\" href=\"http://open.weibo.com/wiki/C/2/direct_messages\">接口文档</a>", false),
    INVITATION_WRITE("invitation_write", "邀请发送接口，<a rel=\"nofollow\" href=\"http://open.weibo.com/wiki/Messages#.E5.A5.BD.E5.8F.8B.E9.82.80.E8.AF.B7\">接口文档</a>", false),
    FRIENDSHIPS_GROUPS_READ("friendships_groups_read", "好友分组读取接口组，<a rel=\"nofollow\" href=\"http://open.weibo.com/wiki/API%E6%96%87%E6%A1%A3_V2#.E5.A5.BD.E5.8F.8B.E5.88.86.E7.BB.84\">接口文档</a>", false),
    FRIENDSHIPS_GROUPS_WRITE("friendships_groups_write", "好友分组写入接口组，<a rel=\"nofollow\" href=\"http://open.weibo.com/wiki/API%E6%96%87%E6%A1%A3_V2#.E5.A5.BD.E5.8F.8B.E5.88.86.E7.BB.84\">接口文档</a>", false),
    STATUSES_TO_ME_READ("statuses_to_me_read", "定向微博读取接口组，<a rel=\"nofollow\" href=\"http://open.weibo.com/wiki/API%E6%96%87%E6%A1%A3_V2#.E5.BE.AE.E5.8D.9A\">接口文档</a>", false),
    FOLLOW_APP_OFFICIAL_MICROBLOG("follow_app_official_microblog", "关注应用官方微博，该参数不对应具体接口，只需在应用控制台填写官方帐号即可。填写的路径：我的应用-选择自己的应用-应用信息-基本信息-官方运营账号（默认值是应用开发者帐号）", false);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
