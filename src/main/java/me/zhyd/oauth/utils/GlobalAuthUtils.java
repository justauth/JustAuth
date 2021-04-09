package me.zhyd.oauth.utils;

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
public class GlobalAuthUtils {
    private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String HMAC_SHA_256 = "HmacSHA256";

    /**
     * 生成钉钉请求的Signature
     *
     * @param secretKey 平台应用的授权密钥
     * @param timestamp 时间戳
     * @return Signature
     */
    public static String generateDingTalkSignature(String secretKey, String timestamp) {
        byte[] signData = sign(secretKey.getBytes(DEFAULT_ENCODING), timestamp.getBytes(DEFAULT_ENCODING), HMAC_SHA_256);
        return urlEncode(new String(Base64Utils.encode(signData, false)));
    }

    /**
     * 签名
     *
     * @param key       key
     * @param data      data
     * @param algorithm algorithm
     * @return byte[]
     */
    private static byte[] sign(byte[] key, byte[] data, String algorithm) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException ex) {
            throw new AuthException("Unsupported algorithm: " + algorithm, ex);
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
            String encoded = URLEncoder.encode(value, GlobalAuthUtils.DEFAULT_ENCODING.displayName());
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
            return URLDecoder.decode(value, GlobalAuthUtils.DEFAULT_ENCODING.displayName());
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
        Map<String, String> res = null;
        if (accessTokenStr.contains("&")) {
            String[] fields = accessTokenStr.split("&");
            res = new HashMap<>((int) (fields.length / 0.75 + 1));
            for (String field : fields) {
                if (field.contains("=")) {
                    String[] keyValue = field.split("=");
                    res.put(GlobalAuthUtils.urlDecode(keyValue[0]), keyValue.length == 2 ? GlobalAuthUtils.urlDecode(keyValue[1]) : null);
                }
            }
        } else {
            res = new HashMap<>(0);
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
    public static String parseMapToString(Map<String, String> params, boolean encode) {
        if (null == params || params.isEmpty()) {
            return "";
        }
        List<String> paramList = new ArrayList<>();
        params.forEach((k, v) -> {
            if (null == v) {
                paramList.add(k + "=");
            } else {
                paramList.add(k + "=" + (encode ? urlEncode(v) : v));
            }
        });
        return String.join("&", paramList);
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
        return url.startsWith("http://") || url.startsWith("http%3A%2F%2F");
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
        return url.startsWith("https://") || url.startsWith("https%3A%2F%2F");
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
     * Generate nonce with given length
     *
     * @param len length
     * @return nonce string
     */
    public static String generateNonce(int len) {
        String s = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm";
        Random rng = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = rng.nextInt(62);
            sb.append(s, index, index + 1);
        }
        return sb.toString();
    }

    /**
     * Get current timestamp
     *
     * @return timestamp string
     */
    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * Generate Twitter signature
     * https://developer.twitter.com/en/docs/basics/authentication/guides/creating-a-signature
     *
     * @param params      parameters including: oauth headers, query params, body params
     * @param method      HTTP method
     * @param baseUrl     base url
     * @param apiSecret   api key secret can be found in the developer portal by viewing the app details page
     * @param tokenSecret oauth token secret
     * @return BASE64 encoded signature string
     */
    public static String generateTwitterSignature(Map<String, String> params, String method, String baseUrl, String apiSecret, String tokenSecret) {
        TreeMap<String, String> map = new TreeMap<>(params);
        String str = parseMapToString(map, true);
        String baseStr = method.toUpperCase() + "&" + urlEncode(baseUrl) + "&" + urlEncode(str);
        String signKey = apiSecret + "&" + (StringUtils.isEmpty(tokenSecret) ? "" : tokenSecret);
        byte[] signature = sign(signKey.getBytes(DEFAULT_ENCODING), baseStr.getBytes(DEFAULT_ENCODING), HMAC_SHA1);

        return new String(Base64Utils.encode(signature, false));
    }

    /**
     * 喜马拉雅签名算法
     * {@code https://open.ximalaya.com/doc/detailApi?categoryId=6&articleId=69}
     *
     * @param params       加密参数
     * @param clientSecret 平台应用的授权key
     * @return Signature
     * @since 1.15.9
     */
    public static String generateXmlySignature(Map<String, String> params, String clientSecret) {
        TreeMap<String, String> map = new TreeMap<>(params);
        String baseStr = Base64Utils.encode(parseMapToString(map, false));
        byte[] sign = sign(clientSecret.getBytes(DEFAULT_ENCODING), baseStr.getBytes(DEFAULT_ENCODING), HMAC_SHA1);
        MessageDigest md5 = null;
        StringBuilder builder = null;
        try {
            builder = new StringBuilder();
            md5 = MessageDigest.getInstance("MD5");
            md5.update(sign);
            byte[] byteData = md5.digest();
            for (byte byteDatum : byteData) {
                builder.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception ignored) {
        }
        return null == builder ? "" : builder.toString();
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
        final Map<String, Object> sorted = new TreeMap<>(parameters);
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
     * MD5加密
     * <p>
     * 代码copy并修改自：https://coding.net/u/napos_openapi/p/eleme-openapi-java-sdk/git/blob/master/src/main/java/eleme/openapi/sdk/utils/SignatureUtil.java
     *
     * @param str 待加密的字符串
     * @return md5 str
     */
    public static String md5(String str) {
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

    /**
     * 生成京东宙斯平台的签名字符串
     * 宙斯签名规则过程如下:
     * 将所有请求参数按照字母先后顺序排列，例如将access_token,app_key,method,timestamp,v 排序为access_token,app_key,method,timestamp,v
     * 1.把所有参数名和参数值进行拼接，例如：access_tokenxxxapp_keyxxxmethodxxxxxxtimestampxxxxxxvx
     * 2.把appSecret夹在字符串的两端，例如：appSecret+XXXX+appSecret
     * 3.使用MD5进行加密，再转化成大写
     * link: http://open.jd.com/home/home#/doc/common?listId=890
     * link: https://github.com/pingjiang/jd-open-api-sdk-src/blob/master/src/main/java/com/jd/open/api/sdk/DefaultJdClient.java
     *
     * @param appSecret 京东应用密钥
     * @param params    签名参数
     * @return 签名后的字符串
     * @since 1.15.0
     */
    public static String generateJdSignature(String appSecret, Map<String, Object> params) {
        Map<String, Object> treeMap = new TreeMap<>(params);
        StringBuilder signBuilder = new StringBuilder(appSecret);
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            String name = entry.getKey();
            String value = String.valueOf(entry.getValue());
            if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(value)) {
                signBuilder.append(name).append(value);
            }
        }
        signBuilder.append(appSecret);
        return md5(signBuilder.toString()).toUpperCase();
    }
}
