package me.zhyd.oauth.request;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.UrlBuilder;

import java.text.MessageFormat;

/**
 * 小米登录
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @version 1.5
 * @since 1.5
 */
public class AuthMiRequest extends BaseAuthRequest {
    private static final String PREFIX = "&&&START&&&";

    public AuthMiRequest(AuthConfig config) {
        super(config, AuthSource.MI);
    }

    @Override
    protected AuthToken getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getMiAccessTokenUrl(config.getClientId(), config.getClientSecret(), config.getRedirectUri(), code);
        return getToken(accessTokenUrl);
    }

    private AuthToken getToken(String accessTokenUrl) {
        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        String jsonStr = StrUtil.replace(response.body(), PREFIX, StrUtil.EMPTY);
        JSONObject object = JSONObject.parseObject(jsonStr);

        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }

        return AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .expireIn(object.getIntValue("expires_in"))
                .scope(object.getString("scope"))
                .tokenType(object.getString("token_type"))
                .refreshToken(object.getString("refresh_token"))
                .openId(object.getString("openId"))
                .macAlgorithm(object.getString("mac_algorithm"))
                .macKey(object.getString("mac_key"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        // 获取用户信息
        HttpResponse userResponse = HttpRequest.get(UrlBuilder.getMiUserInfoUrl(config.getClientId(), authToken.getAccessToken()))
                .execute();

        JSONObject userProfile = JSONObject.parseObject(userResponse.body());
        if (StrUtil.equalsIgnoreCase(userProfile.getString("result"), "error")) {
            throw new AuthException(userProfile.getString("description"));
        }

        JSONObject user = userProfile.getJSONObject("data");

        AuthUser authUser = AuthUser.builder()
                .uuid(authToken.getOpenId())
                .username(user.getString("miliaoNick"))
                .nickname(user.getString("miliaoNick"))
                .avatar(user.getString("miliaoIcon"))
                .email(user.getString("mail"))
                .token(authToken)
                .source(AuthSource.MI)
                .build();

        // 获取用户邮箱手机号等信息
        String emailPhoneUrl = MessageFormat.format("{0}?clientId={1}&token={2}", "https://open.account.xiaomi.com/user/phoneAndEmail", config
                .getClientId(), authToken.getAccessToken());

        HttpResponse emailResponse = HttpRequest.get(emailPhoneUrl).execute();
        JSONObject userEmailPhone = JSONObject.parseObject(emailResponse.body());
        if (!StrUtil.equalsIgnoreCase(userEmailPhone.getString("result"), "error")) {
            JSONObject emailPhone = userEmailPhone.getJSONObject("data");
            authUser.setEmail(emailPhone.getString("email"));
        }

        return authUser;
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return UrlBuilder.getMiAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }

    /**
     * 刷新access token （续期）
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    @Override
    public AuthResponse refresh(AuthToken authToken) {
        String miRefreshUrl = UrlBuilder.getMiRefreshUrl(config.getClientId(), config.getClientSecret(), config.getRedirectUri(), authToken
                .getRefreshToken());

        return AuthResponse.builder().code(ResponseStatus.SUCCESS.getCode()).data(getToken(miRefreshUrl)).build();
    }
}
