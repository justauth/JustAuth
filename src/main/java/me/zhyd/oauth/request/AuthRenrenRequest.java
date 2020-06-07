package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.HttpUtil;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.UrlBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

import static me.zhyd.oauth.config.AuthDefaultSource.RENREN;
import static me.zhyd.oauth.enums.AuthResponseStatus.SUCCESS;

/**
 * 人人登录
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @since 1.9.0
 */
public class AuthRenrenRequest extends AuthDefaultRequest {

    public AuthRenrenRequest(AuthConfig config) {
        super(config, RENREN);
    }

    public AuthRenrenRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, RENREN, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        return this.getToken(accessTokenUrl(authCallback.getCode()));
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String response = doGetUserInfo(authToken);
        JSONObject userObj = JSONObject.parseObject(response).getJSONObject("response");

        return AuthUser.builder()
            .uuid(userObj.getString("id"))
            .avatar(getAvatarUrl(userObj))
            .nickname(userObj.getString("name"))
            .company(getCompany(userObj))
            .gender(getGender(userObj))
            .token(authToken)
            .source(source.toString())
            .build();
    }

    @Override
    public AuthResponse refresh(AuthToken authToken) {
        return AuthResponse.builder()
            .code(SUCCESS.getCode())
            .data(getToken(this.refreshTokenUrl(authToken.getRefreshToken())))
            .build();
    }

    private AuthToken getToken(String url) {
        String response = HttpUtil.post(url);
        JSONObject jsonObject = JSONObject.parseObject(response);
        if (jsonObject.containsKey("error")) {
            throw new AuthException("Failed to get token from Renren: " + jsonObject);
        }

        try {
            return AuthToken.builder()
                .tokenType(jsonObject.getString("token_type"))
                .expireIn(jsonObject.getIntValue("expires_in"))
                .accessToken(URLEncoder.encode(jsonObject.getString("access_token"), "UTF-8"))
                .refreshToken(URLEncoder.encode(jsonObject.getString("refresh_token"), "UTF-8"))
                .openId(jsonObject.getJSONObject("user").getString("id"))
                .build();
        } catch (UnsupportedEncodingException e) {
            throw new AuthException("Failed to encode token" + e.getMessage());
        }
    }

    private String getAvatarUrl(JSONObject userObj) {
        JSONArray jsonArray = userObj.getJSONArray("avatar");
        if (Objects.isNull(jsonArray) || jsonArray.isEmpty()) {
            return null;
        }
        return jsonArray.getJSONObject(0).getString("url");
    }

    private AuthUserGender getGender(JSONObject userObj) {
        JSONObject basicInformation = userObj.getJSONObject("basicInformation");
        if (Objects.isNull(basicInformation)) {
            return AuthUserGender.UNKNOWN;
        }
        return AuthUserGender.getRealGender(basicInformation.getString("sex"));
    }

    private String getCompany(JSONObject userObj) {
        JSONArray jsonArray = userObj.getJSONArray("work");
        if (Objects.isNull(jsonArray) || jsonArray.isEmpty()) {
            return null;
        }
        return jsonArray.getJSONObject(0).getString("name");
    }

    /**
     * 返回获取userInfo的url
     *
     * @param authToken 用户授权后的token
     * @return 返回获取userInfo的url
     */
    @Override
    protected String userInfoUrl(AuthToken authToken) {
        return UrlBuilder.fromBaseUrl(source.userInfo())
            .queryParam("access_token", authToken.getAccessToken())
            .queryParam("userId", authToken.getOpenId())
            .build();
    }
}
