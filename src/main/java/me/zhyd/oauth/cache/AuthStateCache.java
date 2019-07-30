package me.zhyd.oauth.cache;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthStateCache {
    private static AuthCache authCache = new AuthDefaultCache();

    /**
     * 存入缓存
     *
     * @param key   缓存key
     * @param value 缓存内容
     */
    public static void cache(String key, String value) {
        authCache.set(key, value);
    }

    /**
     * 存入缓存
     *
     * @param key     缓存key
     * @param value   缓存内容
     * @param timeout 指定缓存过期时间（毫秒）
     */
    public static void cache(String key, String value, long timeout) {
        authCache.set(key, value, timeout);
    }

    /**
     * 获取缓存内容
     *
     * @param key 缓存key
     * @return 缓存内容
     */
    public static String get(String key) {
        return authCache.get(key);
    }

    /**
     * 是否存在key，如果对应key的value值已过期，也返回false
     *
     * @param key 缓存key
     * @return true：存在key，并且value没过期；false：key不存在或者已过期
     */
    public static boolean containsKey(String key) {
        return authCache.containsKey(key);
    }
}
