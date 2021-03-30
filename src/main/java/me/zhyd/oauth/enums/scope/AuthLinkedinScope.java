package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 领英平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthLinkedinScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    R_LITEPROFILE("r_liteprofile", "Use your name, headline, and photo", true),
    R_EMAILADDRESS("r_emailaddress", "Use the primary email address associated with your LinkedIn account", true),
    W_MEMBER_SOCIAL("w_member_social", "Post, comment and like posts on your behalf", true),
    R_MEMBER_SOCIAL("r_member_social", "Retrieve your posts, comments, likes, and other engagement data", false),
    R_AD_CAMPAIGNS("r_ad_campaigns", "View advertising campaigns you manage", false),
    R_ADS("r_ads", "Retrieve your advertising accounts", false),
    R_ADS_LEADGEN_AUTOMATION("r_ads_leadgen_automation", "Access your Lead Gen Forms and retrieve leads", false),
    R_ADS_REPORTING("r_ads_reporting", "Retrieve reporting for your advertising accounts", false),
    R_BASICPROFILE("r_basicprofile", "Use your basic profile including your name, photo, headline, and current positions", false),
    R_ORGANIZATION_SOCIAL("r_organization_social", "Retrieve your organizations' posts, including any comments, likes and other engagement data", false),
    RW_AD_CAMPAIGNS("rw_ad_campaigns", "Manage your advertising campaigns", false),
    RW_ADS("rw_ads", "Manage your advertising accounts", false),
    RW_COMPANY_ADMIN("rw_company_admin", "For V1 callsManage your organization's page and post updates", false),
    RW_DMP_SEGMENTS("rw_dmp_segments", "Create and manage your matched audiences", false),
    RW_ORGANIZATION_ADMIN("rw_organization_admin", "Manage your organizations' pages and retrieve reporting data", false),
    RW_ORGANIZATION("rw_organization", "For V2 callsManage your organization's page and post updates", false),
    W_ORGANIZATION_SOCIAL("w_organization_social", "Post, comment and like posts on your organization's behalf", false),
    W_SHARE("w_share", "Post updates to LinkedIn as you", false);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
