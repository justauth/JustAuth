package me.zhyd.oauth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 今日头条授权登录时的异常状态码
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.8
 */
@Getter
@AllArgsConstructor
public enum AuthToutiaoErrorCode {
    /**
     * 0：正常；
     * other：调用异常，具体异常内容见{@code desc}
     */
    EC0(0, "接口调用成功"),
    EC1(1, "API配置错误，未传入Client Key"),
    EC2(2, "API配置错误，Client Key错误，请检查是否和开放平台的ClientKey一致"),
    EC3(3, "没有授权信息"),
    EC4(4, "响应类型错误"),
    EC5(5, "授权类型错误"),
    EC6(6, "client_secret错误"),
    EC7(7, "authorize_code过期"),
    EC8(8, "指定url的scheme不是https"),
    EC9(9, "接口内部错误，请联系头条技术"),
    EC10(10, "access_token过期"),
    EC11(11, "缺少access_token"),
    EC12(12, "参数缺失"),
    EC13(13, "url错误"),
    EC21(21, "域名与登记域名不匹配"),
    EC999(999, "未知错误，请联系头条技术"),
    ;

    private int code;
    private String desc;

    public static AuthToutiaoErrorCode getErrorCode(int errorCode) {
        AuthToutiaoErrorCode[] errorCodes = AuthToutiaoErrorCode.values();
        for (AuthToutiaoErrorCode code : errorCodes) {
            if (code.getCode() == errorCode) {
                return code;
            }
        }
        return EC999;
    }
}
