package me.zhyd.oauth.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GlobalAuthUtilTest {

    @Test
    public void testGenerateDingTalkSignature() {
        Assert.assertEquals("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe%2FNis5lq9ik%3D",
                GlobalAuthUtil.generateDingTalkSignature(
                        "SHA-256", "1562325753000 "));
    }

    @Test
    public void testUrlDecode() {
        Assert.assertEquals("", GlobalAuthUtil.urlDecode(null));
        Assert.assertEquals("https://www.foo.bar",
                GlobalAuthUtil.urlDecode("https://www.foo.bar"));
        Assert.assertEquals("mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe/Nis5lq9ik=",
                GlobalAuthUtil.urlDecode(
                        "mLTZEMqIlpAA3xtJ43KcRT0EDLwgSamFe%2FNis5lq9ik%3D"));
    }

    @Test
    public void testParseStringToMap() {
        Map expected = new HashMap();
        expected.put("bar", "baz");
        Assert.assertEquals(expected,
                GlobalAuthUtil.parseStringToMap("foo&bar=baz"));
    }

    @Test
    public void testIsHttpProtocol() {
        Assert.assertFalse(GlobalAuthUtil.isHttpProtocol(""));
        Assert.assertFalse(GlobalAuthUtil.isHttpProtocol("foo"));

        Assert.assertTrue(GlobalAuthUtil.isHttpProtocol("http://www.foo.bar"));
    }

    @Test
    public void testIsHttpsProtocol() {
        Assert.assertFalse(GlobalAuthUtil.isHttpsProtocol(""));
        Assert.assertFalse(GlobalAuthUtil.isHttpsProtocol("foo"));

        Assert.assertTrue(
                GlobalAuthUtil.isHttpsProtocol("https://www.foo.bar"));
    }

    @Test
    public void testIsLocalHost() {
        Assert.assertFalse(GlobalAuthUtil.isLocalHost("foo"));

        Assert.assertTrue(GlobalAuthUtil.isLocalHost(""));
        Assert.assertTrue(GlobalAuthUtil.isLocalHost("127.0.0.1"));
        Assert.assertTrue(GlobalAuthUtil.isLocalHost("localhost"));
    }
}
