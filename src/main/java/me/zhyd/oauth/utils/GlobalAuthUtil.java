package me.zhyd.oauth.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import me.zhyd.oauth.exception.AuthException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 全局的工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.8
 */
public class GlobalAuthUtil {
    private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
    private static final String ALGORITHM = "HmacSHA256";

    public static String generateDingTalkSignature(String secretKey, String timestamp) {
        byte[] signData = sign(secretKey.getBytes(DEFAULT_ENCODING), timestamp.getBytes(DEFAULT_ENCODING));
        return urlEncode(new String(Base64.encode(signData, false)));
    }

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
  
    public static Map<String, Object> parseQueryToMap(String url) {
        Map<String, Object> paramMap = new HashMap<>();
        HttpUtil.decodeParamMap(url, "UTF-8").forEach(paramMap::put);
        return paramMap;
    }

    public static boolean isHttpProtocol(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith("http://");
    }

    public static boolean isHttpsProtocol(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        return url.startsWith("https://");
    }

    public static boolean isLocalHost(String url) {
        return StringUtils.isEmpty(url) || url.contains("127.0.0.1") || url.contains("localhost");
    }

}
