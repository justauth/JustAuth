package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 京东登录
 *
 * @author harry.lee (harryleexyz@qq.com)
 * @since
 */
public class AuthJdRequest extends AuthDefaultRequest {

    public AuthJdRequest(AuthConfig config) {
        super(config, AuthDefaultSource.JD);
    }

    public AuthJdRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.JD, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = HttpRequest.post(source.accessToken())
            .form("app_key", config.getClientId())
            .form("app_secret", config.getClientSecret())
            .form("grant_type", "authorization_code")
            .form("code", authCallback.getCode())
            .execute();
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .expireIn(object.getIntValue("expires_in"))
            .refreshToken(object.getString("refresh_token"))
            .scope(object.getString("scope"))
            .openId(object.getString("open_id"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        return null;
    }

    @Override
    public AuthResponse refresh(AuthToken oldToken) {
        HttpResponse response = HttpRequest.post(source.refresh())
            .form("app_key", config.getClientId())
            .form("app_secret", config.getClientSecret())
            .form("grant_type", "refresh_token")
            .form("refresh_token", oldToken.getRefreshToken())
            .execute();
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .expireIn(object.getIntValue("expires_in"))
                .refreshToken(object.getString("refresh_token"))
                .scope(object.getString("scope"))
                .openId(object.getString("open_id"))
                .build())
            .build();
    }

    private void checkResponse(JSONObject object) {
        if (object.containsKey("msg")) {
            throw new AuthException(object.getString("msg"));
        }
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("app_key", config.getClientId())
            .queryParam("response_type", "code")
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("scope", "snsapi_base")
            .queryParam("state", getRealState(state))
            .build();
    }

}
