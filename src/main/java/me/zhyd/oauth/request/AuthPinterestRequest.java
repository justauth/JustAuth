package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.url.AuthPinterestUrlBuilder;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

import java.util.Objects;

import static me.zhyd.oauth.config.AuthSource.PINTEREST;

/**
 * Pinterest登录
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @version 1.9.0
 * @since 1.9.0
 */
public class AuthPinterestRequest extends AuthDefaultRequest {

    public AuthPinterestRequest(AuthConfig config) {
        super(config, PINTEREST, new AuthPinterestUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = this.urlBuilder.getAccessTokenUrl(authCallback.getCode());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (!response.isOk()) {
            throw new AuthException("Unable to get token from Pinterest using code [" + authCallback.getCode() + "]: " + accessTokenObject);
        }

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .tokenType(accessTokenObject.getString("token_type"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder()
            .accessToken(accessToken)
            .build())).execute();
        JSONObject userObj = JSONObject.parseObject(response.body()).getJSONObject("data");

        return AuthUser.builder()
            .uuid(userObj.getString("id"))
            .avatar(getAvatarUrl(userObj))
            .username(userObj.getString("username"))
            .nickname(userObj.getString("first_name") + " " + userObj.getString("last_name"))
            .gender(AuthUserGender.UNKNOWN)
            .remark(userObj.getString("bio"))
            .token(authToken)
            .source(PINTEREST)
            .build();
    }

    private String getAvatarUrl(JSONObject userObj) {
        // image is a map data structure
        JSONObject jsonObject = userObj.getJSONObject("image");
        if (Objects.isNull(jsonObject)) {
            return null;
        }
        return jsonObject.getJSONObject("60x60").getString("url");
    }

}
