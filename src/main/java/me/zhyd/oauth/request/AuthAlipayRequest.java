package me.zhyd.oauth.request;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.consts.ApiUrl;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.model.AuthUserGender;
import me.zhyd.oauth.utils.StringUtils;

/**
 * 支付宝登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthAlipayRequest extends BaseAuthRequest {

    private AlipayClient alipayClient;

    public AuthAlipayRequest(AuthConfig config) {
        super(config, AuthSource.ALIPAY);
        this.alipayClient = new DefaultAlipayClient(ApiUrl.ALIPAY.accessToken(), config.getClientId(), config.getClientSecret(), "json", "UTF-8", config.getAlipayPublicKey(), "RSA2");
    }

    @Override
    protected AuthToken getAccessToken(String code) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(code);
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = this.alipayClient.execute(request);
        } catch (Exception e) {
            throw new AuthException("Unable to get token from alipay using code [" + code + "]", e);
        }
        if (!response.isSuccess()) {
            throw new AuthException(response.getSubMsg());
        }
        return AuthToken.builder()
                .accessToken(response.getAccessToken())
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;
        try {
            response = this.alipayClient.execute(request, accessToken);
        } catch (AlipayApiException e) {
            throw new AuthException(e.getErrMsg(), e);
        }
        if (!response.isSuccess()) {
            throw new AuthException(response.getSubMsg());
        }
        String province = response.getProvince(),
                city = response.getCity();
        return AuthUser.builder()
                .username(response.getUserName())
                .nickname(response.getNickName())
                .avatar(response.getAvatar())
                .location(String.format("%s %s", StringUtils.isEmpty(province) ? "" : province, StringUtils.isEmpty(city) ? "" : city))
                .gender(AuthUserGender.getRealGender(response.getGender()))
                .token(authToken)
                .source(AuthSource.ALIPAY)
                .build();
    }
}
