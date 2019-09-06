package me.zhyd.oauth.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import me.zhyd.oauth.exception.AuthException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 全局的工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.0.0
 */
public class GlobalAuthUtil {
    private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
    private static final String ALGORITHM = "HmacSHA256";

    /**
     * 生成钉钉请求的Signature
     *
     * @param secretKey 平台应用的授权密钥
     * @param timestamp 时间戳
     * @return Signature
     */
    public static String generateDingTalkSignature(String secretKey, String timestamp) {
        byte[] signData = sign(secretKey.getBytes(DEFAULT_ENCODING), timestamp.getBytes(DEFAULT_ENCODING));
        return urlEncode(new String(Base64.encode(signData, false)));
    }

    /**
     * 签名
     *
     * @param key  key
     * @param data data
     * @return byte[]
     */
    private static byte[] sign(byte[] key, byte[] data) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(key, ALGORITHM));
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException ex) {
            throw new AuthException("Unsupported algorithm: " + ALGORITHM, ex);
        } catch (InvalidKeyException ex) {
            throw new AuthException("Invalid key: " + Arrays.toString(key), ex);
        }
    }

    /**
     * 编码
     *
     * @param value str
     * @return encode str
     */
    public static String urlEncode(String value) {
        if (value == null) {
            return "";
        }
        try {
            String encoded = URLEncoder.encode(value, GlobalAuthUtil.DEFAULT_ENCODING.displayName());
            return encoded.replace("+", "%20").replace("*", "%2A").replace("~", "%7E").replace("/", "%2F");
        } catch (UnsupportedEncodingException e) {
            throw new AuthException("Failed To Encode Uri", e);
        }
    }


    /**
     * 解码
     *
     * @param value str
     * @return decode str
     */
    public static String urlDecode(String value) {
        if (value == null) {
            return "";
        }
        try {
            return URLDecoder.decode(value, GlobalAuthUtil.DEFAULT_ENCODING.displayName());
        } catch (UnsupportedEncodingException e) {
            throw new AuthException("Failed To Decode Uri", e);
        }
    }

    /**
     * string字符串转map，str格式为 {@code xxx=xxx&xxx=xxx}
     *
     * @param accessTokenStr 待转换的字符串
     * @return map
     */
    public static Map<String, String> parseStringToMap(String accessTokenStr) {
        Map<String, String> res = new HashMap<>();
        if (accessTokenStr.contains("&")) {
            String[] fields = accessTokenStr.split("&");
            for (String field : fields) {
                if (field.contains("=")) {
                    String[] keyValue = field.split("=");
                    res.put(GlobalAuthUtil.urlDecode(keyValue[0]), keyValue.length == 2 ? GlobalAuthUtil.urlDecode(keyValue[1]) : null);
                }
            }
        }
        return res;
    }

    /**
     * map转字符串，转换后的字符串格式为 {@code xxx=xxx&xxx=xxx}
     *
     * @param params 待转换的map
     * @param encode 是否转码
     * @return str
     */
    public static String parseMapToString(Map<String, Object> params, boolean encode) {
        List<String> paramList = new ArrayList<>();
        params.forEach((k, v) -> {
            if (ObjectUtil.isNull(v)) {
                paramList.add(k + "=");
            } else {
                String valueString = v.toString();
                paramList.add(k + "=" + (encode ? urlEncode(valueString) : valueString));
            }
        });
        return CollUtil.join(paramList, "&");
    }

    /**
     * 将url的参数列表转换成map
     *
     * @param url 待转换的url
     * @return map
     */
    public static Map<String, Object> parseQueryToMap(String url) {
        Map<String, Object> paramMap = new HashMap<>();
        HttpUtil.decodeParamMap(url, "UTF-8").forEach(paramMap::put);
        return paramMap;
    }

    /**
     * 是否为http协议
     *
     * @param url 待验证的url
     * @return true: http协议, false: 非http协议
     */
    public static boolean isHttpProtocol(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith("http://");
    }

    /**
     * 是否为https协议
     *
     * @param url 待验证的url
     * @return true: https协议, false: 非https协议
     */
    public static boolean isHttpsProtocol(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith("https://");
    }

    /**
     * 是否为本地主机（域名）
     *
     * @param url 待验证的url
     * @return true: 本地主机（域名）, false: 非本地主机（域名）
     */
    public static boolean isLocalHost(String url) {
        return StringUtils.isEmpty(url) || url.contains("127.0.0.1") || url.contains("localhost");
    }

    /**
     * 生成饿了么请求的Signature
     * <p>
     * 代码copy并修改自：https://coding.net/u/napos_openapi/p/eleme-openapi-java-sdk/git/blob/master/src/main/java/eleme/openapi/sdk/utils/SignatureUtil.java
     *
     * @param appKey     平台应用的授权key
     * @param secret     平台应用的授权密钥
     * @param timestamp  时间戳，单位秒。API服务端允许客户端请求最大时间误差为正负5分钟。
     * @param action     饿了么请求的api方法
     * @param token      用户授权的token
     * @param parameters 加密参数
     * @return Signature
     */
    public static String generateElemeSignature(String appKey, String secret, long timestamp, String action, String token, Map<String, Object> parameters) {
        final Map<String, Object> sorted = new TreeMap<>();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            sorted.put(entry.getKey(), entry.getValue());
        }
        sorted.put("app_key", appKey);
        sorted.put("timestamp", timestamp);
        StringBuffer string = new StringBuffer();
        for (Map.Entry<String, Object> entry : sorted.entrySet()) {
            string.append(entry.getKey()).append("=").append(JSON.toJSONString(entry.getValue()));
        }
        String splice = String.format("%s%s%s%s", action, token, string, secret);
        String calculatedSignature = md5(splice);
        return calculatedSignature.toUpperCase();
    }

    /**
     * MD5加密饿了么请求的Signature
     * <p>
     * 代码copy并修改自：https://coding.net/u/napos_openapi/p/eleme-openapi-java-sdk/git/blob/master/src/main/java/eleme/openapi/sdk/utils/SignatureUtil.java
     *
     * @param str 饿了么请求的Signature
     * @return md5 str
     */
    private static String md5(String str) {
        MessageDigest md = null;
        StringBuilder buffer = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] byteData = md.digest();
            buffer = new StringBuilder();
            for (byte byteDatum : byteData) {
                buffer.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception ignored) {
        }

        return null == buffer ? "" : buffer.toString();
    }

}
