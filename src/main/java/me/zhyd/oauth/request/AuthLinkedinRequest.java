package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;


/**
 * 领英登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.4.0
 */
public class AuthLinkedinRequest extends AuthDefaultRequest {

    public AuthLinkedinRequest(AuthConfig config) {
        super(config, AuthDefaultSource.LINKEDIN);
    }

    public AuthLinkedinRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.LINKEDIN, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        return this.getToken(accessTokenUrl(authCallback.getCode()));
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(userInfoUrl(authToken))
            .header("Host", "api.linkedin.com")
            .header("Connection", "Keep-Alive")
            .header("Authorization", "Bearer " + accessToken)
            .execute();
        JSONObject userInfoObject = JSONObject.parseObject(response.body());

        this.checkResponse(userInfoObject);

        String userName = getUserName(userInfoObject);

        // 获取用户头像
        String avatar = this.getAvatar(userInfoObject);

        // 获取用户邮箱地址
        String email = this.getUserEmail(accessToken);
        return AuthUser.builder()
            .uuid(userInfoObject.getString("id"))
            .username(userName)
            .nickname(userName)
            .avatar(avatar)
            .email(email)
            .token(authToken)
            .gender(AuthUserGender.UNKNOWN)
            .source(source.toString())
            .build();
    }

    /**
     * 获取用户的真实名
     *
     * @param userInfoObject 用户json对象
     * @return 用户名
     */
    private String getUserName(JSONObject userInfoObject) {
        String firstName, lastName;
        // 获取firstName
        if (userInfoObject.containsKey("localizedFirstName")) {
            firstName = userInfoObject.getString("localizedFirstName");
        } else {
            firstName = getUserName(userInfoObject, "firstName");
        }
        // 获取lastName
        if (userInfoObject.containsKey("localizedLastName")) {
            lastName = userInfoObject.getString("localizedLastName");
        } else {
            lastName = getUserName(userInfoObject, "lastName");
        }
        return firstName + " " + lastName;
    }

    /**
     * 获取用户的头像
     *
     * @param userInfoObject 用户json对象
     * @return 用户的头像地址
     */
    private String getAvatar(JSONObject userInfoObject) {
        String avatar = null;
        JSONObject profilePictureObject = userInfoObject.getJSONObject("profilePicture");
        if (profilePictureObject.containsKey("displayImage~")) {
            JSONArray displayImageElements = profilePictureObject.getJSONObject("displayImage~")
                .getJSONArray("elements");
            if (null != displayImageElements && displayImageElements.size() > 0) {
                JSONObject largestImageObj = displayImageElements.getJSONObject(displayImageElements.size() - 1);
                avatar = largestImageObj.getJSONArray("identifiers").getJSONObject(0).getString("identifier");
            }
        }
        return avatar;
    }

    /**
     * 获取用户的email
     *
     * @param accessToken 用户授权后返回的token
     * @return 用户的邮箱地址
     */
    private String getUserEmail(String accessToken) {
        HttpResponse emailResponse = HttpRequest.get("https://api.linkedin.com/v2/emailAddress?q=members&projection=(elements*(handle~))")
            .header("Host", "api.linkedin.com")
            .header("Connection", "Keep-Alive")
            .header("Authorization", "Bearer " + accessToken)
            .execute();
        JSONObject emailObj = JSONObject.parseObject(emailResponse.body());
        this.checkResponse(emailObj);
        Object obj = JSONPath.eval(emailObj, "$['elements'][0]['handle~']['emailAddress']");
        return null == obj ? null : (String) obj;
    }

    private String getUserName(JSONObject userInfoObject, String nameKey) {
        String firstName;
        JSONObject firstNameObj = userInfoObject.getJSONObject(nameKey);
        JSONObject localizedObj = firstNameObj.getJSONObject("localized");
        JSONObject preferredLocaleObj = firstNameObj.getJSONObject("preferredLocale");
        firstName = localizedObj.getString(preferredLocaleObj.getString("language") + "_" + preferredLocaleObj.getString("country"));
        return firstName;
    }

    @Override
    public AuthResponse refresh(AuthToken oldToken) {
        String refreshToken = oldToken.getRefreshToken();
        if (StringUtils.isEmpty(refreshToken)) {
            throw new AuthException(AuthResponseStatus.UNSUPPORTED);
        }
        String refreshTokenUrl = refreshTokenUrl(refreshToken);
        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(this.getToken(refreshTokenUrl))
            .build();
    }

    /**
     * 检查响应内容是否正确
     *
     * @param object 请求响应内容
     */
    private void checkResponse(JSONObject object) {
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }
    }

    /**
     * 获取token，适用于获取access_token和刷新token
     *
     * @param accessTokenUrl 实际请求token的地址
     * @return token对象
     */
    private AuthToken getToken(String accessTokenUrl) {
        HttpResponse response = HttpRequest.post(accessTokenUrl)
            .header("Host", "www.linkedin.com")
            .contentType("application/x-www-form-urlencoded")
            .execute();
        String accessTokenStr = response.body();
        JSONObject accessTokenObject = JSONObject.parseObject(accessTokenStr);

        this.checkResponse(accessTokenObject);

        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
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
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("response_type", "code")
            .queryParam("client_id", config.getClientId())
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("scope", "r_liteprofile%20r_emailaddress%20w_member_social")
            .queryParam("state", getRealState(state))
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
            .queryParam("projection", "(id,firstName,lastName,profilePicture(displayImage~:playableStreams))")
            .build();
    }
}
