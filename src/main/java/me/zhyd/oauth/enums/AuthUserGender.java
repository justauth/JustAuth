package me.zhyd.oauth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 用户性别
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.8
 */
@Getter
@AllArgsConstructor
public enum AuthUserGender {
    /**
     * MALE/FAMALE为正常值，通过{@link AuthUserGender#getRealGender(String)}方法获取真实的性别
     * UNKNOWN为容错值，部分平台不会返回用户性别，为了方便统一，使用UNKNOWN标记所有未知或不可测的用户性别信息
     */
    MALE(1, "男"),
    FEMALE(0, "女"),
    UNKNOWN(-1, "未知");

    private int code;
    private String desc;

    public static AuthUserGender getRealGender(String code) {
        if (code == null) {
            return UNKNOWN;
        }
        String[] males = {"m", "男", "1", "male"};
        if (Arrays.asList(males).contains(code.toLowerCase())) {
            return MALE;
        }
        String[] females = {"f", "女", "0", "female"};
        if (Arrays.asList(females).contains(code.toLowerCase())) {
            return FEMALE;
        }
        return UNKNOWN;
    }
}
