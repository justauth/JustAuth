package me.zhyd.oauth.model;

import me.zhyd.oauth.utils.StringUtils;

/**
 * 百度授权登录时的异常状态码
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public enum AuthBaiduErrorCode {
    OK("ok", "ok", "ok"),
    INVALID_REQUEST("invalid_request", "invalid refresh token", "请求缺少某个必需参数，包含一个不支持的参数或参数值，或者格式不正确。"),
    INVALID_CLIENT("invalid_client", "unknown client id", "client_id”、“client_secret”参数无效。"),
    INVALID_GRANT("invalid_grant", "The provided authorization grant is revoked", "提供的Access Grant是无效的、过期的或已撤销的，例如，Authorization Code无效(一个授权码只能使用一次)、Refresh Token无效、redirect_uri与获取Authorization Code时提供的不一致、Devie Code无效(一个设备授权码只能使用一次)等。"),
    UNAUTHORIZED_CLIENT("unauthorized_client", "The client is not authorized to use this authorization grant type", "应用没有被授权，无法使用所指定的grant_type。"),
    UNSUPPORTED_GRANT_TYPE("unsupported_grant_type", "The authorization grant type is not supported", "“grant_type”百度OAuth2.0服务不支持该参数。"),
    INVALID_SCOPE("invalid_scope", "The requested scope is exceeds the scope granted by the resource owner", "请求的“scope”参数是无效的、未知的、格式不正确的、或所请求的权限范围超过了数据拥有者所授予的权限范围。"),
    EXPIRED_TOKEN("expired_token", "refresh token has been used", "提供的Refresh Token已过期"),
    REDIRECT_URI_MISMATCH("redirect_uri_mismatch", "Invalid redirect uri", "“redirect_uri”所在的根域与开发者注册应用时所填写的根域名不匹配。"),
    UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type", "The response type is not supported", "“response_type”参数值不为百度OAuth2.0服务所支持，或者应用已经主动禁用了对应的授权模式"),
    SLOW_DOWN("slow_down", "The device is polling too frequently", "Device Flow中，设备通过Device Code换取Access Token的接口过于频繁，两次尝试的间隔应大于5秒。"),
    AUTHORIZATION_PENDING("authorization_pending", "User has not yet completed the authorization", "Device Flow中，用户还没有对Device Code完成授权操作。"),
    AUTHORIZATION_DECLINED("authorization_declined", "User has declined the authorization", "Device Flow中，用户拒绝了对Device Code的授权操作。"),
    INVALID_REFERER("invalid_referer", "Invalid Referer", "Implicit Grant模式中，浏览器请求的Referer与根域名绑定不匹配");

    private String code;
    private String msg;
    private String desc;

    AuthBaiduErrorCode(String code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public static AuthBaiduErrorCode getErrorCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return OK;
        }
        AuthBaiduErrorCode[] errorCodes = AuthBaiduErrorCode.values();
        for (AuthBaiduErrorCode errorCode : errorCodes) {
            if (code.equalsIgnoreCase(errorCode.getCode())) {
                return errorCode;
            }
        }
        return OK;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDesc() {
        return desc;
    }
}
