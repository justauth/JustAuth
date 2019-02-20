package me.zhyd.oauth.request;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 16:37
 * @since 1.8
 */
public enum ResponseStatus {
    SUCCESS(2000, "Success"),
    FAILURE(5000, "Authentication failure"),
    NOT_IMPLEMENTED(5001, "Not Implemented"),
    UNSUPPORTED(5002, "Unsupported authentication, please check the configuration."),
    ;

    private int code;
    private String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

