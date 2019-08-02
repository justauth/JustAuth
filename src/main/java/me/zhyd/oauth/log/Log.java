package me.zhyd.oauth.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 针对JustAuth提供的轻量级的日志打印工具
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2019/8/1 17:14
 * @since 1.8
 */
public class Log {

    public static void debug(String msg) {
        debug(msg, null);
    }

    public static void warn(String msg) {
        warn(msg, null);
    }

    public static void error(String msg) {
        error(msg, null);
    }

    public static void debug(String msg, Throwable t) {
        print(Level.DEBUG, msg, t, System.out);
    }

    public static void warn(String msg, Throwable t) {
        print(Level.WARN, msg, t, System.out);
    }

    public static void error(String msg, Throwable t) {
        print(Level.ERROR, msg, t, System.err);
    }

    private static void print(Level level, String msg, Throwable t, PrintStream ps) {
        if (Config.enable) {
            if (level.getLevelNum() >= Config.level.getLevelNum()) {
                ps.println(String.format("%s %s %s [%s] - %s", getDate(), Thread.currentThread().getName(), getCaller(), level, msg));
                writeThrowable(t, ps);
                ps.flush();
            }
        }
    }

    private static String getCaller() {
        int offset = 2;
        StackTraceElement[] stackTraceArr = (new Throwable()).getStackTrace();
        StackTraceElement stackTrace = null;
        if (offset >= stackTraceArr.length) {
            offset = offset - 1;
        }
        stackTrace = stackTraceArr[offset];
        return stackTrace.getClassName() +
            "(" +
            stackTrace.getMethodName() +
            ':' +
            stackTrace.getLineNumber() +
            ")";
    }

    private static String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private static void writeThrowable(Throwable t, PrintStream targetStream) {
        if (t != null) {
            t.printStackTrace(targetStream);
        }
    }

    /**
     * 日志级别
     *
     * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
     * @version 1.0
     * @date 2019/8/2 19:49
     * @since 1.8
     */
    @Getter
    @AllArgsConstructor
    public enum Level {
        /**
         * DEBUG: 普通级别
         */
        DEBUG(10),
        /**
         * WARN: 警告级别
         */
        WARN(30),
        /**
         * ERROR: 异常级别
         */
        ERROR(40);

        private int levelNum;
    }

    /**
     * 日志配置
     *
     * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
     * @version 1.0
     * @date 2019/8/1 17:14
     * @since 1.8
     */
    static class Config {

        /**
         * 需要打印的日志级别
         */
        static Level level = Level.DEBUG;
        /**
         * 是否启用日志打印功能，默认启用
         */
        static boolean enable = true;
    }
}
