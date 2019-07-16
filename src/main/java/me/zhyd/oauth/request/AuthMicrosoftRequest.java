package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.*;
import me.zhyd.oauth.url.MicrosoftUrlBuilder;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 微软登录
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @version 1.5
 * @since 1.5
 */
public class AuthMicrosoftRequest extends BaseAuthRequest {
    public AuthMicrosoftRequest(AuthConfig config) {
        super(config, AuthSource.MICROSOFT, new MicrosoftUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = this.urlBuilder.getAccessTokenUrl(authCallback.getCode());

        return getToken(accessTokenUrl);
    }

    /**
     * 获取token，适用于获取access_token和刷新token
     *
     * @param accessTokenUrl 实际请求token的地址
     * @return token对象
     */
    private AuthToken getToken(String accessTokenUrl) {
        Map<String, Object> paramMap = new HashMap<>(6);
        HttpUtil.decodeParamMap(accessTokenUrl, "UTF-8").forEach(paramMap::put);
        HttpResponse response = HttpRequest.post(accessTokenUrl)
                .header("Host", "https://login.microsoftonline.com")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .form(paramMap)
                .execute();
        String accessTokenStr = response.body();
        JSONObject accessTokenObject = JSONObject.parseObject(accessTokenStr);

        this.checkResponse(accessTokenObject);

        return AuthToken.builder()
                .accessToken(accessTokenObject.getString("access_token"))
                .expireIn(accessTokenObject.getIntValue("expires_in"))
                .scope(accessTokenObject.getString("scope"))
                .tokenType(accessTokenObject.getString("token_type"))
                .refreshToken(accessTokenObject.getString("refresh_token"))
                .build();
    }

    private void checkResponse(JSONObject response) {
        if (response.containsKey("error")) {
            throw new AuthException(response.getString("error_description"));
        }
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String token = authToken.getAccessToken();
        String tokenType = authToken.getTokenType();
        String jwt = tokenType + " " + token;
        HttpResponse response = HttpRequest.get(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder().build()))
                .header("Authorization", jwt)
                .execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        return AuthUser.builder()
                .uuid(object.getString("id"))
                .username(object.getString("userPrincipalName"))
                .nickname(object.getString("displayName"))
                .location(object.getString("officeLocation"))
                .email(object.getString("mail"))
                .gender(AuthUserGender.UNKNOW)
                .token(authToken)
                .source(AuthSource.MICROSOFT)
                .build();
    }

    /**
     * 刷新access token （续期）
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    @Override
    public AuthResponse refresh(AuthToken authToken) {
        String refreshTokenUrl = this.urlBuilder.getRefreshUrl(authToken.getRefreshToken());

        return AuthResponse.builder().code(ResponseStatus.SUCCESS.getCode()).data(getToken(refreshTokenUrl)).build();
    }
}
