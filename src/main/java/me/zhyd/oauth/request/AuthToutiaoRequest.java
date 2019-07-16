package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.enums.AuthToutiaoErrorCode;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.url.ToutiaoUrlBuilder;
import me.zhyd.oauth.url.entity.AuthAccessTokenEntity;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;

/**
 * 今日头条登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.5
 * @since 1.5
 */
public class AuthToutiaoRequest extends BaseAuthRequest {

    public AuthToutiaoRequest(AuthConfig config) {
        super(config, AuthSource.TOUTIAO, new ToutiaoUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = this.urlBuilder.getAccessTokenUrl(AuthAccessTokenEntity.builder()
                .config(config)
                .code(authCallback.getCode())
                .build());
        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());

        if (accessTokenObject.containsKey("error_code")) {
            throw new AuthException(AuthToutiaoErrorCode.getErrorCode(accessTokenObject.getIntValue("error_code")).getDesc());
        }

        return AuthToken.builder()
                .accessToken(accessTokenObject.getString("access_token"))
                .expireIn(accessTokenObject.getIntValue("expires_in"))
                .openId(accessTokenObject.getString("open_id"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse userResponse = HttpRequest.get(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder()
                .clientId(config.getClientId())
                .accessToken(authToken.getAccessToken())
                .build())).execute();

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
}
