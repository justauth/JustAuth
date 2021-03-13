package me.zhyd.oauth.config;

import me.zhyd.oauth.log.Log;

/**
 * JustAuth 日志配置类
 *
 * @author HeJin
 */
public class JustAuthLogConfig {

    /**
     * 设置日志级别
     *
     * @param level 日志级别
     */
    public static void setLevel(Log.Level level) {
        Log.Config.level = level;
    }

    /**
     * 关闭日志
     */
    public static void disable() {
        Log.Config.enable = false;
    }

    /**
     * 开启日志
     */
    public static void enable() {
        Log.Config.enable = true;
    }
}
