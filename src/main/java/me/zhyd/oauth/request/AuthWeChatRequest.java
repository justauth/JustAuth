package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * <p>
 * 微信登录
 * </p>
 *
 * @package: me.zhyd.oauth.request
 * @description: 微信登录
 * @author: yangkai.shen
 * @date: Created in 2019-05-17 11:11
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class AuthWeChatRequest extends BaseAuthRequest {
    public AuthWeChatRequest(AuthConfig config) {
        super(config, AuthSource.WECHAT);
    }

    /**
     * 微信的特殊性，此时返回的信息同时包含 openid 和 access_token
     *
     * @param code 授权码
     * @return 所有信息
     */
    @Override
    protected String getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getWeChatAccessTokenUrl(config.getClientId(), config.getClientSecret(), code);
        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (!accessTokenObject.containsKey("access_token") || !accessTokenObject.containsKey("openid") || !accessTokenObject
                .containsKey("refresh_token")) {
            throw new AuthException("Unable to get access_token or openid or refresh_token from wechat using code [" + code + "]");
        }
        return response.body();
    }

    @Override
    protected AuthUser getUserInfo(String accessToken) {
        String token = this.getToken(accessToken);
        String openId = this.getOpenId(accessToken);

        HttpResponse response = HttpRequest.get(UrlBuilder.getWeChatUserInfoUrl(token, openId)).execute();
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.containsKey("errcode")) {
            throw new AuthException(object.getString("errmsg"));
        }

        return AuthUser.builder()
                .username(object.getString("nickname"))
                .nickname(object.getString("nickname"))
                .avatar(object.getString("headimgurl"))
                .location(object.getString("country") + "-" + object.getString("province") + "-" + object.getString("city"))
                .gender(AuthUserGender.getRealGender(object.getString("sex")))
                .accessToken(accessToken)
                .source(AuthSource.WECHAT)
                .build();
    }

    /**
     * 刷新access token （续期）
     *
     * @param accessToken 登录成功后返回的accessToken
     * @return AuthResponse
     */
    @Override
    public AuthResponse refresh(String accessToken) {
        String refreshToken = getRefreshToken(accessToken);
        HttpResponse response = HttpRequest.get(UrlBuilder.getWeChatRefreshUrl(config.getClientId(), refreshToken))
                .execute();

        JSONObject object = JSONObject.parseObject(response.body());
        if (object.containsKey("errcode")) {
            throw new AuthException(object.getString("errmsg"));
        }

        return AuthResponse.builder().data(object).build();
    }

    private String getRefreshToken(String accessToken) {
        JSONObject accessTokenObject = JSONObject.parseObject(accessToken);
        return accessTokenObject.getString("refresh_token");
    }

    private String getOpenId(String accessToken) {
        JSONObject accessTokenObject = JSONObject.parseObject(accessToken);
        return accessTokenObject.getString("openid");
    }

    private String getToken(String accessToken) {
        JSONObject accessTokenObject = JSONObject.parseObject(accessToken);
        return accessTokenObject.getString("access_token");
    }
}
