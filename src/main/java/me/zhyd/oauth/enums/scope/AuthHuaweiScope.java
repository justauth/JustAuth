package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 华为平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthHuaweiScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    BASE_PROFILE("https://www.huawei.com/auth/account/base.profile", "获取用户的基本信息", true),
    MOBILE_NUMBER("https://www.huawei.com/auth/account/mobile.number", "获取用户的手机号", false),
    ACCOUNTLIST("https://www.huawei.com/auth/account/accountlist", "获取用户的账单列表", false),

    /**
     * 以下两个 scope 不需要经过华为评估和验证
     */
    SCOPE_DRIVE_FILE("https://www.huawei.com/auth/drive.file", "只允许访问由应用程序创建或打开的文件", false),
    SCOPE_DRIVE_APPDATA("https://www.huawei.com/auth/drive.appdata", "只允许访问由应用程序创建或打开的文件", false),
    /**
     * 以下四个 scope 使用前需要向drivekit@huawei.com提交申请
     * <p>
     * 参考：https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides-V5/server-dev-0000001050039664-V5#ZH-CN_TOPIC_0000001050039664__section1618418855716
     */
    SCOPE_DRIVE("https://www.huawei.com/auth/drive", "只允许访问由应用程序创建或打开的文件", false),
    SCOPE_DRIVE_READONLY("https://www.huawei.com/auth/drive.readonly", "只允许访问由应用程序创建或打开的文件", false),
    SCOPE_DRIVE_METADATA("https://www.huawei.com/auth/drive.metadata", "只允许访问由应用程序创建或打开的文件", false),
    SCOPE_DRIVE_METADATA_READONLY("https://www.huawei.com/auth/drive.metadata.readonly", "只允许访问由应用程序创建或打开的文件", false),


    ;

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
