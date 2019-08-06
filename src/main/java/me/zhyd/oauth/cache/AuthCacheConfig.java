package me.zhyd.oauth.cache;

/**
 * AuthCache配置类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.10.0
 */
public class AuthCacheConfig {

    /**
     * 默认缓存过期时间：3分钟
     * 鉴于授权过程中，根据个人的操作习惯，或者授权平台的不同（google等），每个授权流程的耗时也有差异，不过单个授权流程一般不会太长
     * 本缓存工具默认的过期时间设置为3分钟，即程序默认认为3分钟内的授权有效，超过3分钟则默认失效，失效后删除
     */
    public static long timeout = 3 * 60 * 1000;

    /**
     * 是否开启定时{@link AuthDefaultCache#pruneCache()}的任务
     */
    public static boolean schedulePrune = true;
}
