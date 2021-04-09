package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 抖音平台 OAuth 授权范围
 *
 * https://open.douyin.com/platform/doc/6855240178122983437
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.16.1
 */
@Getter
@AllArgsConstructor
public enum AuthDouyinScope implements AuthScope {

    /**
     * 无需申请	默认开启
     */
    USER_INFO("user_info", "返回抖音用户公开信息", true),
    /**
     * 无需申请	默认开启
     */
    AWEME_SHARE("aweme.share", "抖音分享", false),
    /**
     * 普通权限,管理中心申请
     */
    IM_SHARE("im.share", "分享给抖音好友", false),
    RENEW_REFRESH_TOKEN("renew_refresh_token", "授权有效期动态续期", false),
    FOLLOWING_LIST("following.list", "获取该用户的关注列表", false),
    FANS_LIST("fans.list", "获取该用户的粉丝列表", false),
    VIDEO_CREATE("video.create", "视频发布及管理", false),
    VIDEO_DELETE("video.delete", "删除内容", false),
    VIDEO_DATA("video.data", "查询授权用户的抖音视频数据", false),
    VIDEO_LIST("video.list", "查询特定抖音视频的视频数据", false),
    /**
     * 特殊权限	默认关闭	管理中心申请
     */
    SHARE_WITH_SOURCE("share_with_source", "分享携带来源标签，用户可点击标签进入转化页", false),
    MOBILE("mobile", "用抖音帐号登录第三方平台，获得用户在抖音上的手机号码", false),
    MOBILE_ALERT("mobile_alert", "用抖音帐号登录第三方平台，获得用户在抖音上的手机号码", false),
    VIDEO_SEARCH("video.search", "关键词视频管理", false),
    POI_SEARCH("poi.search", "查询POI信息", false),
    LOGIN_ID("login_id", "静默授权直接获取该用户的open id", false),
    /**
     * 抖音数据权限, 默认关闭, 管理中心申请
     */
    DATA_EXTERNAL_USER("data.external.user", "查询用户的获赞、评论、分享，主页访问等相关数据", false),
    DATA_EXTERNAL_ITEM("data.external.item", "查询作品的获赞，评论，分享等相关数据", false),
    FANS_DATA("fans.data", "获取用户粉丝画像数据", false),
    HOTSEARCH("hotsearch", "获取抖音热门内容", false),
    STAR_TOP_SCORE_DISPLAY("star_top_score_display", "星图达人与达人对应各指数评估分，以及星图6大热门维度下的达人榜单", false),
    STAR_TOPS("star_tops", "星图达人与达人对应各指数评估分，以及星图6大热门维度下的达人榜单", false),
    STAR_AUTHOR_SCORE_DISPLAY("star_author_score_display", "星图达人与达人对应各指数评估分，以及星图6大热门维度下的达人榜单", false),
    notes("data.external.sdk_share", "获取用户通过分享SDK分享视频数据", false),
    /**
     * 定向开通	默认关闭	定向开通
     */
    DISCOVERY_ENT("discovery.ent", "查询抖音电影榜、抖音剧集榜、抖音综艺榜数据", false),
    ;

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
