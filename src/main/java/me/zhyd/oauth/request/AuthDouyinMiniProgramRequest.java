package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.HttpUtils;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * @author JackyTang (jackytang01(a)gmail.com)
 * @version 1.0
 * @website
 * @date 2024/04/22 00:06
 * @since 17
 */
public class AuthDouyinMiniProgramRequest extends AuthDefaultRequest {
    public AuthDouyinMiniProgramRequest(AuthConfig config) {
        super(config, AuthDefaultSource.DOUYIN_MINI_PROGRAM);
    }

    public AuthDouyinMiniProgramRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.DOUYIN_MINI_PROGRAM, authStateCache);
    }

    @Override
    public AuthToken getAccessToken(AuthCallback authCallback) {
        // 参见 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html 文档
        // 使用 code 获取对应的 openId、unionId 等字段
        String response = new HttpUtils(config.getHttpConfig()).get(accessTokenUrl(authCallback.getCode(), authCallback.getAnonymous_code())).getBody();
        JSCode2SessionResponse accessTokenObject = JSONObject.parseObject(response, JSCode2SessionResponse.class);
        assert accessTokenObject != null;
        checkResponse(accessTokenObject);
        // 拼装结果
        return AuthToken.builder()
            .openId(accessTokenObject.getOpenid())
            .unionId(accessTokenObject.getUnionId())
            .accessToken(accessTokenObject.getSessionKey())
            .anonymousOpenid(accessTokenObject.anonymousOpenid)
            .build();
    }

    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
        // 参见 https://developer.open-douyin.com/docs/resource/zh-CN/mini-game/develop/guide/open-api/info/tt-get-user-info 文档
        // 如果需要用户信息，需要在小程序调用函数后传给后端
        return AuthUser.builder()
            .username("")
            .nickname("")
            .avatar("")
            .uuid(authToken.getOpenId())
            .token(authToken)
            .source(source.toString())
            .build();
    }

    private void checkResponse(JSCode2SessionResponse response) {
        if (response.getError() != 0) {
            throw new AuthException(response.getErrorCode(), response.getErrorMsg());
        }
    }

    private String accessTokenUrl(String code, String anonymousCode) {
        return UrlBuilder.fromBaseUrl(source.accessToken())
            .queryParam("appid", config.getClientId())
            .queryParam("secret", config.getClientSecret())
            .queryParam("code", code)
            .queryParam("anonymous_code", anonymousCode)
            .build();
    }

    @Data
    @SuppressWarnings("SpellCheckingInspection")
    private static class JSCode2SessionResponse {

        @JSONField(name = "error")
        private int error;
        @JSONField(name = "errcode")
        private int errorCode;
        @JSONField(name = "errmsg")
        private String errorMsg;
        @JSONField(name = "session_key")
        private String sessionKey;
        @JSONField(name = "openid")
        private String openid;
        @JSONField(name = "anonymous_openid")
        private String anonymousOpenid;
        @JSONField(name = "unionid")
        private String unionId;

    }

}
