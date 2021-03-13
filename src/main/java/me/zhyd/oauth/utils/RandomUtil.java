package me.zhyd.oauth.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成随机字符串
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.16.0
 */
public class RandomUtil {

    /**
     * 用于随机选的字符和数字
     */
    public static final String BASE_CHAR_NUMBER = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 获得一个随机的字符串
     *
     * @param length 字符串的长度
     * @return 指定长度的随机字符串
     */
    public static String randomString(int length) {
        final StringBuilder sb = new StringBuilder(length);

        if (length < 1) {
            length = 1;
        }
        int baseLength = BASE_CHAR_NUMBER.length();
        for (int i = 0; i < length; i++) {
            int number = ThreadLocalRandom.current().nextInt(baseLength);
            sb.append(BASE_CHAR_NUMBER.charAt(number));
        }
        return sb.toString();
    }
}
