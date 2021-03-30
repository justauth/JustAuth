package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 酷家乐平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthKujialeScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    GET_USER_INFO("get_user_info", "获取用户的基本信息", true),
    GET_DESIGN("get_design", "获取指定方案详情", false),
    GET_BUDGET_LIST("get_budget_list", "获取清单预算概览数据", false);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
