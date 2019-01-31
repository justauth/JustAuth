package me.zhyd.oauth.model;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 17:24
 * @since 1.8
 */
public enum AuthUserGender {
    MALE(1, "男"), FEMALE(0, "女"), UNKNOW(-1, "");
    private int code;
    private String desc;

    AuthUserGender(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthUserGender getRealGender(String code) {
        if (code == null) {
            return UNKNOW;
        }
        if ("m".equals(code) || "男".equals(code) || "1".equals(code) || "male".equalsIgnoreCase(code)) {
            return MALE;
        }
        if ("f".equals(code) || "女".equals(code) || "0".equals(code) || "female".equalsIgnoreCase(code)) {
            return FEMALE;
        }
        return UNKNOW;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
