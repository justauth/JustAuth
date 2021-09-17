package me.zhyd.oauth.utils;

import com.xkcoding.http.HttpUtil;
import com.xkcoding.http.config.HttpConfig;
import com.xkcoding.http.support.HttpHeader;
import com.xkcoding.http.support.SimpleHttpResponse;
import me.zhyd.oauth.exception.AuthException;

import java.util.Map;

/**
 * HttpUtil 工具，统一处理 http 请求，方便对 simple-http 做定制
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpUtils {

    private SimpleHttpResponse httpResponse;

    public HttpUtils(HttpConfig config) {
        HttpUtil.setConfig(config);
    }

    public HttpUtils() {
    }


    /**
     * GET 请求
     *
     * @param url URL
     * @return HttpUtils
     */
    public HttpUtils get(String url) {
        this.httpResponse = HttpUtil.get(url, null, null, false);
        return this;
    }

    /**
     * GET 请求
     *
     * @param url    URL
     * @param params 参数
     * @param header 请求头
     * @param encode 是否需要 url encode
     * @return HttpUtils
     */
    public HttpUtils get(String url, Map<String, String> params, HttpHeader header, boolean encode) {
        this.httpResponse = HttpUtil.get(url, params, header, encode);
        return this;
    }

    /**
     * POST 请求
     *
     * @param url URL
     * @return HttpUtils
     */
    public HttpUtils post(String url) {
        this.httpResponse = HttpUtil.post(url);
        return this;
    }

    /**
     * POST 请求
     *
     * @param url  URL
     * @param data JSON 参数
     * @return HttpUtils
     */
    public HttpUtils post(String url, String data) {
        this.httpResponse = HttpUtil.post(url, data);
        return this;
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param data   JSON 参数
     * @param header 请求头
     * @return HttpUtils
     */
    public HttpUtils post(String url, String data, HttpHeader header) {
        this.httpResponse = HttpUtil.post(url, data, header);
        return this;
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param params form 参数
     * @param encode 是否需要 url encode
     * @return HttpUtils
     */
    public HttpUtils post(String url, Map<String, String> params, boolean encode) {
        this.httpResponse = HttpUtil.post(url, params, encode);
        return this;
    }

    /**
     * POST 请求
     *
     * @param url    URL
     * @param params form 参数
     * @param header 请求头
     * @param encode 是否需要 url encode
     * @return HttpUtils
     */
    public HttpUtils post(String url, Map<String, String> params, HttpHeader header, boolean encode) {
        this.httpResponse = HttpUtil.post(url, params, header, encode);
        return this;
    }

    private HttpUtils check() {
        if (null == httpResponse) {
            throw new AuthException("Invalid SimpleHttpResponse.");
        }
        if (!httpResponse.isSuccess()) {
            throw new AuthException(httpResponse.getError());
        }
        return this;
    }

    public String getBody() {
        return this.check().getHttpResponse().getBody();
    }

    public SimpleHttpResponse getHttpResponse() {
        return httpResponse;
    }
}
