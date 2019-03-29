package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthDingTalkErrorCode;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.GlobalAuthUtil;
import me.zhyd.oauth.utils.UrlBuilder;

import java.util.Objects;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthDingTalkRequest extends BaseAuthRequest {

    public AuthDingTalkRequest(AuthConfig config) {
        super(config, AuthSource.DINGTALK);
    }

    @Override
    protected String getAccessToken(String code) {
        throw new AuthException(ResponseStatus.NOT_IMPLEMENTED);
    }

    @Override
    protected AuthUser getUserInfo(String code) {
        // 根据timestamp, appSecret计算签名值
        String stringToSign = System.currentTimeMillis() + "";
        String urlEncodeSignature = GlobalAuthUtil.generateDingTalkSignature(config.getClientSecret(), stringToSign);
        HttpResponse response = HttpRequest.post(UrlBuilder.getDingTalkUserInfoUrl(urlEncodeSignature, stringToSign, config.getClientId()))
                .body(Objects.requireNonNull(new JSONObject().put("tmp_auth_code", code)))
                .execute();
        String userInfo = response.body();
        JSONObject object = new JSONObject(userInfo);
        AuthDingTalkErrorCode errorCode = AuthDingTalkErrorCode.getErrorCode(object.getInt("errcode"));
        if (!AuthDingTalkErrorCode.EC0.equals(errorCode)) {
            throw new AuthException(errorCode.getDesc());
        }
        object = object.getJSONObject("user_info");
        return AuthUser.builder()
                .nickname(object.getStr("nick"))
                .source(AuthSource.DINGTALK)
                .build();
    }

    @Override
    public AuthResponse login(String code) {
        return AuthResponse.builder()
                .data(this.getUserInfo(code))
                .build();
    }
}
