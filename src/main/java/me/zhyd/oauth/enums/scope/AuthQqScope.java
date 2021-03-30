package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * QQ 平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthQqScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    GET_USER_INFO("get_user_info", "获取登录用户的昵称、头像、性别", true),
    /**
     * 以下 scope 需要申请：http://wiki.connect.qq.com/openapi%e6%9d%83%e9%99%90%e7%94%b3%e8%af%b7
     */
    GET_VIP_INFO("get_vip_info", "获取QQ会员的基本信息", false),
    GET_VIP_RICH_INFO("get_vip_rich_info", "获取QQ会员的高级信息", false),
    LIST_ALBUM("list_album", "获取用户QQ空间相册列表", false),
    UPLOAD_PIC("upload_pic", "上传一张照片到QQ空间相册", false),
    ADD_ALBUM("add_album", "在用户的空间相册里，创建一个新的个人相册", false),
    LIST_PHOTO("list_photo", "获取用户QQ空间相册中的照片列表", false);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
