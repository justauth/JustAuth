package me.zhyd.oauth.utils;

import com.xkcoding.http.util.MapUtil;
import com.xkcoding.http.util.StringUtil;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 构造URL
 * </p>
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @since 1.9.0
 */
@Setter
public class UrlBuilder {

    private final Map<String, String> params = new LinkedHashMap<>(7);
    private String baseUrl;

    private UrlBuilder() {

    }

    /**
     * @param baseUrl 基础路径
     * @return the new {@code UrlBuilder}
     */
    public static UrlBuilder fromBaseUrl(String baseUrl) {
        UrlBuilder builder = new UrlBuilder();
        builder.setBaseUrl(baseUrl);
        return builder;
    }

    /**
     * 只读的参数Map
     *
     * @return unmodifiable Map
     * @since 1.15.0
     */
    public Map<String, Object> getReadOnlyParams() {
        return Collections.unmodifiableMap(params);
    }

    /**
     * 添加参数
     *
     * @param key   参数名称
     * @param value 参数值
     * @return this UrlBuilder
     */
    public UrlBuilder queryParam(String key, Object value) {
        if (StringUtil.isEmpty(key)) {
            throw new RuntimeException("参数名不能为空");
        }
        String valueAsString = (value != null ? value.toString() : null);
        this.params.put(key, valueAsString);

        return this;
    }

    /**
     * 构造url
     *
     * @return url
     */
    public String build() {
        return this.build(false);
    }

    /**
     * 构造url
     *
     * @param encode 转码
     * @return url
     */
    public String build(boolean encode) {
        if (MapUtil.isEmpty(this.params)) {
            return this.baseUrl;
        }
        String baseUrl = StringUtils.appendIfNotContain(this.baseUrl, "?", "&");
        String paramString = MapUtil.parseMapToString(this.params, encode);
        return baseUrl + paramString;
    }
}
