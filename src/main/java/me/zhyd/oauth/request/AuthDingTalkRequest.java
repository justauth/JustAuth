package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.enums.AuthDingTalkErrorCode;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.url.DingtalkUrlBuilder;
import me.zhyd.oauth.url.entity.AuthUserInfoEntity;
import me.zhyd.oauth.utils.GlobalAuthUtil;

/**
 * 钉钉登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthDingTalkRequest extends BaseAuthRequest {

    public AuthDingTalkRequest(AuthConfig config) {
        super(config, AuthSource.DINGTALK, new DingtalkUrlBuilder());
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        return AuthToken.builder().accessCode(authCallback.getCode()).build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String code = authToken.getAccessCode();
        // 根据timestamp, appSecret计算签名值
        String timestamp = System.currentTimeMillis() + "";
        String urlEncodeSignature = GlobalAuthUtil.generateDingTalkSignature(config.getClientSecret(), timestamp);
        JSONObject param = new JSONObject();
        param.put("tmp_auth_code", code);
        HttpResponse response = HttpRequest.post(this.urlBuilder.getUserInfoUrl(AuthUserInfoEntity.builder()
                .signature(urlEncodeSignature)
                .timestamp(timestamp)
                .clientId(config.getClientId())
                .build()
        )).body(param.toJSONString()).execute();
        String userInfo = response.body();
        JSONObject object = JSON.parseObject(userInfo);
        AuthDingTalkErrorCode errorCode = AuthDingTalkErrorCode.getErrorCode(object.getIntValue("errcode"));
        if (AuthDingTalkErrorCode.EC0 != errorCode) {
            throw new AuthException(errorCode.getDesc());
        }
        object = object.getJSONObject("user_info");
        AuthToken token = AuthToken.builder()
                .openId(object.getString("openid"))
                .unionId(object.getString("unionid"))
                .build();
        return AuthUser.builder()
                .uuid(object.getString("unionid"))
                .nickname(object.getString("nick"))
                .username(object.getString("nick"))
                .gender(AuthUserGender.UNKNOW)
                .source(AuthSource.DINGTALK)
                .token(token)
                .build();
    }
}
