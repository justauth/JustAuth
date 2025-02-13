package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 小红书商业平台 OAuth 授权范围
 *
 * @author yangjiahao
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthXiaohongshuMarketingScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    report_service("report_service", "获取账户报表信息", true),
    ad_query("ad_query", "获取推广计划、推广单元、推广创意信息", false),
    ad_manage("ad_manage", "创建&修改推广计划、推广单元、推广创意", false),
    account_manage("account_manage", "账户管理", false);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
