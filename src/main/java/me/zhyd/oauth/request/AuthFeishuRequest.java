package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.support.HttpHeader;
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
import me.zhyd.oauth.utils.GlobalAuthUtils;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 飞书平台，企业自建应用授权登录，原逻辑由 beacon 集成于 1.14.0 版，但最新的飞书 api 已修改，并且飞书平台一直为 {@code Deprecated} 状态
 * <p>
 * 所以，最终修改该平台的实际发布版本为 1.15.9
 *
 * @author beacon
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com) 重构业务逻辑 20210101
 * @since 1.15.9
 */
public class AuthFeishuRequest extends AuthDefaultRequest {

    public AuthFeishuRequest(AuthConfig config) {
        super(config, AuthDefaultSource.FEISHU);
    }

    public AuthFeishuRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.FEISHU, authStateCache);
    }

    /**
     * 获取 app_access_token（企业自建应用）
     * <p>
     * Token 有效期为 2 小时，在此期间调用该接口 token 不会改变。当 token 有效期小于 30 分的时候，再次请求获取 token 的时候，
     * 会生成一个新的 token，与此同时老的 token 依然有效。
     *
     * @return appAccessToken
     */
    private String getAppAccessToken() {
        String cacheKey = this.source.getName().concat(":app_access_token:").concat(config.getClientId());
        String cacheAppAccessToken = this.authStateCache.get(cacheKey);
        if (StringUtils.isNotEmpty(cacheAppAccessToken)) {
            return cacheAppAccessToken;
        }
        String url = "https://open.feishu.cn/open-apis/auth/v3/app_access_token/internal/";
        JSONObject requestObject = new JSONObject();
        requestObject.put("app_id", config.getClientId());
        requestObject.put("app_secret", config.getClientSecret());
        String response = new HttpUtils(config.getHttpConfig()).post(url, requestObject.toJSONString(), new HttpHeader()
            .add("Content-Type", "application/json"));
        JSONObject jsonObject = JSON.parseObject(response);
        this.checkResponse(jsonObject);
        String appAccessToken = jsonObject.getString("app_access_token");
        // 缓存 app access token
        this.authStateCache.cache(cacheKey, appAccessToken, jsonObject.getLongValue("expire") * 1000);
        return appAccessToken;
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        JSONObject requestObject = new JSONObject();
        requestObject.put("app_access_token", this.getAppAccessToken());
        requestObject.put("grant_type", "authorization_code");
        requestObject.put("code", authCallback.getCode());
        return getToken(requestObject, this.source.accessToken());

    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        String response = new HttpUtils(config.getHttpConfig()).get(source.userInfo(), null, new HttpHeader()
            .add("Content-Type", "application/json")
            .add("Authorization", "Bearer " + accessToken), false);
        JSONObject object = JSON.parseObject(response);
        this.checkResponse(object);
        JSONObject data = object.getJSONObject("data");
        return AuthUser.builder()
            .rawUserInfo(object)
            .uuid(data.getString("union_id"))
            .username(data.getString("name"))
            .nickname(data.getString("name"))
            .avatar(data.getString("avatar_url"))
            .email(data.getString("email"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(source.toString())
            .build();
    }

    @Override
    public AuthResponse refresh(AuthToken authToken) {
        JSONObject requestObject = new JSONObject();
        requestObject.put("app_access_token", this.getAppAccessToken());
        requestObject.put("grant_type", "refresh_token");
        requestObject.put("refresh_token", authToken.getRefreshToken());
        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(getToken(requestObject, this.source.refresh()))
            .build();

    }

    private AuthToken getToken(JSONObject param, String url) {
        String response = new HttpUtils(config.getHttpConfig()).post(url, param.toJSONString(), new HttpHeader()
            .add("Content-Type", "application/json"));
        JSONObject jsonObject = JSON.parseObject(response);
        this.checkResponse(jsonObject);
        JSONObject data = jsonObject.getJSONObject("data");
        return AuthToken.builder()
            .accessToken(data.getString("access_token"))
            .refreshToken(data.getString("refresh_token"))
            .expireIn(data.getIntValue("expires_in"))
            .tokenType(data.getString("token_type"))
            .openId(data.getString("open_id"))
            .build();
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("app_id", config.getClientId())
            .queryParam("redirect_uri", GlobalAuthUtils.urlEncode(config.getRedirectUri()))
            .queryParam("state", getRealState(state))
            .build();
    }


    /**
     * 校验响应内容是否正确
     *
     * @param jsonObject 响应内容
     */
    private void checkResponse(JSONObject jsonObject) {
        if (jsonObject.getIntValue("code") != 0) {
            throw new AuthException(jsonObject.getString("message"));
        }
    }

}
