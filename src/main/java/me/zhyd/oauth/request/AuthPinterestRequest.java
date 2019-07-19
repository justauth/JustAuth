package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.UrlBuilder;

import java.util.Objects;

import static me.zhyd.oauth.config.AuthSource.PINTEREST;

/**
 * Pinterest登录
 *
 * @author hongwei.peng (pengisgood(at)gmail(dot)com)
 * @version 1.9.0
 * @since 1.8
 */
public class AuthPinterestRequest extends AuthDefaultRequest {

    private static final String FAILURE = "failure";

    public AuthPinterestRequest(AuthConfig config) {
        super(config, PINTEREST);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doPostAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        this.checkResponse(accessTokenObject);
        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .tokenType(accessTokenObject.getString("token_type"))
            .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String userinfoUrl = UrlBuilder.fromBaseUrl(userInfoUrl(authToken))
            .queryParam("fields", "id,username,first_name,last_name,bio,image")
            .build();
        HttpResponse response = HttpRequest.post(userinfoUrl).execute();
        JSONObject object = JSONObject.parseObject(response.body());
        this.checkResponse(object);
        JSONObject userObj = object.getJSONObject("data");
        return AuthUser.builder()
            .uuid(userObj.getString("id"))
            .avatar(getAvatarUrl(userObj))
            .username(userObj.getString("username"))
            .nickname(userObj.getString("first_name") + " " + userObj.getString("last_name"))
            .gender(AuthUserGender.UNKNOWN)
            .remark(userObj.getString("bio"))
            .token(authToken)
            .source(source)
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

    @Override
    public String authorize() {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("response_type", "code")
            .queryParam("client_id", config.getClientId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("state", getRealState(config.getState()))
            .queryParam("scope", "read_public")
            .build();
    }

    /**
     * 检查响应内容是否正确
     *
     * @param object 请求响应内容
     */
    private void checkResponse(JSONObject object) {
        if (!object.containsKey("status") && FAILURE.equals(object.getString("status"))) {
            throw new AuthException(object.getString("message"));
        }
    }

}
