package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.*;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 百度账号登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthBaiduRequest extends BaseAuthRequest {

    public AuthBaiduRequest(AuthConfig config) {
        super(config, AuthSource.BAIDU);
    }

    @Override
    protected AuthToken getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getBaiduAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config
                .getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        AuthBaiduErrorCode errorCode = AuthBaiduErrorCode.getErrorCode(accessTokenObject.getString("error"));
        if (!AuthBaiduErrorCode.OK.equals(errorCode)) {
            throw new AuthException(errorCode.getDesc());
        }
        return AuthToken.builder().accessToken(accessTokenObject.getString("access_token")).build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(UrlBuilder.getBaiduUserInfoUrl(accessToken)).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        AuthBaiduErrorCode errorCode = AuthBaiduErrorCode.getErrorCode(object.getString("error"));
        if (!AuthBaiduErrorCode.OK.equals(errorCode)) {
            throw new AuthException(errorCode.getDesc());
        }
        return AuthUser.builder()
                .uuid(object.getString("userid"))
                .username(object.getString("username"))
                .nickname(object.getString("username"))
                .gender(AuthUserGender.getRealGender(object.getString("sex")))
                .token(authToken)
                .source(AuthSource.BAIDU)
                .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return UrlBuilder.getBaiduAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }

    @Override
    public AuthResponse revoke(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(UrlBuilder.getBaiduRevokeUrl(accessToken)).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        if (object.containsKey("error_code")) {
            return AuthResponse.builder()
                    .code(ResponseStatus.FAILURE.getCode())
                    .msg(object.getString("error_msg"))
                    .build();
        }
        ResponseStatus status = object.getIntValue("result") == 1 ? ResponseStatus.SUCCESS : ResponseStatus.FAILURE;
        return AuthResponse.builder().code(status.getCode()).msg(status.getMsg()).build();
    }

}
