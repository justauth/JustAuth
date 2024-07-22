package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.support.HttpHeader;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.scope.AuthFigmaScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.*;

/**
 * Figma登录
 * @author xiangqian
 * @since 1.16.6
 */
public class AuthFigmaRequest extends AuthDefaultRequest {
    public AuthFigmaRequest(AuthConfig config) {
        super(config, AuthDefaultSource.FIGMA);
    }

    public AuthFigmaRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.FIGMA, authStateCache);
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state))
            .queryParam("scope", this.getScopes(",", true, AuthScopeUtils.getDefaultScopes(AuthFigmaScope.values())))
            .build();
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpHeader header = new HttpHeader()
            .add("content-type", "application/x-www-form-urlencoded")
            .add("Authorization", "Basic " + Base64Utils.encode(config.getClientId().concat(":").concat(config.getClientSecret())));

        String response = new HttpUtils(config.getHttpConfig()).post(super.accessTokenUrl(authCallback.getCode()), null, header, true).getBody();
        JSONObject accessTokenObject = JSONObject.parseObject(response);

        this.checkResponse(accessTokenObject);

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
            .scope(accessTokenObject.getString("scope"))
            .userId(accessTokenObject.getString("user_id"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .build();
    }

    @Override
    public AuthResponse<AuthToken> refresh(AuthToken authToken) {
        HttpHeader header = new HttpHeader().add("content-type", "application/x-www-form-urlencoded");
        String response = new HttpUtils(config.getHttpConfig()).post(this.refreshTokenUrl(authToken.getRefreshToken()), null, header, false).getBody();
        JSONObject dataObj = JSONObject.parseObject(response);

        this.checkResponse(dataObj);

        return AuthResponse.<AuthToken>builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(dataObj.getString("access_token"))
                .openId(dataObj.getString("open_id"))
                .expireIn(dataObj.getIntValue("expires_in"))
                .refreshToken(dataObj.getString("refresh_token"))
                .scope(dataObj.getString("scope"))
                .build())
            .build();

    }

    @Override
    protected String refreshTokenUrl(String refreshToken) {
        return UrlBuilder.fromBaseUrl(source.refresh())
            .queryParam("client_id", config.getClientId())
            .queryParam("client_secret", config.getClientSecret())
            .queryParam("refresh_token", refreshToken)
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpHeader header = new HttpHeader().add("Authorization", "Bearer " + authToken.getAccessToken());
        String response = new HttpUtils(config.getHttpConfig()).get(super.userInfoUrl(authToken), null, header, false).getBody();
        JSONObject dataObj = JSONObject.parseObject(response);

        this.checkResponse(dataObj);

        return AuthUser.builder()
            .rawUserInfo(dataObj)
            .uuid(dataObj.getString("id"))
            .username(dataObj.getString("handle"))
            .avatar(dataObj.getString("img_url"))
            .email(dataObj.getString("email"))
            .token(authToken)
            .source(source.toString())
            .build();
    }


    /**
     * 校验响应结果
     *
     * @param object 接口返回的结果
     */
    private void checkResponse(JSONObject object) {
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error") + ":" + object.getString("message"));
        }
    }
}
