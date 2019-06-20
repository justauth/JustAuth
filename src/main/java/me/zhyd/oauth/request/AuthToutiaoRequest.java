package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthToutiaoErrorCode;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 今日头条登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.5
 * @since 1.5
 */
public class AuthToutiaoRequest extends BaseAuthRequest {

    public AuthToutiaoRequest(AuthConfig config) {
        super(config, AuthSource.TOUTIAO);
    }

    @Override
    protected AuthToken getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getToutiaoAccessTokenUrl(config.getClientId(), config.getClientSecret(), code);
        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        JSONObject object = JSONObject.parseObject(response.body());

        if (object.containsKey("error_code")) {
            throw new AuthException(AuthToutiaoErrorCode.getErrorCode(object.getIntValue("error_code")).getDesc());
        }

        return AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .expireIn(object.getIntValue("expires_in"))
                .openId(object.getString("open_id"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse userResponse = HttpRequest.get(UrlBuilder.getToutiaoUserInfoUrl(config.getClientId(), authToken.getAccessToken())).execute();

        JSONObject userProfile = JSONObject.parseObject(userResponse.body());

        if (userProfile.containsKey("error_code")) {
            throw new AuthException(AuthToutiaoErrorCode.getErrorCode(userProfile.getIntValue("error_code")).getDesc());
        }

        JSONObject user = userProfile.getJSONObject("data");

        boolean isAnonymousUser = user.getIntValue("uid_type") == 14;
        String anonymousUserName = "匿名用户";

        return AuthUser.builder()
                .uuid(user.getString("uid"))
                .username(isAnonymousUser ? anonymousUserName : user.getString("screen_name"))
                .nickname(isAnonymousUser ? anonymousUserName : user.getString("screen_name"))
                .avatar(user.getString("avatar_url"))
                .remark(user.getString("description"))
                .gender(AuthUserGender.getRealGender(user.getString("gender")))
                .token(authToken)
                .source(AuthSource.TOUTIAO)
                .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return UrlBuilder.getToutiaoAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }
}
