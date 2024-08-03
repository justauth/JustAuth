package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * <p>
 * 企业微信登录父类
 * </p>
 *
 * @author liguanhua (347826496(a)qq.com)
 * @since 1.15.9
 */
public abstract class AbstractAuthWeChatEnterpriseRequest extends AuthDefaultRequest {

    public AbstractAuthWeChatEnterpriseRequest(AuthConfig config, AuthSource source) {
        super(config,source);
    }


    public AbstractAuthWeChatEnterpriseRequest(AuthConfig config, AuthSource source, AuthStateCache authStateCache) {
        super(config, source, authStateCache);
    }

    @Override
    public AuthToken getAccessToken(AuthCallback authCallback) {
        String response = doGetAuthorizationCode(accessTokenUrl(null));

        JSONObject object = this.checkResponse(response);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .expireIn(object.getIntValue("expires_in"))
            .code(authCallback.getCode())
            .build();
    }

    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
        String response = doGetUserInfo(authToken);
        JSONObject object = this.checkResponse(response);

        // 返回 OpenId 或其他，均代表非当前企业用户，不支持
        if (!object.containsKey("UserId")) {
            throw new AuthException(AuthResponseStatus.UNIDENTIFIED_PLATFORM, source);
        }
        String userId = object.getString("UserId");
        String userTicket = object.getString("user_ticket");
        JSONObject userDetail = getUserDetail(authToken.getAccessToken(), userId, userTicket);

        return AuthUser.builder()
            .rawUserInfo(userDetail)
            .username(userDetail.getString("name"))
            .nickname(userDetail.getString("alias"))
            .avatar(userDetail.getString("avatar"))
            .location(userDetail.getString("address"))
            .email(userDetail.getString("email"))
            .uuid(userId)
            .gender(AuthUserGender.getWechatRealGender(userDetail.getString("gender")))
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
    private JSONObject checkResponse(String response) {
        JSONObject object = JSONObject.parseObject(response);

        if (object.containsKey("errcode") && object.getIntValue("errcode") != 0) {
            throw new AuthException(object.getString("errmsg"), source);
        }

        return object;
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
     * @param userTicket  成员票据，用于获取用户信息或敏感信息
     * @return 用户详情
     */
    private JSONObject getUserDetail(String accessToken, String userId, String userTicket) {
        // 用户基础信息
        String userInfoUrl = UrlBuilder.fromBaseUrl("https://qyapi.weixin.qq.com/cgi-bin/user/get")
            .queryParam("access_token", accessToken)
            .queryParam("userid", userId)
            .build();
        String userInfoResponse = new HttpUtils(config.getHttpConfig()).get(userInfoUrl).getBody();
        JSONObject userInfo = checkResponse(userInfoResponse);

        // 用户敏感信息
        if (StringUtils.isNotEmpty(userTicket)) {
            String userDetailUrl = UrlBuilder.fromBaseUrl("https://qyapi.weixin.qq.com/cgi-bin/auth/getuserdetail")
                .queryParam("access_token", accessToken)
                .build();
            JSONObject param = new JSONObject();
            param.put("user_ticket", userTicket);
            String userDetailResponse = new HttpUtils(config.getHttpConfig()).post(userDetailUrl, param.toJSONString()).getBody();
            JSONObject userDetail = checkResponse(userDetailResponse);

            userInfo.putAll(userDetail);
        }
        return userInfo;
    }

}
