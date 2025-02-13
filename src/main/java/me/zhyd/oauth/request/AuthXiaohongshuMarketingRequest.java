package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.scope.AuthXiaohongshuMarketingScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthScopeUtils;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 小红书商业开放平台登录
 * <p>
 * 小红书商业开放平台
 *
 * @author yangjiahao
 * @since 2024-10-08
 */
public class AuthXiaohongshuMarketingRequest extends AuthDefaultRequest {
    public AuthXiaohongshuMarketingRequest(AuthConfig config) {
        super(config, AuthDefaultSource.XHS);
    }

    public AuthXiaohongshuMarketingRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.XHS, authStateCache);
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     * @since 1.9.3
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("appId", config.getClientId())
            .queryParam("scope", this.getScopes(" ", true, AuthScopeUtils.getDefaultScopes(AuthXiaohongshuMarketingScope.values())))
            .queryParam("redirectUri", config.getRedirectUri())
            .queryParam("state", getRealState(state))
            .build();
    }

    @Override
    public AuthToken getAccessToken(AuthCallback authCallback) {

        Map<String, String> params = new HashMap<>(7);
        params.put("app_id", config.getClientId());
        params.put("secret", config.getClientSecret());
        params.put("code", authCallback.getCode());
        String response = new HttpUtils(config.getHttpConfig()).post(source.accessToken(), params, false).getBody();
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .expireIn(object.getIntValue("access_token_expires_in"))
            .refreshToken(object.getString("refresh_token"))
            .scope(object.getString("scope"))
            .build();
    }

    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
        throw new UnsupportedOperationException("不支持获取用户信息 url");
    }

    @Override
    public AuthResponse<AuthToken> refresh(AuthToken oldToken) {
        Map<String, String> form = new HashMap<>(7);
        form.put("app_id", config.getClientId());
        form.put("secret", config.getClientSecret());
        form.put("refresh_token", oldToken.getRefreshToken());

        String response = new HttpUtils(config.getHttpConfig()).post(source.refresh(), form, false).getBody();
        JSONObject object = JSONObject.parseObject(response);

        this.checkResponse(object);

        return AuthResponse.<AuthToken>builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .refreshToken(object.getString("refresh_token"))
                .scope(object.getString("scope"))
                .expireIn(object.getIntValue("expires_in"))
                .build())
            .build();
    }


    /**
     * 检查响应内容是否正确
     *
     * @param
     */
    private void checkResponse(JSONObject object) {
        if (object.getIntValue("code") != 0) {
            throw new AuthException(object.getString("msg"));
        }
    }


}
