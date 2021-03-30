package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 企业自建应用授权范围
 *
 * @author liguanhua (347826496(a)qq.com)
 * @since 1.15.9
 */
@Getter
@AllArgsConstructor
public enum AuthWeChatEnterpriseWebScope implements AuthScope {
    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    SNSAPI_BASE("snsapi_base", "应用授权作用域。企业自建应用固定填写：snsapi_base", true);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
