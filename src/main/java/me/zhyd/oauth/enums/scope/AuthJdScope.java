package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 京东平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthJdScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    SNSAPI_BASE("snsapi_base", "基础授权", true);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
