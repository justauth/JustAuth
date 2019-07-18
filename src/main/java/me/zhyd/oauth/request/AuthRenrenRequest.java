package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.url.AuthRenrenUrlBuilder;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.util.Objects;

import static me.zhyd.oauth.config.AuthSource.RENREN;

/**
 * 人人登录
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @version 1.8.1
 * @since 1.8.1
 */
public class AuthRenrenRequest extends AuthDefaultRequest {

    public AuthRenrenRequest(AuthConfig config) {
        super(config, RENREN, new AuthRenrenUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = this.urlBuilder.getAccessTokenUrl(authCallback.getCode());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (!response.isOk()) {
            throw new AuthException("Unable to get token from renren using code [" + authCallback.getCode() + "]: " + accessTokenObject);
        }

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
            .openId(accessTokenObject.getJSONObject("user").getString("id"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder()
            .openId(authToken.getOpenId())
            .accessToken(accessToken)
            .build())).execute();
        JSONObject userObj = JSONObject.parseObject(response.body()).getJSONObject("response");

        return AuthUser.builder()
            .uuid(userObj.getString("id"))
            .avatar(getAvatarUrl(userObj))
            .nickname(userObj.getString("name"))
            .company(getCompany(userObj))
            .gender(getGender(userObj))
            .token(authToken)
            .source(RENREN)
            .build();
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
}
