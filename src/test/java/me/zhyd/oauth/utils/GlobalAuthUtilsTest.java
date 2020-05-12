package me.zhyd.oauth.utils;

import com.alibaba.fastjson.JSON;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static me.zhyd.oauth.config.AuthDefaultSource.TWITTER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GlobalAuthUtilsTest {

    @Test
    public void generateDingTalkSignature() {
        assertEquals("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe%2FNis5lq9ik%3D", GlobalAuthUtils.generateDingTalkSignature("SHA-256", "1562325753000 "));
    }

    @Test
    public void urlDecode() {
        assertEquals("", GlobalAuthUtils.urlDecode(null));
        assertEquals("https://www.foo.bar", GlobalAuthUtils.urlDecode("https://www.foo.bar"));
        assertEquals("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe/Nis5lq9ik=", GlobalAuthUtils.urlDecode("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe%2FNis5lq9ik%3D"));
    }

    @Test
    public void parseStringToMap() {
        Map expected = new HashMap();
        expected.put("bar", "baz");
        assertEquals(expected, GlobalAuthUtils.parseStringToMap("foo&bar=baz"));
    }

    @Test
    public void isHttpProtocol() {
        Assert.assertFalse(GlobalAuthUtils.isHttpProtocol(""));
        Assert.assertFalse(GlobalAuthUtils.isHttpProtocol("foo"));

        Assert.assertTrue(GlobalAuthUtils.isHttpProtocol("http://www.foo.bar"));
    }

    @Test
    public void isHttpsProtocol() {
        Assert.assertFalse(GlobalAuthUtils.isHttpsProtocol(""));
        Assert.assertFalse(GlobalAuthUtils.isHttpsProtocol("foo"));

        Assert.assertTrue(GlobalAuthUtils.isHttpsProtocol("https://www.foo.bar"));
    }

    @Test
    public void isLocalHost() {
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
        params.put("oauth_signature", GlobalAuthUtils.generateTwitterSignature(params, "POST", baseUrl, config.getClientSecret(), null));

        params.forEach((k, v) -> params.put(k, "\"" + GlobalAuthUtils.urlEncode(v) + "\""));
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
            .oauth_token("W_KLmAAAAAAAxq5LAAABbXxJeD0")
            .oauth_verifier("lYou4gxfA6S5KioUa8VF8HCShzA2nSxp")
            .build();
        Map<String, String> params = new HashMap<>();
        params.put("oauth_consumer_key", config.getClientId());
        params.put("oauth_nonce", "sTj7Ivg73u052eXstpoS1AWQCynuDEPN");
        params.put("oauth_signature_method", "HMAC-SHA1");
        params.put("oauth_timestamp", "1569751082");
        params.put("oauth_token", authCallback.getOauth_token());
        params.put("oauth_verifier", authCallback.getOauth_verifier());
        params.put("oauth_version", "1.0");

        params.put("oauth_signature", GlobalAuthUtils.generateTwitterSignature(params, "POST", TWITTER.accessToken(), config.getClientSecret(), authCallback
            .getOauth_token()));

        params.forEach((k, v) -> params.put(k, "\"" + GlobalAuthUtils.urlEncode(v) + "\""));
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
        oauthParams.put("oauth_signature", GlobalAuthUtils.generateTwitterSignature(params, "GET", TWITTER.userInfo(), config.getClientSecret(), authToken
            .getOauthTokenSecret()));
        oauthParams.forEach((k, v) -> oauthParams.put(k, "\"" + GlobalAuthUtils.urlEncode(v) + "\""));

        String actual = "OAuth " + GlobalAuthUtils.parseMapToString(oauthParams, false).replaceAll("&", ", ");
        assertEquals("OAuth oauth_nonce=\"sTj7Ivg73u052eXstpoS1AWQCynuDEPN\", oauth_signature=\"yHHq2J1W5QLAO8gGipnY1V%2Bzxqk%3D\", oauth_token=\"1961977975-PcFQaCnpN9h9xqtqHwHlpGBXFrHJ9bOLy7OtGAL\", oauth_consumer_key=\"HD0XLqzi5Wz0G08rh45Cg8mgh\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1569751082\", oauth_version=\"1.0\"", actual);
    }

    @Test
    public void md5() {
        String str = "helloworld,iamjustauth";
        String md5Str = GlobalAuthUtils.md5(str);
        assertEquals("b0d923de4289b69976448cac718528b8", md5Str);
    }

    @Test
    public void treemap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", "1");
        parameters.put("screen_name", "222");
        parameters.put("a", "222");
        parameters.put("include_entities", Boolean.toString(true));
        final Map<String, Object> sorted = new TreeMap<>(parameters);
        assertEquals("{\"a\":\"222\",\"include_entities\":\"true\",\"screen_name\":\"222\",\"user_id\":\"1\"}", JSON.toJSONString(sorted));
    }

    @Test
    public void urlEncode() {
        assertEquals("", GlobalAuthUtils.urlEncode(null));
        assertEquals("https%3A%2F%2Fwww.foo.bar", GlobalAuthUtils.urlEncode("https://www.foo.bar"));
        assertEquals("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe%252FNis5lq9ik%253D", GlobalAuthUtils.urlEncode("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe%2FNis5lq9ik%3D"));
    }

    @Test
    public void parseMapToString() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("user_id", "1");
        parameters.put("screen_name", "史上最全的第三方授权登录库");
        parameters.put("include_entities", Boolean.toString(true));
        assertEquals("user_id=1&screen_name=史上最全的第三方授权登录库&include_entities=true", GlobalAuthUtils.parseMapToString(parameters, false));
        assertEquals("user_id=1&screen_name=%E5%8F%B2%E4%B8%8A%E6%9C%80%E5%85%A8%E7%9A%84%E7%AC%AC%E4%B8%89%E6%96%B9%E6%8E%88%E6%9D%83%E7%99%BB%E5%BD%95%E5%BA%93&include_entities=true", GlobalAuthUtils.parseMapToString(parameters, true));
        assertEquals("", GlobalAuthUtils.parseMapToString(null, true));
    }

    @Test
    public void generateNonce() {
        assertEquals(10, GlobalAuthUtils.generateNonce(10).length());
    }

    @Test
    public void getTimestamp() {
        assertNotNull(GlobalAuthUtils.getTimestamp());
    }

    @Test
    public void generateTwitterSignature() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", "你好");
        queryParams.put("gender", "male");

        assertEquals("J6MAQH1kcgUdj2jmygN3rdfI4lo=", GlobalAuthUtils.generateTwitterSignature(queryParams, "GET", TWITTER.userInfo(), "xxxxx", "xxxxx"));
    }

    @Test
    public void generateElemeSignature() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "你好");
        parameters.put("gender", "male");

        String appKey = "appKey";
        String secret = "appKey";
        long timestamp = 1233456789;
        String action = "appKey";
        String token = "appKey";

        assertEquals("26FEB8BF7E84FED2619D9C5D97F421BD", GlobalAuthUtils.generateElemeSignature(appKey, secret, timestamp, action, token, parameters));
    }

    @Test
    public void generateJdSignature() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "你好");
        parameters.put("gender", "male");

        String appSecret = "appKey";

        assertEquals("FE04EC03BA8A619802CF309959C2B43F", GlobalAuthUtils.generateJdSignature(appSecret, parameters));
    }
}
