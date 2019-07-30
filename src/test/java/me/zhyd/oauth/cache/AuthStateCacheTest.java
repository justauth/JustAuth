package me.zhyd.oauth.cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class AuthStateCacheTest {

    @Test
    public void cache1() throws InterruptedException {
        AuthStateCache.cache("key", "value");
        Assert.assertEquals(AuthStateCache.get("key"), "value");

        TimeUnit.MILLISECONDS.sleep(4);
        Assert.assertEquals(AuthStateCache.get("key"), "value");
    }

    @Test
    public void cache2() throws InterruptedException {
        AuthStateCache.cache("key", "value", 10);
        Assert.assertEquals(AuthStateCache.get("key"), "value");

        // 没过期
        TimeUnit.MILLISECONDS.sleep(5);
        Assert.assertEquals(AuthStateCache.get("key"), "value");

        // 过期
        TimeUnit.MILLISECONDS.sleep(6);
        Assert.assertNull(AuthStateCache.get("key"));
    }
}
