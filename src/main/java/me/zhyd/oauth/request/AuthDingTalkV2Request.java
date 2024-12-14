package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import com.xkcoding.http.support.HttpHeader;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.scope.AuthDingTalkScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthScopeUtils;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 新版钉钉二维码登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.16.7
 */
public class AuthDingTalkV2Request extends AuthDefaultRequest {

    public AuthDingTalkV2Request(AuthConfig config) {
        super(config, AuthDefaultSource.DINGTALK_V2);
    }

    public AuthDingTalkV2Request(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.DINGTALK_V2, authStateCache);
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("response_type", "code")
            .queryParam("client_id", config.getClientId())
            .queryParam("scope", this.getScopes(",", true, AuthScopeUtils.getDefaultScopes(AuthDingTalkScope.values())))
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("prompt", "consent")
            .queryParam("org_type", config.getDingTalkOrgType())
            .queryParam("corpId", config.getDingTalkCorpId())
            .queryParam("exclusiveLogin", config.isDingTalkExclusiveLogin())
            .queryParam("exclusiveCorpId", config.getDingTalkExclusiveCorpId())
            .queryParam("state", getRealState(state))
            .build();
    }

    @Override
    public AuthToken getAccessToken(AuthCallback authCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("grantType", "authorization_code");
        params.put("clientId", config.getClientId());
        params.put("clientSecret", config.getClientSecret());
        params.put("code", authCallback.getCode());
        String response = new HttpUtils(config.getHttpConfig()).post(this.source.accessToken(), JSONObject.toJSONString(params)).getBody();
        JSONObject accessTokenObject = JSONObject.parseObject(response);
        if (!accessTokenObject.containsKey("accessToken")) {
            throw new AuthException(JSONObject.toJSONString(response), source);
        }
        return AuthToken.builder()
            .accessToken(accessTokenObject.getString("accessToken"))
            .refreshToken(accessTokenObject.getString("refreshToken"))
            .expireIn(accessTokenObject.getIntValue("expireIn"))
            .corpId(accessTokenObject.getString("corpId"))
            .build();
    }

    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
        HttpHeader header = new HttpHeader();
        header.add("x-acs-dingtalk-access-token", authToken.getAccessToken());

        String response = new HttpUtils(config.getHttpConfig()).get(this.source.userInfo(), null, header, false).getBody();
        JSONObject object = JSONObject.parseObject(response);

        authToken.setOpenId(object.getString("openId"));
        authToken.setUnionId(object.getString("unionId"));
        return AuthUser.builder()
            .rawUserInfo(object)
            .uuid(object.getString("unionId"))
            .username(object.getString("nick"))
            .nickname(object.getString("nick"))
            .avatar(object.getString("avatarUrl"))
            .snapshotUser(object.getBooleanValue("visitor"))
            .token(authToken)
            .source(source.toString())
            .build();
    }

    /**
     * 返回获取accessToken的url
     *
     * @param code 授权码
     * @return 返回获取accessToken的url
     */
    protected String accessTokenUrl(String code) {
        return UrlBuilder.fromBaseUrl(source.accessToken())
            .queryParam("code", code)
            .queryParam("clientId", config.getClientId())
            .queryParam("clientSecret", config.getClientSecret())
            .queryParam("grantType", "authorization_code")
            .build();
    }
}
