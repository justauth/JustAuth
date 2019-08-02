package me.zhyd.oauth.log;

import org.junit.Test;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2019/8/2 19:36
 * @since 1.8
 */
public class LogTest {

    public static void main(String[] args) {
        // 测试正常打印
        Log.debug("[1] This is a test.");
        Log.debug("[1] This is a test.", new NullPointerException("npe"));

        Log.warn("[1] This is a test.");
        Log.warn("[1] This is a test.", new NullPointerException("npe"));

        Log.error("[1] This is a test.");
        Log.error("[1] This is a test.", new NullPointerException("npe"));

        // 测试只打印 error级别的日志
        Log.Config.level = Log.Level.ERROR;

        Log.debug("[2] This is a test.");
        Log.warn("[2] This is a test.");
        Log.error("[2] This is a test.");

        // 测试关闭日志
        Log.Config.enable = false;

        Log.debug("[3] This is a test.");
        Log.warn("[3] This is a test.");
        Log.error("[3] This is a test.");
    }

    /**
     * 1000000: 23135ms
     * 100000: 3016ms
     * 10000: 328ms
     * 1000: 26ms
     */
    @Test
    public void testByThread() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            System.out.println(callMethodByThread());
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

    }

    /**
     * 1000000: 19058ms
     * 100000: 2772ms
     * 10000: 323ms
     * 1000: 29ms
     */
    @Test
    public void testByThrowable() {
        long end = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            System.out.println(callMethodByThrowable());
        }
        long end2 = System.currentTimeMillis();
        System.out.println((end2 - end) + "ms");

    }

    @Test
    public void testBySecurityManager() {
        long end = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            System.out.println(callMethodBySecurityManager());
        }
        long end2 = System.currentTimeMillis();
        System.out.println((end2 - end) + "ms");

    }

    private String callMethodByThread() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.getMethodName());
        }
        return stackTrace[2].getMethodName();
    }

    private String callMethodByThrowable() {
        StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.getMethodName());
        }
        return stackTrace[2].getMethodName();
    }

    private String callMethodBySecurityManager() {
        return new SecurityManager() {
            String getClassName() {
                for (Class clazz : getClassContext()) {
                    System.out.println(clazz);
                }
                return getClassContext()[0].getName();
            }
        }.getClassName();
    }

}
