package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.HttpUtil;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.GlobalAuthUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * 喜马拉雅登录
 *
 * @author zwzch (zwzch4j@gmail.com)
 * @since 1.15.9
 */
public class AuthXmlyRequest extends AuthDefaultRequest {

    public AuthXmlyRequest(AuthConfig config) {
        super(config, AuthDefaultSource.XMLY);
    }

    public AuthXmlyRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.XMLY, authStateCache);
    }

    /**
     * 获取access token
     *
     * @param authCallback 授权成功后的回调参数
     * @return token
     * @see AuthDefaultRequest#authorize(String)
     */
    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        Map<String, String> map = new HashMap<>(9);
        map.put("code", authCallback.getCode());
        map.put("client_id", config.getClientId());
        map.put("client_secret", config.getClientSecret());
        map.put("device_id", config.getDeviceId());
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", config.getRedirectUri());
        String response = HttpUtil.post(source.accessToken(), map, true);
        JSONObject accessTokenObject = JSONObject.parseObject(response);
        this.checkResponse(accessTokenObject);

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .uid(accessTokenObject.getString("uid"))
            .build();
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     * @since 1.15.8
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("response_type", "code")
            .queryParam("client_id", config.getClientId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("state", getRealState(state))
            .queryParam("client_os_type", "3")
            .queryParam("device_id", config.getDeviceId())
            .build();
    }

    /**
     * 使用token换取用户信息
     *
     * @param authToken token信息
     * @return 用户信息
     * @see AuthDefaultRequest#getAccessToken(AuthCallback)
     */
    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
        Map<String, String> map = new TreeMap<>();
        map.put("app_key", config.getClientId());
        map.put("client_os_type", Optional.ofNullable(config.getClientOsType()).orElse(3).toString());
        map.put("device_id", config.getDeviceId());
        map.put("pack_id", config.getPackId());
        map.put("access_token", authToken.getAccessToken());
        map.put("sig", GlobalAuthUtils.generateXmlySignature(map, config.getClientSecret()));
        String rawUserInfo = HttpUtil.get(source.userInfo(), map, false);
        JSONObject object = JSONObject.parseObject(rawUserInfo);
        checkResponse(object);
        return AuthUser.builder()
            .uuid(object.getString("id"))
            .nickname(object.getString("nickname"))
            .avatar(object.getString("avatar_url"))
            .rawUserInfo(object)
            .source(source.toString())
            .token(authToken)
            .gender(AuthUserGender.UNKNOWN)
            .build();
    }

    /**
     * 校验响应结果
     *
     * @param object 接口返回的结果
     */
    private void checkResponse(JSONObject object) {
        if (object.containsKey("errcode")) {
            throw new AuthException(object.getIntValue("error_no"), object.getString("error_desc"));
        }
    }
}
