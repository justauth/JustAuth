package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.GlobalAuthUtil;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * @author beacon
 * @since 1.14.0
 */
public class AuthFeishuRequest extends AuthDefaultRequest {

    public AuthFeishuRequest(AuthConfig config) {
        super(config, AuthDefaultSource.FEISHU);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        JSONObject requestObject = new JSONObject();
        requestObject.put("app_id",config.getClientId());
        requestObject.put("app_secret",config.getClientSecret());
        requestObject.put("grant_type","authorization_code");
        requestObject.put("code",authCallback.getCode());
        HttpResponse httpResponse = HttpRequest.post(source.accessToken()).body(requestObject.toJSONString(), "application/json").execute();
        JSONObject jsonObject = JSON.parseObject(httpResponse.body());
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
        HttpResponse userInfoResponse = HttpRequest.get(source.userInfo()).header("Authorization", "Bearer " + accessToken).execute();
        JSONObject jsonObject = JSON.parseObject(userInfoResponse.body());
        return AuthUser.builder()
            .avatar(jsonObject.getString("AvatarUrl"))
            .username(jsonObject.getString("Mobile"))
            .email(jsonObject.getString("Email"))
            .nickname("Name")
            .build();
    }

    @Override
    public AuthResponse refresh(AuthToken authToken) {
        JSONObject requestObject = new JSONObject();
        requestObject.put("app_id",config.getClientId());
        requestObject.put("app_secret",config.getClientSecret());
        requestObject.put("grant_type","refresh_token");
        requestObject.put("refresh_token",authToken.getRefreshToken());
        HttpResponse httpResponse = HttpRequest.post(source.refresh())
            .body(requestObject.toJSONString(), "application/json")
            .execute();
        JSONObject jsonObject = JSON.parseObject(httpResponse.body());
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
            .queryParam("app_id",config.getClientId())
            .queryParam("redirect_uri", GlobalAuthUtil.urlEncode(config.getRedirectUri()))
            .queryParam("state",getRealState(state))
            .build();
    }


    /**
     * 校验响应内容是否正确
     * @param jsonObject 响应内容
     */
    private void checkResponse(JSONObject jsonObject){
        if(jsonObject.getIntValue("code") != 0){
            throw new AuthException(jsonObject.getString("message"));
        }
    }

}
