package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.*;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/19 18:43
 * @since 1.8
 */
public class AuthBaiduRequest extends BaseAuthRequest {

    public AuthBaiduRequest(AuthConfig config) {
        super(config, AuthSource.BAIDU);
    }

    @Override
    protected String getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getBaiduAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config.getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        AuthBaiduErrorCode errorCode = AuthBaiduErrorCode.getErrorCode(accessTokenObject.getString("error"));
        if (!AuthBaiduErrorCode.OK.equals(errorCode)) {
            throw new AuthException(errorCode.getDesc());
        }
        return accessTokenObject.getString("access_token");
    }

    @Override
    protected AuthUser getUserInfo(String accessToken) {
        HttpResponse response = HttpRequest.get(UrlBuilder.getBaiduUserInfoUrl(accessToken)).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        AuthBaiduErrorCode errorCode = AuthBaiduErrorCode.getErrorCode(object.getString("error"));
        if (!AuthBaiduErrorCode.OK.equals(errorCode)) {
            throw new AuthException(errorCode.getDesc());
        }
        return AuthUser.builder()
                .username(object.getString("username"))
                .nickname(object.getString("username"))
                .gender(AuthUserGender.getRealGender(object.getString("sex")))
                .accessToken(accessToken)
                .source(AuthSource.BAIDU)
                .build();
    }

    @Override
    public AuthResponse revoke(String accessToken) {
        HttpResponse response = HttpRequest.get(UrlBuilder.getBaiduRevokeUrl(accessToken)).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        ResponseStatus status = object.getIntValue("result") == 1 ? ResponseStatus.SUCCESS : ResponseStatus.FAILURE;
        return AuthResponse.builder().code(status.getCode()).msg(status.getMsg()).build();
    }

}
