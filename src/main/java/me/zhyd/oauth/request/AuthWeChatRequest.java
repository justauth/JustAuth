package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.*;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 微信登录
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @version 1.0
 * @since 1.8
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
    protected AuthToken getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getWeChatAccessTokenUrl(config.getClientId(), config.getClientSecret(), code);
        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (!accessTokenObject.containsKey("access_token") || !accessTokenObject.containsKey("openid") || !accessTokenObject
                .containsKey("refresh_token")) {
            throw new AuthException("Unable to get access_token or openid or refresh_token from wechat using code [" + code + "]");
        }
        JSONObject object = JSONObject.parseObject(response.body());
        return AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .refreshToken(object.getString("refresh_token"))
                .openId(object.getString("openid"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        String openId = authToken.getOpenId();

        HttpResponse response = HttpRequest.get(UrlBuilder.getWeChatUserInfoUrl(accessToken, openId)).execute();
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
                .token(authToken)
                .source(AuthSource.WECHAT)
                .build();
    }

    @Override
    public AuthResponse refresh(AuthToken authToken) {
        String refreshToken = authToken.getRefreshToken();
        HttpResponse response = HttpRequest.get(UrlBuilder.getWeChatRefreshUrl(config.getClientId(), refreshToken))
                .execute();

        JSONObject object = JSONObject.parseObject(response.body());
        if (object.containsKey("errcode")) {
            throw new AuthException(object.getString("errmsg"));
        }

        return AuthResponse.builder().data(object).build();
    }
}
