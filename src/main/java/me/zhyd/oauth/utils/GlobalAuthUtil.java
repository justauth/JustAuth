package me.zhyd.oauth.utils;

import cn.hutool.core.codec.Base64;
import me.zhyd.oauth.exception.AuthException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局的工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class GlobalAuthUtil {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String ALGORITHM = "HmacSHA256";

    public static String generateDingTalkSignature(String canonicalString, String secret) {
        try {
            byte[] signData = sign(canonicalString.getBytes(DEFAULT_ENCODING), secret.getBytes(DEFAULT_ENCODING));
            return urlEncode(new String(Base64.encode(signData, false)));
        } catch (UnsupportedEncodingException ex) {
            throw new AuthException("Unsupported algorithm: " + DEFAULT_ENCODING, ex);
        }
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

    private static String urlEncode(String value) {
        if (value == null) {
            return "";
        }

        try {
            String encoded = URLEncoder.encode(value, GlobalAuthUtil.DEFAULT_ENCODING);
            return encoded.replace("+", "%20").replace("*", "%2A")
                    .replace("~", "%7E").replace("/", "%2F");
        } catch (UnsupportedEncodingException e) {
            throw new AuthException("Failed To Encode Uri", e);
        }
    }

    public static String urlDecode(String value) {
        if (value == null) {
            return "";
        }
        try {
            return URLDecoder.decode(value, GlobalAuthUtil.DEFAULT_ENCODING);
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
}
