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
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.containsKey("error")) {
            throw new AuthException(ResponseStatus.FAILURE + ":" + object.getString("error_description"));
        }
        authToken.setAccessToken(object.getString("access_token"));
        authToken.setRefreshToken(object.getString("refresh_token"));
        authToken.setExpireIn(object.getIntValue("expires_in"));
        authToken.setUid(object.getString("taobao_user_id"));
        authToken.setOpenId(object.getString("taobao_open_uid"));

        String nick = GlobalAuthUtil.urlDecode(object.getString("taobao_user_nick"));
        return AuthUser.builder()
                .uuid(object.getString("taobao_user_id"))
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
        return UrlBuilder.getTaobaoAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }
}
