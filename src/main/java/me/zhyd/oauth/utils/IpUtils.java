package me.zhyd.oauth.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取IP的工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.0
 */
public class IpUtils {

    /**
     * 获取IP
     */
    public static String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}