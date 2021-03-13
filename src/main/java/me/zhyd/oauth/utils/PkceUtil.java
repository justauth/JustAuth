package me.zhyd.oauth.utils;

import java.nio.charset.StandardCharsets;

/**
 * 该配置仅用于支持 PKCE 模式的平台，针对无服务应用，不推荐使用隐式授权，推荐使用 PKCE 模式
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
public class PkceUtil {

    public static String generateCodeVerifier() {
        String randomStr = RandomUtil.randomString(50);
        return Base64Utils.encodeUrlSafe(randomStr);
    }

    /**
     * 适用于 OAuth 2.0 PKCE 增强协议
     *
     * @param codeChallengeMethod s256 / plain
     * @param codeVerifier        客户端生产的校验码
     * @return code challenge
     */
    public static String generateCodeChallenge(String codeChallengeMethod, String codeVerifier) {
        if ("S256".equalsIgnoreCase(codeChallengeMethod)) {
            // https://tools.ietf.org/html/rfc7636#section-4.2
            // code_challenge = BASE64URL-ENCODE(SHA256(ASCII(code_verifier)))
            return newStringUsAscii(Base64Utils.encodeUrlSafe(Sha256.digest(codeVerifier), true));
        } else {
            return codeVerifier;
        }
    }

    public static String newStringUsAscii(byte[] bytes) {
        return new String(bytes, StandardCharsets.US_ASCII);
    }
}
