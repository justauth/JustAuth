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
     * @param authCallback 回调返回的参数
     * @return 所有信息
     */
    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = UrlBuilder.getWeChatAccessTokenUrl(config.getClientId(), config.getClientSecret(), authCallback.getCode());
        return this.getToken(accessTokenUrl);
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        String openId = authToken.getOpenId();

        HttpResponse response = HttpRequest.get(UrlBuilder.getWeChatUserInfoUrl(accessToken, openId)).execute();
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        String location = String.format("%s-%s-%s", object.getString("country"), object.getString("province"), object.getString("city"));

        if (object.containsKey("unionid")){
            authToken.setUnionId(object.getString("unionid"));
        }

        return AuthUser.builder()
                .username(object.getString("nickname"))
                .nickname(object.getString("nickname"))
                .avatar(object.getString("headimgurl"))
                .location(location)
                .uuid(openId)
                .gender(AuthUserGender.getRealGender(object.getString("sex")))
                .token(authToken)
                .source(AuthSource.WECHAT)
                .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return UrlBuilder.getWeChatAuthorizeUrl(config.getClientId(), config.getRedirectUri(), config.getState());
    }

    @Override
    public AuthResponse refresh(AuthToken oldToken) {
        String refreshTokenUrl = UrlBuilder.getWeChatRefreshUrl(config.getClientId(), oldToken.getRefreshToken());
        return AuthResponse.builder()
                .code(ResponseStatus.SUCCESS.getCode())
                .data(this.getToken(refreshTokenUrl))
                .build();
    }

    /**
     * 检查响应内容是否正确
     *
     * @param object 请求响应内容
     */
    private void checkResponse(JSONObject object) {
        if (object.containsKey("errcode")) {
            throw new AuthException(object.getIntValue("errcode"), object.getString("errmsg"));
        }
    }

    /**
     * 获取token，适用于获取access_token和刷新token
     *
     * @param accessTokenUrl 实际请求token的地址
     * @return token对象
     */
    private AuthToken getToken(String accessTokenUrl) {
        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());

        this.checkResponse(accessTokenObject);

        return AuthToken.builder()
                .accessToken(accessTokenObject.getString("access_token"))
                .refreshToken(accessTokenObject.getString("refresh_token"))
                .expireIn(accessTokenObject.getIntValue("expires_in"))
                .openId(accessTokenObject.getString("openid"))
                .build();
    }
}
