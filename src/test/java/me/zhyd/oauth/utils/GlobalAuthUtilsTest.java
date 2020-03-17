package me.zhyd.oauth.utils;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static me.zhyd.oauth.config.AuthDefaultSource.TWITTER;
import static me.zhyd.oauth.utils.GlobalAuthUtils.generateTwitterSignature;
import static me.zhyd.oauth.utils.GlobalAuthUtils.urlEncode;
import static org.junit.Assert.assertEquals;

public class GlobalAuthUtilsTest {

    @Test
    public void testGenerateDingTalkSignature() {
        assertEquals("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe%2FNis5lq9ik%3D", GlobalAuthUtils.generateDingTalkSignature("SHA-256", "1562325753000 "));
    }

    @Test
    public void testUrlDecode() {
        assertEquals("", GlobalAuthUtils.urlDecode(null));
        assertEquals("https://www.foo.bar", GlobalAuthUtils.urlDecode("https://www.foo.bar"));
        assertEquals("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe/Nis5lq9ik=", GlobalAuthUtils.urlDecode("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe%2FNis5lq9ik%3D"));
    }

    @Test
    public void testParseStringToMap() {
        Map expected = new HashMap();
        expected.put("bar", "baz");
        assertEquals(expected, GlobalAuthUtils.parseStringToMap("foo&bar=baz"));
    }

    @Test
    public void testIsHttpProtocol() {
        Assert.assertFalse(GlobalAuthUtils.isHttpProtocol(""));
        Assert.assertFalse(GlobalAuthUtils.isHttpProtocol("foo"));

        Assert.assertTrue(GlobalAuthUtils.isHttpProtocol("http://www.foo.bar"));
    }

    @Test
    public void testIsHttpsProtocol() {
        Assert.assertFalse(GlobalAuthUtils.isHttpsProtocol(""));
        Assert.assertFalse(GlobalAuthUtils.isHttpsProtocol("foo"));

        Assert.assertTrue(GlobalAuthUtils.isHttpsProtocol("https://www.foo.bar"));
    }

    @Test
    public void testIsLocalHost() {
        Assert.assertFalse(GlobalAuthUtils.isLocalHost("foo"));

        Assert.assertTrue(GlobalAuthUtils.isLocalHost(""));
        Assert.assertTrue(GlobalAuthUtils.isLocalHost("127.0.0.1"));
        Assert.assertTrue(GlobalAuthUtils.isLocalHost("localhost"));
    }

    @Test
    public void testGenerateTwitterSignatureForRequestToken() {
        AuthConfig config = AuthConfig.builder()
            .clientId("HD0XLqzi5Wz0G08rh45Cg8mgh")
            .clientSecret("0YX3RH2DnPiT77pgzLzFdfpMKX8ENLIWQKYQ7lG5TERuZNgXN5")
            .redirectUri("https://codinglife.tech")
            .build();
        Map<String, String> params = new HashMap<>();
        params.put("oauth_consumer_key", config.getClientId());
        params.put("oauth_nonce", "sTj7Ivg73u052eXstpoS1AWQCynuDEPN");
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_timestamp", "1569750981");
        params.put("oauth_callback", config.getRedirectUri());
        params.put("oauth_version", "1.0");

        String baseUrl = "https://api.twitter.com/oauth/request_token";
        params.put("oauth_signature", generateTwitterSignature(params, "POST", baseUrl, config.getClientSecret(), null));

        params.forEach((k, v) -> params.put(k, "\"" + urlEncode(v.toString()) + "\""));
        String actual = "OAuth " + GlobalAuthUtils.parseMapToString(params, false).replaceAll("&", ", ");

        assertEquals("OAuth oauth_nonce=\"sTj7Ivg73u052eXstpoS1AWQCynuDEPN\", oauth_signature=\"%2BL5Jq%2FTaKubge04cWw%2B4yfjFlaU%3D\", oauth_callback=\"https%3A%2F%2Fcodinglife.tech\", oauth_consumer_key=\"HD0XLqzi5Wz0G08rh45Cg8mgh\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1569750981\", oauth_version=\"1.0\"", actual);
    }

    @Test
    public void testGenerateTwitterSignatureForAccessToken() {
        AuthConfig config = AuthConfig.builder()
            .clientId("HD0XLqzi5Wz0G08rh45Cg8mgh")
            .clientSecret("0YX3RH2DnPiT77pgzLzFdfpMKX8ENLIWQKYQ7lG5TERuZNgXN5")
            .build();
        AuthCallback authCallback = AuthCallback.builder()
            .oauthToken("W_KLmAAAAAAAxq5LAAABbXxJeD0")
            .oauthVerifier("lYou4gxfA6S5KioUa8VF8HCShzA2nSxp")
            .build();
        Map<String, String> params = new HashMap<>();
        params.put("oauth_consumer_key", config.getClientId());
        params.put("oauth_nonce", "sTj7Ivg73u052eXstpoS1AWQCynuDEPN");
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_timestamp", "1569751082");
        params.put("oauth_token", authCallback.getOauthToken());
        params.put("oauth_verifier", authCallback.getOauthVerifier());
        params.put("oauth_version", "1.0");

        params.put("oauth_signature", generateTwitterSignature(params, "POST", TWITTER.accessToken(), config.getClientSecret(), authCallback
            .getOauthToken()));

        params.forEach((k, v) -> params.put(k, "\"" + urlEncode(v.toString()) + "\""));
        String actual = "OAuth " + GlobalAuthUtils.parseMapToString(params, false).replaceAll("&", ", ");

        assertEquals("OAuth oauth_verifier=\"lYou4gxfA6S5KioUa8VF8HCShzA2nSxp\", oauth_nonce=\"sTj7Ivg73u052eXstpoS1AWQCynuDEPN\", oauth_signature=\"9i0lmWgvphtkl2KcCO9VyZ3K2%2F0%3D\", oauth_token=\"W_KLmAAAAAAAxq5LAAABbXxJeD0\", oauth_consumer_key=\"HD0XLqzi5Wz0G08rh45Cg8mgh\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1569751082\", oauth_version=\"1.0\"", actual);
    }

    @Test
    public void testGenerateTwitterSignatureForUserInfo() {
        AuthConfig config = AuthConfig.builder()
            .clientId("HD0XLqzi5Wz0G08rh45Cg8mgh")
            .clientSecret("0YX3RH2DnPiT77pgzLzFdfpMKX8ENLIWQKYQ7lG5TERuZNgXN5")
            .build();
        AuthToken authToken = AuthToken.builder()
            .oauthToken("1961977975-PcFQaCnpN9h9xqtqHwHlpGBXFrHJ9bOLy7OtGAL")
            .oauthTokenSecret("ffyKe39GYYf8tAyhliSe3QmazpO65kZp5b49xOFX6wHho")
            .userId("1961977975")
            .screenName("pengisgood")
            .build();

        Map<String, String> oauthParams = new HashMap<>();
        oauthParams.put("oauth_consumer_key", config.getClientId());
        oauthParams.put("oauth_nonce", "sTj7Ivg73u052eXstpoS1AWQCynuDEPN");
        oauthParams.put("oauth_signature_method", "HMAC-SHA1");
        oauthParams.put("oauth_timestamp", "1569751082");
        oauthParams.put("oauth_token", authToken.getOauthToken());
        oauthParams.put("oauth_version", "1.0");

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("user_id", authToken.getUserId());
        queryParams.put("screen_name", authToken.getScreenName());
        queryParams.put("include_entities", Boolean.toString(true));

        Map<String, String> params = new HashMap<>(queryParams);
        oauthParams.put("oauth_signature", generateTwitterSignature(params, "GET", TWITTER.userInfo(), config.getClientSecret(), authToken
            .getOauthTokenSecret()));
        oauthParams.forEach((k, v) -> oauthParams.put(k, "\"" + urlEncode(v.toString()) + "\""));

        String actual = "OAuth " + GlobalAuthUtils.parseMapToString(oauthParams, false).replaceAll("&", ", ");
        assertEquals("OAuth oauth_nonce=\"sTj7Ivg73u052eXstpoS1AWQCynuDEPN\", oauth_signature=\"yHHq2J1W5QLAO8gGipnY1V%2Bzxqk%3D\", oauth_token=\"1961977975-PcFQaCnpN9h9xqtqHwHlpGBXFrHJ9bOLy7OtGAL\", oauth_consumer_key=\"HD0XLqzi5Wz0G08rh45Cg8mgh\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1569751082\", oauth_version=\"1.0\"", actual);
    }
}
