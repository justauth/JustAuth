package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.support.HttpHeader;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.GlobalAuthUtils;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 注意：该平台暂时存在问题，请不要使用。待修复完成后会重新发版by yadong.zhang
 *
 * @author beacon
 * @since 1.14.0
 */
@Deprecated
public class AuthFeishuRequest extends AuthDefaultRequest {

    public AuthFeishuRequest(AuthConfig config) {
        super(config, AuthDefaultSource.FEISHU);
        throw new AuthException(AuthResponseStatus.FAILURE);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        JSONObject requestObject = new JSONObject();
        requestObject.put("app_id", config.getClientId());
        requestObject.put("app_secret", config.getClientSecret());
        requestObject.put("grant_type", "authorization_code");
        requestObject.put("code", authCallback.getCode());
        String response = new HttpUtils(config.getHttpConfig()).post(source.accessToken(), requestObject.toJSONString(), new HttpHeader()
            .add("Content-Type", "application/json"));
        JSONObject jsonObject = JSON.parseObject(response);
        this.checkResponse(jsonObject);
        return AuthToken.builder()
            .accessToken(jsonObject.getString("access_token"))
            .refreshToken(jsonObject.getString("refresh_token"))
            .expireIn(jsonObject.getIntValue("expires_in"))
            .tokenType(jsonObject.getString("token_type"))
            .openId(jsonObject.getString("open_id"))
            .build();

    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        String response = new HttpUtils(config.getHttpConfig()).get(source.userInfo(), null, new HttpHeader()
            .add("Content-Type", "application/json")
            .add("Authorization", "Bearer " + accessToken), false);
        JSONObject object = JSON.parseObject(response);
        return AuthUser.builder()
            .rawUserInfo(object)
            .avatar(object.getString("AvatarUrl"))
            .username(object.getString("Mobile"))
            .email(object.getString("Email"))
            .nickname("Name")
            .build();
    }

    @Override
    public AuthResponse refresh(AuthToken authToken) {
        JSONObject requestObject = new JSONObject();
        requestObject.put("app_id", config.getClientId());
        requestObject.put("app_secret", config.getClientSecret());
        requestObject.put("grant_type", "refresh_token");
        requestObject.put("refresh_token", authToken.getRefreshToken());
        String response = new HttpUtils(config.getHttpConfig()).post(source.refresh(), requestObject.toJSONString(), new HttpHeader()
            .add("Content-Type", "application/json"));
        JSONObject jsonObject = JSON.parseObject(response);
        this.checkResponse(jsonObject);
        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(jsonObject.getString("access_token"))
                .refreshToken(jsonObject.getString("refresh_token"))
                .expireIn(jsonObject.getIntValue("expires_in"))
                .tokenType(jsonObject.getString("token_type"))
                .openId(jsonObject.getString("open_id"))
                .build())
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
