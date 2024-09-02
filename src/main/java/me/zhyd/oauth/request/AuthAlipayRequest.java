package me.zhyd.oauth.request;

import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthChecker;
import me.zhyd.oauth.utils.GlobalAuthUtils;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import java.net.InetSocketAddress;

/**
 * 支付宝登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.0.1
 */
public class AuthAlipayRequest extends AuthDefaultRequest {

    /**
     * 支付宝公钥：当选择支付宝登录时，该值可用
     * 对应“RSA2(SHA256)密钥”中的“支付宝公钥”
     */
    private final String alipayPublicKey;

    private final AlipayClient alipayClient;

    private static final String GATEWAY = "https://openapi.alipay.com/gateway.do";

    /**
     * @see AuthAlipayRequest#AuthAlipayRequest(me.zhyd.oauth.config.AuthConfig, java.lang.String)
     * @deprecated 请使用带有"alipayPublicKey"参数的构造方法
     */
    @Deprecated
    public AuthAlipayRequest(AuthConfig config) {
        this(config, (String) null);
    }

    /**
     * @see AuthAlipayRequest#AuthAlipayRequest(me.zhyd.oauth.config.AuthConfig, java.lang.String, me.zhyd.oauth.cache.AuthStateCache)
     * @deprecated 请使用带有"alipayPublicKey"参数的构造方法
     */
    @Deprecated
    public AuthAlipayRequest(AuthConfig config, AuthStateCache authStateCache) {
        this(config, null, authStateCache);
    }

    /**
     * @see AuthAlipayRequest#AuthAlipayRequest(me.zhyd.oauth.config.AuthConfig, java.lang.String, me.zhyd.oauth.cache.AuthStateCache, java.lang.String, java.lang.Integer)
     * @deprecated 请使用带有"alipayPublicKey"参数的构造方法
     */
    @Deprecated
    public AuthAlipayRequest(AuthConfig config, AuthStateCache authStateCache, String proxyHost, Integer proxyPort) {
        this(config, null, authStateCache, proxyHost, proxyPort);
    }

    /**
     * 构造方法，需要设置"alipayPublicKey"
     *
     * @param config          公共的OAuth配置
     * @param alipayPublicKey 支付宝公钥
     * @see AuthAlipayRequest#AuthAlipayRequest(me.zhyd.oauth.config.AuthConfig)
     */
    public AuthAlipayRequest(AuthConfig config, String alipayPublicKey) {
        super(config, AuthDefaultSource.ALIPAY);
        this.alipayPublicKey = determineAlipayPublicKey(alipayPublicKey, config);
        check(config);
        this.alipayClient = new DefaultAlipayClient(GATEWAY, config.getClientId(), config.getClientSecret(), "json", "UTF-8", this.alipayPublicKey, "RSA2");
    }

    /**
     * 构造方法，需要设置"alipayPublicKey"
     *
     * @param config          公共的OAuth配置
     * @param alipayPublicKey 支付宝公钥
     * @see AuthAlipayRequest#AuthAlipayRequest(me.zhyd.oauth.config.AuthConfig, me.zhyd.oauth.cache.AuthStateCache)
     */
    public AuthAlipayRequest(AuthConfig config, String alipayPublicKey, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.ALIPAY, authStateCache);
        this.alipayPublicKey = determineAlipayPublicKey(alipayPublicKey, config);
        check(config);
        if (config.getHttpConfig() != null && config.getHttpConfig().getProxy() != null
            && config.getHttpConfig().getProxy().address() instanceof InetSocketAddress) {
            InetSocketAddress address = (InetSocketAddress) config.getHttpConfig().getProxy().address();
            this.alipayClient = new DefaultAlipayClient(GATEWAY, config.getClientId(), config.getClientSecret(),
                "json", "UTF-8", this.alipayPublicKey, "RSA2", address.getHostName(), address.getPort());
        } else {
            this.alipayClient = new DefaultAlipayClient(GATEWAY, config.getClientId(), config.getClientSecret(),
                "json", "UTF-8", this.alipayPublicKey, "RSA2");
        }
    }

    /**
     * 构造方法，需要设置"alipayPublicKey"
     *
     * @param config          公共的OAuth配置
     * @param alipayPublicKey 支付宝公钥
     * @see AuthAlipayRequest#AuthAlipayRequest(me.zhyd.oauth.config.AuthConfig, me.zhyd.oauth.cache.AuthStateCache, java.lang.String, java.lang.Integer)
     */
    public AuthAlipayRequest(AuthConfig config, String alipayPublicKey, AuthStateCache authStateCache, String proxyHost, Integer proxyPort) {
        super(config, AuthDefaultSource.ALIPAY, authStateCache);
        this.alipayPublicKey = determineAlipayPublicKey(alipayPublicKey, config);
        check(config);
        this.alipayClient = new DefaultAlipayClient(GATEWAY, config.getClientId(), config.getClientSecret(),
            "json", "UTF-8", this.alipayPublicKey, "RSA2", proxyHost, proxyPort);
    }

    private String determineAlipayPublicKey(String alipayPublicKey, AuthConfig config) {
        return alipayPublicKey != null ? alipayPublicKey : config.getAlipayPublicKey();
    }

    protected void check(AuthConfig config) {
        AuthChecker.checkConfig(config, AuthDefaultSource.ALIPAY);

        if (!StringUtils.isNotEmpty(alipayPublicKey)) {
            throw new AuthException(AuthResponseStatus.PARAMETER_INCOMPLETE, AuthDefaultSource.ALIPAY);
        }

        // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1
        if (GlobalAuthUtils.isLocalHost(config.getRedirectUri())) {
            // The redirect uri of alipay is forbidden to use localhost or 127.0.0.1
            throw new AuthException(AuthResponseStatus.ILLEGAL_REDIRECT_URI, AuthDefaultSource.ALIPAY);
        }
    }

    @Override
    protected void checkCode(AuthCallback authCallback) {
        if (StringUtils.isEmpty(authCallback.getAuth_code())) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_CODE, source);
        }
    }

    @Override
    public AuthToken getAccessToken(AuthCallback authCallback) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCallback.getAuth_code());
        AlipaySystemOauthTokenResponse response;
        try {
            response = this.alipayClient.execute(request);
        } catch (Exception e) {
            throw new AuthException(e);
        }
        if (!response.isSuccess()) {
            throw new AuthException(response.getSubMsg());
        }
        return AuthToken.builder()
            .accessToken(response.getAccessToken())
            .uid(response.getUserId())
            .expireIn(Integer.parseInt(response.getExpiresIn()))
            .refreshToken(response.getRefreshToken())
            .build();
    }

    /**
     * 刷新access token （续期）
     *
     * @param authToken 登录成功后返回的Token信息
     * @return AuthResponse
     */
    @Override
    public AuthResponse<AuthToken> refresh(AuthToken authToken) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("refresh_token");
        request.setRefreshToken(authToken.getRefreshToken());
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = this.alipayClient.execute(request);
        } catch (Exception e) {
            throw new AuthException(e);
        }
        if (!response.isSuccess()) {
            throw new AuthException(response.getSubMsg());
        }
        return AuthResponse.<AuthToken>builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(response.getAccessToken())
                .uid(response.getUserId())
                .expireIn(Integer.parseInt(response.getExpiresIn()))
                .refreshToken(response.getRefreshToken())
                .build())
            .build();
    }

    @Override
    public AuthUser getUserInfo(AuthToken authToken) {
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

        String province = response.getProvince(), city = response.getCity();
        String location = String.format("%s %s", StringUtils.isEmpty(province) ? "" : province, StringUtils.isEmpty(city) ? "" : city);

        return AuthUser.builder()
            .rawUserInfo(JSONObject.parseObject(JSONObject.toJSONString(response)))
            .uuid(response.getUserId())
            .username(StringUtils.isEmpty(response.getUserName()) ? response.getNickName() : response.getUserName())
            .nickname(response.getNickName())
            .avatar(response.getAvatar())
            .location(location)
            .gender(AuthUserGender.getRealGender(response.getGender()))
            .token(authToken)
            .source(source.toString())
            .build();
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     * @since 1.9.3
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("app_id", config.getClientId())
            .queryParam("scope", "auth_user")
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("state", getRealState(state))
            .build();
    }
}
