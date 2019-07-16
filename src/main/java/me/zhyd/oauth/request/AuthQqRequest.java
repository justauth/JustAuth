package me.zhyd.oauth.request;

import cn.hutool.core.util.StrUtil;
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
import me.zhyd.oauth.url.QqUrlBuilder;
import me.zhyd.oauth.url.entity.AuthAccessTokenEntity;
import me.zhyd.oauth.url.entity.AuthAuthorizeEntity;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;
import me.zhyd.oauth.utils.GlobalAuthUtil;
import me.zhyd.oauth.utils.StringUtils;

import java.util.Map;

/**
 * qq登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @author yangkai.shen (https://xkcoding.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthQqRequest extends BaseAuthRequest {
    public AuthQqRequest(AuthConfig config) {
        super(config, AuthSource.QQ, new QqUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String accessTokenUrl = this.urlBuilder.getAccessTokenUrl(AuthAccessTokenEntity.builder()
                .config(config)
                .code(authCallback.getCode())
                .build());
        HttpResponse response = HttpRequest.get(accessTokenUrl).execute();
        Map<String, String> accessTokenObject = GlobalAuthUtil.parseStringToMap(response.body());
        if (!accessTokenObject.containsKey("access_token")) {
            throw new AuthException("Unable to get token from qq using code [" + authCallback.getCode() + "]");
        }
        return AuthToken.builder()
                .accessToken(accessTokenObject.get("access_token"))
                .expireIn(Integer.valueOf(accessTokenObject.get("expires_in")))
                .refreshToken(accessTokenObject.get("refresh_token"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        String openId = this.getOpenId(authToken);
        HttpResponse response = HttpRequest.get(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder()
                .clientId(config.getClientId())
                .accessToken(accessToken)
                .openId(openId)
                .build()))
                .execute();
        JSONObject object = JSONObject.parseObject(response.body());
        if (object.getIntValue("ret") != 0) {
            throw new AuthException(object.getString("msg"));
        }
        String avatar = object.getString("figureurl_qq_2");
        if (StringUtils.isEmpty(avatar)) {
            avatar = object.getString("figureurl_qq_1");
        }

        String location = String.format("%s-%s", object.getString("province"), object.getString("city"));
        return AuthUser.builder()
                .username(object.getString("nickname"))
                .nickname(object.getString("nickname"))
                .avatar(avatar)
                .location(location)
                .uuid(openId)
                .gender(AuthUserGender.getRealGender(object.getString("gender")))
                .token(authToken)
                .source(AuthSource.QQ)
                .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return this.urlBuilder.getAuthorizeUrl(AuthAuthorizeEntity.builder()
                .config(config)
                .build());
    }

    private String getOpenId(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(this.urlBuilder.getOpenIdUrl(accessToken, config.isUnionId())).execute();
        if (response.isOk()) {
            String body = response.body();
            String removePrefix = StrUtil.replace(body, "callback(", "");
            String removeSuffix = StrUtil.replace(removePrefix, ");", "");
            String openId = StrUtil.trim(removeSuffix);
            JSONObject object = JSONObject.parseObject(openId);
            if (object.containsKey("error")) {
                throw new AuthException(object.get("error") + ":" + object.get("error_description"));
            }
            authToken.setOpenId(object.getString("openid"));
            if (object.containsKey("unionid")) {
                authToken.setUnionId(object.getString("unionid"));
            }
            return StringUtils.isEmpty(authToken.getUnionId()) ? authToken.getOpenId() : authToken.getUnionId();
        }

        throw new AuthException("request error");
    }
}
