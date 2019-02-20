package me.zhyd.oauth.model;

import lombok.Builder;
import lombok.Data;
import me.zhyd.oauth.request.ResponseStatus;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/1/31 15:43
 * @since 1.8
 */
@Builder
@Data
public class AuthResponse<T> {
    private int code = ResponseStatus.SUCCESS.getCode();
    private String msg = ResponseStatus.SUCCESS.getMsg();
    private T data;
}
