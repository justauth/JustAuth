package me.zhyd.oauth.utils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 高性能的创建UUID的工具类，https://github.com/lets-mica/mica
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.9.3
 */
public class UuidUtils {

    /**
     * All possible chars for representing a number as a String
     * copy from mica：https://github.com/lets-mica/mica/blob/master/mica-core/src/main/java/net/dreamlu/mica/core/utils/NumberUtil.java#L113
     */
    private final static byte[] DIGITS = {
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z'
    };

    /**
     * 生成uuid，采用 jdk 9 的形式，优化性能
     * copy from mica：https://github.com/lets-mica/mica/blob/master/mica-core/src/main/java/net/dreamlu/mica/core/utils/StringUtil.java#L335
     * <p>
     * 关于mica uuid生成方式的压测结果，可以参考：https://github.com/lets-mica/mica-jmh/wiki/uuid
     *
     * @return UUID
     */
    public static String getUUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long lsb = random.nextLong();
        long msb = random.nextLong();
        byte[] buf = new byte[32];
        formatUnsignedLong(lsb, buf, 20, 12);
        formatUnsignedLong(lsb >>> 48, buf, 16, 4);
        formatUnsignedLong(msb, buf, 12, 4);
        formatUnsignedLong(msb >>> 16, buf, 8, 4);
        formatUnsignedLong(msb >>> 32, buf, 0, 8);
        return new String(buf, StandardCharsets.UTF_8);
    }

    /**
     * copy from mica：https://github.com/lets-mica/mica/blob/master/mica-core/src/main/java/net/dreamlu/mica/core/utils/StringUtil.java#L348
     */
    private static void formatUnsignedLong(long val, byte[] buf, int offset, int len) {
        int charPos = offset + len;
        int radix = 1 << 4;
        int mask = radix - 1;
        do {
            buf[--charPos] = DIGITS[((int) val) & mask];
            val >>>= 4;
        } while (charPos > offset);
    }
}
