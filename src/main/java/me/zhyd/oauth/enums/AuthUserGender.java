package me.zhyd.oauth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zhyd.oauth.utils.StringUtils;

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
    MALE("1", "男"),
    FEMALE("0", "女"),
    UNKNOWN("-1", "未知");

    private String code;
    private String desc;

    /**
     * 获取用户的实际性别，常规网站
     *
     * @param originalGender 用户第三方标注的原始性别
     * @return 用户性别
     */
    public static AuthUserGender getRealGender(String originalGender) {
        if (null == originalGender || UNKNOWN.getCode().equals(originalGender)) {
            return UNKNOWN;
        }
        String[] males = {"m", "男", "1", "male"};
        if (Arrays.asList(males).contains(originalGender.toLowerCase())) {
            return MALE;
        }
        return FEMALE;
    }

    /**
     * 获取微信平台用户的实际性别，0表示未定义，1表示男性，2表示女性
     *
     * @param originalGender 用户第三方标注的原始性别
     * @return 用户性别
     * @since 1.13.2
     */
    public static AuthUserGender getWechatRealGender(String originalGender) {
        if (StringUtils.isEmpty(originalGender) || "0".equals(originalGender)) {
            return AuthUserGender.UNKNOWN;
        }
        return getRealGender(originalGender);
    }
}
