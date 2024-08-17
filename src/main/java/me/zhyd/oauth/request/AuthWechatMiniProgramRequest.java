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
 * 微信小程序授权登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @author yudaocode
 * @version 1.0.0
 * @since 1.0.0
 */
public class AuthWechatMiniProgramRequest extends AuthDefaultRequest {
    public AuthWechatMiniProgramRequest(AuthConfig config) {
        super(config, AuthDefaultSource.WECHAT_MINI_PROGRAM);
    }

    public AuthWechatMiniProgramRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.WECHAT_MINI_PROGRAM, authStateCache);
    }

    @Override
    public AuthToken getAccessToken(AuthCallback authCallback) {
        // 参见 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html 文档
        // 使用 code 获取对应的 openId、unionId 等字段
        String response = new HttpUtils(config.getHttpConfig()).get(accessTokenUrl(authCallback.getCode())).getBody();
        JSCode2SessionResponse accessTokenObject = JSONObject.parseObject(response, JSCode2SessionResponse.class);
        assert accessTokenObject != null;
        checkResponse(accessTokenObject);
        // 拼装结果
        return AuthToken.builder()
            .openId(accessTokenObject.getOpenid())
            .unionId(accessTokenObject.getUnionId())
            .accessToken(accessTokenObject.getSessionKey())
            .build();
    }

    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
        // 参见 https://developers.weixin.qq.com/miniprogram/dev/api/open-api/user-info/wx.getUserProfile.html 文档
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

    /**
     * 检查响应内容是否正确
     *
     * @param response 请求响应内容
     */
    private void checkResponse(JSCode2SessionResponse response) {
        if (response.getErrorCode() != 0) {
            throw new AuthException(response.getErrorCode(), response.getErrorMsg());
        }
    }

    @Override
    protected String accessTokenUrl(String code) {
        return UrlBuilder.fromBaseUrl(source.accessToken())
            .queryParam("appid", config.getClientId())
            .queryParam("secret", config.getClientSecret())
            .queryParam("js_code", code)
            .queryParam("grant_type", "authorization_code")
            .build();
    }

    @Data
    @SuppressWarnings("SpellCheckingInspection")
    private static class JSCode2SessionResponse {

        @JSONField(name = "errcode")
        private int errorCode;
        @JSONField(name = "errmsg")
        private String errorMsg;
        @JSONField(name = "session_key")
        private String sessionKey;
        private String openid;
        @JSONField(name = "unionid")
        private String unionId;

    }

}
