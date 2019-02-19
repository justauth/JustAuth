package me.zhyd.oauth.utils;

import cn.hutool.core.codec.Base64;
import me.zhyd.oauth.exception.AuthException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DingTalkSignatureUtil {
    /* The default encoding. */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /* Signature method. */
    private static final String ALGORITHM = "HmacSHA256";

    public static String computeSignature(String canonicalString, String secret) {
        try {
            byte[] signData = sign(canonicalString.getBytes(DEFAULT_ENCODING), secret.getBytes(DEFAULT_ENCODING));
            return urlEncode(new String(Base64.encode(signData, false)), DEFAULT_ENCODING);
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

    /**
     * Encode a URL segment with special chars replaced.
     */
    private static String urlEncode(String value, String encoding) {
        if (value == null) {
            return "";
        }

        try {
            String encoded = URLEncoder.encode(value, encoding);
            return encoded.replace("+", "%20").replace("*", "%2A")
                    .replace("~", "%7E").replace("/", "%2F");
        } catch (UnsupportedEncodingException e) {
            throw new AuthException("FailedToEncodeUri", e);
        }
    }
}
