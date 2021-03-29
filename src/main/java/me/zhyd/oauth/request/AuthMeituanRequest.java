package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 美团登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.12.0
 */
public class AuthMeituanRequest extends AuthDefaultRequest {

    public AuthMeituanRequest(AuthConfig config) {
        super(config, AuthDefaultSource.MEITUAN);
    }

    public AuthMeituanRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.MEITUAN, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        Map<String, String> form = new HashMap<>(7);
        form.put("app_id", config.getClientId());
        form.put("secret", config.getClientSecret());
        form.put("code", authCallback.getCode());
        form.put("grant_type", "authorization_code");

        String response = new HttpUtils(config.getHttpConfig()).post(source.accessToken(), form, false);
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .refreshToken(object.getString("refresh_token"))
            .expireIn(object.getIntValue("expires_in"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        Map<String, String> form = new HashMap<>(5);
        form.put("app_id", config.getClientId());
        form.put("secret", config.getClientSecret());
        form.put("access_token", authToken.getAccessToken());

        String response = new HttpUtils(config.getHttpConfig()).post(source.userInfo(), form, false);
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthUser.builder()
            .rawUserInfo(object)
            .uuid(object.getString("openid"))
            .username(object.getString("nickname"))
            .nickname(object.getString("nickname"))
            .avatar(object.getString("avatar"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(source.toString())
            .build();
    }

    @Override
    public AuthResponse refresh(AuthToken oldToken) {
        Map<String, String> form = new HashMap<>(7);
        form.put("app_id", config.getClientId());
        form.put("secret", config.getClientSecret());
        form.put("refresh_token", oldToken.getRefreshToken());
        form.put("grant_type", "refresh_token");

        String response = new HttpUtils(config.getHttpConfig()).post(source.refresh(), form, false);
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .refreshToken(object.getString("refresh_token"))
                .expireIn(object.getIntValue("expires_in"))
                .build())
            .build();
    }

    private void checkResponse(JSONObject object) {
        if (object.containsKey("error_code")) {
            throw new AuthException(object.getString("erroe_msg"));
        }
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state))
            .queryParam("scope", "")
            .build();
    }

}
