package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthDIngTalkErrorCode;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.DingTalkSignatureUtil;
import me.zhyd.oauth.utils.UrlBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/18 18:43
 * @since 1.8
 */
public class AuthDingTalkRequest extends BaseAuthRequest {

    public AuthDingTalkRequest(AuthConfig config) {
        super(config);
    }

    @Override
    public void authorize(HttpServletResponse response) {
        String authorizeUrl = UrlBuilder.getDingTalkQrConnectUrl(config.getClientId(), config.getRedirectUri());
        try {
            response.sendRedirect(authorizeUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String authorize() {
        return UrlBuilder.getDingTalkQrConnectUrl(config.getClientId(), config.getRedirectUri());
    }

    @Override
    public AuthResponse login(String code) {
        // 根据timestamp, appSecret计算签名值
        String stringToSign = System.currentTimeMillis() + "";
        String urlEncodeSignature = DingTalkSignatureUtil.computeSignature(config.getClientSecret(), stringToSign);
        HttpResponse response = HttpRequest.post(UrlBuilder.getDingTalkUserInfoUrl(urlEncodeSignature, stringToSign, config.getClientId()))
                .body(Objects.requireNonNull(new JSONObject().put("tmp_auth_code", code)))
                .execute();
        String userInfo = response.body();
        JSONObject object = new JSONObject(userInfo);
        AuthDIngTalkErrorCode errorCode = AuthDIngTalkErrorCode.getErrorCode(object.getInt("errcode"));
        if (!AuthDIngTalkErrorCode.EC0.equals(errorCode)) {
            return AuthResponse.builder().code(errorCode.getCode()).msg(errorCode.getDesc()).build();
        }
        object = object.getJSONObject("user_info");
        System.out.println(userInfo);
        return AuthResponse.builder()
                .data(AuthUser.builder()
                        .nickname(object.getStr("nick"))
                        .source(AuthSource.DINGTALK)
                        .build())
                .build();
    }
}
