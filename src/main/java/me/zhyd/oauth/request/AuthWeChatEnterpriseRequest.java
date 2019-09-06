package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * <p>
 * 企业微信登录
 * </p>
 *
 * @author yangkai.shen (https://xkcoding.com)
 * @since 1.10.0
 */
public class AuthWeChatEnterpriseRequest extends AuthDefaultRequest {
    public AuthWeChatEnterpriseRequest(AuthConfig config) {
        super(config, AuthDefaultSource.WECHAT_ENTERPRISE);
    }

    public AuthWeChatEnterpriseRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.WECHAT_ENTERPRISE, authStateCache);
    }

    /**
     * 微信的特殊性，此时返回的信息同时包含 openid 和 access_token
     *
     * @param authCallback 回调返回的参数
     * @return 所有信息
     */
    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doGetAuthorizationCode(accessTokenUrl(authCallback.getCode()));

        JSONObject object = this.checkResponse(response);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .expireIn(object.getIntValue("expires_in"))
            .code(authCallback.getCode())
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = doGetUserInfo(authToken);
        JSONObject object = this.checkResponse(response);

        // 返回 OpenId 或其他，均代表非当前企业用户，不支持
        if (!object.containsKey("UserId")) {
            throw new AuthException(AuthResponseStatus.UNIDENTIFIED_PLATFORM);
        }
        String userId = object.getString("UserId");
        HttpResponse userDetailResponse = getUserDetail(authToken.getAccessToken(), userId);
        JSONObject userDetail = this.checkResponse(userDetailResponse);

        String gender = getRealGender(userDetail);

        return AuthUser.builder()
            .username(userDetail.getString("name"))
            .nickname(userDetail.getString("alias"))
            .avatar(userDetail.getString("avatar"))
            .location(userDetail.getString("address"))
            .email(userDetail.getString("email"))
            .uuid(userId)
            .gender(AuthUserGender.getRealGender(gender))
            .token(authToken)
            .source(source.toString())
            .build();
    }

    /**
     * 校验请求结果
     *
     * @param response 请求结果
     * @return 如果请求结果正常，则返回JSONObject
     */
    private JSONObject checkResponse(HttpResponse response) {
        JSONObject object = JSONObject.parseObject(response.body());

        if (object.containsKey("errcode") && object.getIntValue("errcode") != 0) {
            throw new AuthException(object.getIntValue("errcode"), object.getString("errmsg"));
        }

        return object;
    }

    /**
     * 获取用户的实际性别，0表示未定义，1表示男性，2表示女性
     *
     * @param userDetail 用户详情
     * @return 用户性别
     */
    private String getRealGender(JSONObject userDetail) {
        int gender = userDetail.getIntValue("gender");
        if (AuthUserGender.MALE.getCode() == gender) {
            return "1";
        }
        return 2 == gender ? "0" : null;
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
            .queryParam("appid", config.getClientId())
            .queryParam("agentid", config.getAgentId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("state", getRealState(state))
            .build();
    }

    /**
     * 返回获取accessToken的url
     *
     * @param code 授权码
     * @return 返回获取accessToken的url
     */
    @Override
    protected String accessTokenUrl(String code) {
        return UrlBuilder.fromBaseUrl(source.accessToken())
            .queryParam("corpid", config.getClientId())
            .queryParam("corpsecret", config.getClientSecret())
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
            .queryParam("access_token", authToken.getAccessToken())
            .queryParam("code", authToken.getCode())
            .build();
    }

    /**
     * 用户详情
     *
     * @param accessToken accessToken
     * @param userId      企业内用户id
     * @return 用户详情
     */
    private HttpResponse getUserDetail(String accessToken, String userId) {
        String userDetailUrl = UrlBuilder.fromBaseUrl("https://qyapi.weixin.qq.com/cgi-bin/user/get")
            .queryParam("access_token", accessToken)
            .queryParam("userid", userId)
            .build();
        return HttpRequest.get(userDetailUrl).execute();
    }

}
