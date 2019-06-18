package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthDingTalkErrorCode;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.GlobalAuthUtil;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * 钉钉登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthDingTalkRequest extends BaseAuthRequest {

    public AuthDingTalkRequest(AuthConfig config) {
        super(config, AuthSource.DINGTALK);
    }

    @Override
    protected AuthToken getAccessToken(String code) {
        return AuthToken.builder()
                .accessCode(code)
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String code = authToken.getAccessCode();
        // 根据timestamp, appSecret计算签名值
        String stringToSign = System.currentTimeMillis() + "";
        String urlEncodeSignature = GlobalAuthUtil.generateDingTalkSignature(config.getClientSecret(), stringToSign);
        JSONObject param = new JSONObject();
        param.put("tmp_auth_code", code);
        HttpResponse response = HttpRequest.post(UrlBuilder.getDingTalkUserInfoUrl(urlEncodeSignature, stringToSign, config.getClientId()))
                .body(param.toJSONString())
                .execute();
        String userInfo = response.body();
        JSONObject object = JSON.parseObject(userInfo);
        AuthDingTalkErrorCode errorCode = AuthDingTalkErrorCode.getErrorCode(object.getIntValue("errcode"));
        if (!AuthDingTalkErrorCode.EC0.equals(errorCode)) {
            throw new AuthException(errorCode.getDesc());
        }
        object = object.getJSONObject("user_info");
        return AuthUser.builder()
                .uuid(object.getString("openid"))
                .nickname(object.getString("nick"))
                .username(object.getString("nick"))
                .source(AuthSource.DINGTALK)
                .build();
    }
}
