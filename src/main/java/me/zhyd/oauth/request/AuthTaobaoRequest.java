package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.GlobalAuthUtil;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 淘宝登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthTaobaoRequest extends BaseAuthRequest {

    public AuthTaobaoRequest(AuthConfig config) {
        super(config, AuthSource.TAOBAO);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        return AuthToken.builder().accessCode(authCallback.getCode()).build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessCode = authToken.getAccessCode();
        HttpResponse response = HttpRequest.post(UrlBuilder.getTaobaoAccessTokenUrl(this.config.getClientId(), this.config
                .getClientSecret(), accessCode, this.config.getRedirectUri())).execute();
        JSONObject accessTokenObject = JSONObject.parseObject(response.body());
        if (accessTokenObject.containsKey("error")) {
            throw new AuthException(ResponseStatus.FAILURE + ":" + accessTokenObject.getString("error_description"));
        }
        authToken.setAccessToken(accessTokenObject.getString("access_token"));
        authToken.setRefreshToken(accessTokenObject.getString("refresh_token"));
        authToken.setExpireIn(accessTokenObject.getIntValue("expires_in"));
        authToken.setUid(accessTokenObject.getString("taobao_user_id"));
        authToken.setOpenId(accessTokenObject.getString("taobao_open_uid"));

        String nick = GlobalAuthUtil.urlDecode(accessTokenObject.getString("taobao_user_nick"));
        return AuthUser.builder()
                .uuid(accessTokenObject.getString("taobao_user_id"))
                .username(nick)
                .nickname(nick)
                .gender(AuthUserGender.UNKNOW)
                .token(authToken)
                .source(AuthSource.TAOBAO)
                .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return UrlBuilder.getTaobaoAuthorizeUrl(config.getClientId(), config.getRedirectUri(), config.getState());
    }
}
