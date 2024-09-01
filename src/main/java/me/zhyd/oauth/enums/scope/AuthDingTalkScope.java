package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 钉钉平台 OAuth 授权范围
 *
 * https://open.dingtalk.com/document/orgapp/obtain-identity-credentials#title-4up-u8w-5ug
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.16.7
 */
@Getter
@AllArgsConstructor
public enum AuthDingTalkScope implements AuthScope {

    /**
     * 无需申请	默认开启
     */
    openid("openid", "授权后可获得用户userid", true),
    /**
     * 无需申请	默认开启
     */
    corpid("corpid", "授权后可获得登录过程中用户选择的组织id", false)
    ;

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
