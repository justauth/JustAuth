package me.zhyd.oauth.utils;

import org.junit.Test;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/7/19 15:52
 * @since 1.8
 */
public class CustomTest {

    /**
     * 1000000: 23135ms
     * 100000: 3016ms
     * 10000: 328ms
     * 1000: 26ms
     */
    @Test
    public void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            callMethod();
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
    public void test2() {
        long end = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            callMethod2();
        }
        long end2 = System.currentTimeMillis();
        System.out.println((end2 - end) + "ms");

    }

    public String callMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//        for (StackTraceElement stackTraceElement : stackTrace) {
//            System.out.println(stackTraceElement.getMethodName());
//        }
        return stackTrace[2].getMethodName();
    }

    public String callMethod2() {
        StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
//        for (StackTraceElement stackTraceElement : stackTrace) {
//            System.out.println(stackTraceElement.getMethodName());
//        }
        return stackTrace[2].getMethodName();
    }
}
