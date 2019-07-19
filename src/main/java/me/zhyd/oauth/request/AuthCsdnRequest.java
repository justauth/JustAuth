package me.zhyd.oauth.request;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.enums.AuthUserGender;

/**
 * CSDN登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Deprecated
public class AuthCsdnRequest extends AuthDefaultRequest {

    public AuthCsdnRequest(AuthConfig config) {
        super(config, AuthSource.CSDN);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doPostAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (accessTokenObject.containsKey("error_code")) {
            throw new AuthException("Unable to get token from csdn using code [" + authCallback.getCode() + "]: " + accessTokenObject);
        }
        return AuthToken.builder().accessToken(accessTokenObject.getString("access_token")).build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = doGetUserInfo(authToken);
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.containsKey("error_code")) {
            throw new AuthException(object.getString("error"));
        }
        return AuthUser.builder()
            .uuid(object.getString("username"))
            .username(object.getString("username"))
            .remark(object.getString("description"))
            .blog(object.getString("website"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(AuthSource.CSDN)
            .build();
    }
}
