package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;

/**
 * 阿里云登录
 *
 * @see <a href=https://help.aliyun.com/document_detail/93696.html?spm=a2c4g.11186623.6.656.146f53e8Tz7IGi>阿里云授权（OAuth）文档</a>
 */
public class AuthAliyunRequest extends AuthDefaultRequest {

    public AuthAliyunRequest(AuthConfig config) {
        super(config, AuthDefaultSource.ALIYUN);
    }

    public AuthAliyunRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.ALIYUN, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String response = doPostAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response);
        return AuthToken.builder()
                .accessToken(accessTokenObject.getString("access_token"))
                .expireIn(accessTokenObject.getIntValue("expires_in"))
                .tokenType(accessTokenObject.getString("token_type"))
                .idToken(accessTokenObject.getString("id_token"))
                .refreshToken(accessTokenObject.getString("refresh_token"))
                .build();
    }

    /*
     * 用户信息示例（主账号登录时）
     * {
     *      "sub":"PPPpppP+NRsXg/aaAaAAaA==",
     *      "uid":"1222222222222222",
     *      "login_name":"阿里云1234",
     *      "requestid":"6f6af0f2-0f98-4410-a4b0-83bd5e1c1506",
     *      "name":"root",
     *      "bid":"22222",
     *      "aid":"1222222222222222"
     * }
     */
    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String userInfo = doGetUserInfo(authToken);
        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        return AuthUser.builder().rawUserInfo(jsonObject).build();
    }

}
