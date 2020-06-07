package me.zhyd.oauth.utils;

import com.xkcoding.http.HttpUtil;
import com.xkcoding.http.config.HttpConfig;
import com.xkcoding.http.support.HttpHeader;

import java.util.Map;

/**
 * HttpUtil 工具，统一处理 http 请求，方便对 simple-http 做定制
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpUtils {

    public HttpUtils(HttpConfig config) {
        HttpUtil.setConfig(config);
    }

    public HttpUtils() {
    }


    /**
     * GET 请求
     *
     * @param url URL
     * @return 结果
     */
    public String get(String url) {
        return HttpUtil.get(url);
    }

    /**
     * GET 请求
     *
     * @param url    URL
     * @param params 参数
     * @param header 请求头
     * @param encode 是否需要 url encode
     * @return 结果
     */
    public String get(String url, Map<String, String> params, HttpHeader header, boolean encode) {
        return HttpUtil.get(url, params, header, encode);
    }

    /**
     * POST 请求
     *
     * @param url URL
     * @return 结果
     */
    public String post(String url) {
        return HttpUtil.post(url);
    }

    /**
     * POST 请求
     *
     * @param url  URL
     * @param data JSON 参数
     * @return 结果
     */
    public String post(String url, String data) {
        return HttpUtil.post(url, data);
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param data   JSON 参数
     * @param header 请求头
     * @return 结果
     */
    public String post(String url, String data, HttpHeader header) {
        return HttpUtil.post(url, data, header);
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param params form 参数
     * @param encode 是否需要 url encode
     * @return 结果
     */
    public String post(String url, Map<String, String> params, boolean encode) {
        return HttpUtil.post(url, params, encode);
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param params form 参数
     * @param header 请求头
     * @param encode 是否需要 url encode
     * @return 结果
     */
    public String post(String url, Map<String, String> params, HttpHeader header, boolean encode) {
        return HttpUtil.post(url, params, header, encode);
    }
}
