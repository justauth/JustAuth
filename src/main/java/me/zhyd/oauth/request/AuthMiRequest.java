package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.constants.Constants;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.enums.scope.AuthMiScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.log.Log;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthScopeUtils;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import java.text.MessageFormat;

/**
 * 小米登录
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @since 1.5.0
 */
public class AuthMiRequest extends AuthDefaultRequest {
    private static final String PREFIX = "&&&START&&&";

    public AuthMiRequest(AuthConfig config) {
        super(config, AuthDefaultSource.MI);
    }

    public AuthMiRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.MI, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        return getToken(accessTokenUrl(authCallback.getCode()));
    }

    private AuthToken getToken(String accessTokenUrl) {
        String response = new HttpUtils(config.getHttpConfig()).get(accessTokenUrl);
        String jsonStr = response.replace(PREFIX, Constants.EMPTY);
        JSONObject accessTokenObject = JSONObject.parseObject(jsonStr);

        if (accessTokenObject.containsKey("error")) {
            throw new AuthException(accessTokenObject.getString("error_description"));
        }

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .scope(accessTokenObject.getString("scope"))
            .tokenType(accessTokenObject.getString("token_type"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
            .openId(accessTokenObject.getString("openId"))
            .macAlgorithm(accessTokenObject.getString("mac_algorithm"))
            .macKey(accessTokenObject.getString("mac_key"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        // 获取用户信息
        String userResponse = doGetUserInfo(authToken);

        JSONObject userProfile = JSONObject.parseObject(userResponse);
        if ("error".equalsIgnoreCase(userProfile.getString("result"))) {
            throw new AuthException(userProfile.getString("description"));
        }

        JSONObject object = userProfile.getJSONObject("data");

        AuthUser authUser = AuthUser.builder()
            .rawUserInfo(object)
            .uuid(authToken.getOpenId())
            .username(object.getString("miliaoNick"))
            .nickname(object.getString("miliaoNick"))
            .avatar(object.getString("miliaoIcon"))
            .email(object.getString("mail"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(source.toString())
            .build();

        // 获取用户邮箱手机号等信息
        String emailPhoneUrl = MessageFormat.format("{0}?clientId={1}&token={2}", "https://open.account.xiaomi.com/user/phoneAndEmail", config
            .getClientId(), authToken.getAccessToken());

        String emailResponse = new HttpUtils(config.getHttpConfig()).get(emailPhoneUrl);
        JSONObject userEmailPhone = JSONObject.parseObject(emailResponse);
        if (!"error".equalsIgnoreCase(userEmailPhone.getString("result"))) {
            JSONObject emailPhone = userEmailPhone.getJSONObject("data");
            authUser.setEmail(emailPhone.getString("email"));
        } else {
            Log.warn("小米开发平台暂时不对外开放用户手机及邮箱信息的获取");
        }

        return authUser;
    }

    /**
     * 刷新access token （续期）
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    @Override
    public AuthResponse refresh(AuthToken authToken) {
        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(getToken(refreshTokenUrl(authToken.getRefreshToken())))
            .build();
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
        return UrlBuilder.fromBaseUrl(super.authorize(state))
            .queryParam("skip_confirm", "false")
            .queryParam("scope", this.getScopes(" ", true, AuthScopeUtils.getDefaultScopes(AuthMiScope.values())))
            .build();
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
            .queryParam("clientId", config.getClientId())
            .queryParam("token", authToken.getAccessToken())
            .build();
    }
}
