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
    MALE(1, "男"), FEMALE(0, "女"), UNKNOWN(-1, "未知");
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
