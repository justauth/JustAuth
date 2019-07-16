package me.zhyd.oauth.utils;

import me.zhyd.oauth.config.AuthSource;

import java.text.MessageFormat;

/**
 * Url构建工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.0
 */
public class UrlBuilder {



    private static final String GITEE_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String GITEE_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String GITEE_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}";

    private static final String DING_TALK_QRCONNECT_PATTERN = "{0}?appid={1}&response_type=code&scope=snsapi_login&redirect_uri={2}&state={3}";
    private static final String DING_TALK_USER_INFO_PATTERN = "{0}?signature={1}&timestamp={2}&accessKey={3}";

    private static final String BAIDU_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String BAIDU_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String BAIDU_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&display=popup&state={3}";
    private static final String BAIDU_REVOKE_PATTERN = "{0}?access_token={1}";

    private static final String CSDN_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String CSDN_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String CSDN_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&state={3}";

    private static final String CODING_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}";
    private static final String CODING_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String CODING_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&scope=user&state={3}";

    private static final String ALIPAY_AUTHORIZE_PATTERN = "{0}?app_id={1}&scope=auth_user&redirect_uri={2}&state={3}";


    private static final String FACEBOOK_AUTHORIZE_PATTERN = "{0}?client_id={1}&redirect_uri={2}&state={3}&response_type=code&scope=";
    private static final String FACEBOOK_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&code={3}&redirect_uri={4}&grant_type=authorization_code";
    private static final String FACEBOOK_USER_INFO_PATTERN = "{0}?access_token={1}&fields=id,name,birthday,gender,hometown,email,devices,picture.width(400)";

    private static final String DOUYIN_AUTHORIZE_PATTERN = "{0}?client_key={1}&redirect_uri={2}&state={3}&response_type=code&scope=user_info";
    private static final String DOUYIN_ACCESS_TOKEN_PATTERN = "{0}?client_key={1}&client_secret={2}&code={3}&grant_type=authorization_code";
    private static final String DOUYIN_USER_INFO_PATTERN = "{0}?access_token={1}&open_id={2}";
    private static final String DOUYIN_REFRESH_TOKEN_PATTERN = "{0}?client_key={1}&refresh_token={2}&grant_type=refresh_token";



    /**
     * 获取state，如果为空， 则默认去当前日期的时间戳
     *
     * @param state state
     */
    private static Object getState(String state) {
        return StringUtils.isEmpty(state) ? String.valueOf(System.currentTimeMillis()) : state;
    }

    /**
     * 获取gitee token的接口地址
     *
     * @param clientId     gitee 应用的Client ID
     * @param clientSecret gitee 应用的Client Secret
     * @param code         gitee 授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getGiteeAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(GITEE_ACCESS_TOKEN_PATTERN, AuthSource.GITEE.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取gitee用户详情的接口地址
     *
     * @param token gitee 应用的token
     * @return full url
     */
    public static String getGiteeUserInfoUrl(String token) {
        return MessageFormat.format(GITEE_USER_INFO_PATTERN, AuthSource.GITEE.userInfo(), token);
    }

    /**
     * 获取gitee授权地址
     *
     * @param clientId    gitee 应用的Client ID
     * @param redirectUrl gitee 应用授权成功后的回调地址
     * @param state       随机字符串，用于保持会话状态，防止CSRF攻击
     * @return json
     */
    public static String getGiteeAuthorizeUrl(String clientId, String redirectUrl, String state) {
        return MessageFormat.format(GITEE_AUTHORIZE_PATTERN, AuthSource.GITEE.authorize(), clientId, redirectUrl, getState(state));
    }

    /**
     * 获取钉钉登录二维码的地址
     *
     * @param clientId    钉钉 应用的App Id
     * @param redirectUrl 钉钉 应用授权成功后的回调地址
     * @param state       随机字符串，用于保持会话状态，防止CSRF攻击
     * @return full url
     */
    public static String getDingTalkQrConnectUrl(String clientId, String redirectUrl, String state) {
        return MessageFormat.format(DING_TALK_QRCONNECT_PATTERN, AuthSource.DINGTALK.authorize(), clientId, redirectUrl, getState(state));
    }

    /**
     * 获取钉钉用户信息的地址
     *
     * @param signature 通过appSecret计算出来的签名值，签名计算方法：https://open-doc.dingtalk.com/microapp/faquestions/hxs5v9
     * @param timestamp 当前时间戳，单位是毫秒
     * @param accessKey 钉钉 应用的App Id
     * @return full url
     */
    public static String getDingTalkUserInfoUrl(String signature, String timestamp, String accessKey) {
        return MessageFormat.format(DING_TALK_USER_INFO_PATTERN, AuthSource.DINGTALK.userInfo(), signature, timestamp, accessKey);
    }

    /**
     * 获取baidu token的接口地址
     *
     * @param clientId     baidu 应用的API Key
     * @param clientSecret baidu 应用的Secret Key
     * @param code         baidu 授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getBaiduAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(BAIDU_ACCESS_TOKEN_PATTERN, AuthSource.BAIDU.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取baidu用户详情的接口地址
     *
     * @param token baidu 应用的token
     * @return full url
     */
    public static String getBaiduUserInfoUrl(String token) {
        return MessageFormat.format(BAIDU_USER_INFO_PATTERN, AuthSource.BAIDU.userInfo(), token);
    }

    /**
     * 获取baidu授权地址
     *
     * @param clientId    baidu 应用的API Key
     * @param redirectUrl baidu 应用授权成功后的回调地址
     * @param state       随机字符串，用于保持会话状态，防止CSRF攻击
     * @return json
     */
    public static String getBaiduAuthorizeUrl(String clientId, String redirectUrl, String state) {
        return MessageFormat.format(BAIDU_AUTHORIZE_PATTERN, AuthSource.BAIDU.authorize(), clientId, redirectUrl, getState(state));
    }

    /**
     * 获取收回baidu授权的地址
     *
     * @param accessToken baidu 授权登录后的token
     * @return json
     */
    public static String getBaiduRevokeUrl(String accessToken) {
        return MessageFormat.format(BAIDU_REVOKE_PATTERN, AuthSource.BAIDU.revoke(), accessToken);
    }

    /**
     * 获取csdn token的接口地址
     *
     * @param clientId     csdn 应用的App Key
     * @param clientSecret csdn 应用的App Secret
     * @param code         csdn 授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getCsdnAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(CSDN_ACCESS_TOKEN_PATTERN, AuthSource.CSDN.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取csdn用户详情的接口地址
     *
     * @param token csdn 应用的token
     * @return full url
     */
    public static String getCsdnUserInfoUrl(String token) {
        return MessageFormat.format(CSDN_USER_INFO_PATTERN, AuthSource.CSDN.userInfo(), token);
    }

    /**
     * 获取csdn授权地址
     *
     * @param clientId    csdn 应用的Client ID
     * @param redirectUrl csdn 应用授权成功后的回调地址
     * @param state       随机字符串，用于保持会话状态，防止CSRF攻击
     * @return full url
     */
    public static String getCsdnAuthorizeUrl(String clientId, String redirectUrl, String state) {
        return MessageFormat.format(CSDN_AUTHORIZE_PATTERN, AuthSource.CSDN.authorize(), clientId, redirectUrl, getState(state));
    }

    /**
     * 获取coding token的接口地址
     *
     * @param clientId     coding 应用的App Key
     * @param clientSecret coding 应用的App Secret
     * @param code         coding 授权前的code，用来换token
     * @return full url
     */
    public static String getCodingAccessTokenUrl(String clientId, String clientSecret, String code) {
        return MessageFormat.format(CODING_ACCESS_TOKEN_PATTERN, AuthSource.CODING.accessToken(), clientId, clientSecret, code);
    }

    /**
     * 获取coding用户详情的接口地址
     *
     * @param token coding 应用的token
     * @return full url
     */
    public static String getCodingUserInfoUrl(String token) {
        return MessageFormat.format(CODING_USER_INFO_PATTERN, AuthSource.CODING.userInfo(), token);
    }

    /**
     * 获取coding授权地址
     *
     * @param clientId    coding 应用的Client ID
     * @param redirectUrl coding 应用授权成功后的回调地址
     * @param state       随机字符串，用于保持会话状态，防止CSRF攻击
     * @return full url
     */
    public static String getCodingAuthorizeUrl(String clientId, String redirectUrl, String state) {
        return MessageFormat.format(CODING_AUTHORIZE_PATTERN, AuthSource.CODING.authorize(), clientId, redirectUrl, getState(state));
    }

    /**
     * 获取alipay授权地址
     *
     * @param clientId    alipay 应用的Client ID
     * @param redirectUrl alipay 应用授权成功后的回调地址
     * @param state       随机字符串，用于保持会话状态，防止CSRF攻击
     * @return full url
     */
    public static String getAlipayAuthorizeUrl(String clientId, String redirectUrl, String state) {
        return MessageFormat.format(ALIPAY_AUTHORIZE_PATTERN, AuthSource.ALIPAY.authorize(), clientId, redirectUrl, getState(state));
    }

    /**
     * 获取Facebook授权地址
     *
     * @param clientId    Facebook 应用的Client ID
     * @param redirectUrl Facebook 应用授权成功后的回调地址
     * @param state       随机字符串，用于保持会话状态，防止CSRF攻击
     * @return full url
     */
    public static String getFacebookAuthorizeUrl(String clientId, String redirectUrl, String state) {
        return MessageFormat.format(FACEBOOK_AUTHORIZE_PATTERN, AuthSource.FACEBOOK.authorize(), clientId, redirectUrl, getState(state));
    }

    /**
     * 获取Facebook token的接口地址
     *
     * @param clientId     Facebook 应用的Client ID
     * @param clientSecret Facebook 应用的Client Secret
     * @param code         Facebook 授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getFacebookAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(FACEBOOK_ACCESS_TOKEN_PATTERN, AuthSource.FACEBOOK.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取Facebook用户详情的接口地址
     *
     * @param token Facebook 应用的token
     * @return full url
     */
    public static String getFacebookUserInfoUrl(String token) {
        return MessageFormat.format(FACEBOOK_USER_INFO_PATTERN, AuthSource.FACEBOOK.userInfo(), token);
    }

    /**
     * 获取Douyin授权地址
     *
     * @param clientId    Douyin 应用的Client ID
     * @param redirectUrl Douyin 应用授权成功后的回调地址
     * @param state       随机字符串，用于保持会话状态，防止CSRF攻击
     * @return full url
     */
    public static String getDouyinAuthorizeUrl(String clientId, String redirectUrl, String state) {
        return MessageFormat.format(DOUYIN_AUTHORIZE_PATTERN, AuthSource.DOUYIN.authorize(), clientId, redirectUrl, getState(state));
    }

    /**
     * 获取Douyin token的接口地址
     *
     * @param clientId     Douyin 应用的Client ID
     * @param clientSecret Douyin 应用的Client Secret
     * @param code         Douyin 授权前的code，用来换token
     * @return full url
     */
    public static String getDouyinAccessTokenUrl(String clientId, String clientSecret, String code) {
        return MessageFormat.format(DOUYIN_ACCESS_TOKEN_PATTERN, AuthSource.DOUYIN.accessToken(), clientId, clientSecret, code);
    }

    /**
     * 获取Douyin用户详情的接口地址
     *
     * @param token  Douyin 应用的token
     * @param openId 用户在当前应用的唯一标识 通过token接口获取
     * @return full url
     */
    public static String getDouyinUserInfoUrl(String token, String openId) {
        return MessageFormat.format(DOUYIN_USER_INFO_PATTERN, AuthSource.DOUYIN.userInfo(), token, openId);
    }

    /**
     * 获取Douyin 刷新令牌 地址
     *
     * @param clientId     Douyin 应用的client_key
     * @param refreshToken Douyin 应用返回的refresh_token
     * @return full url
     */
    public static String getDouyinRefreshUrl(String clientId, String refreshToken) {
        return MessageFormat.format(DOUYIN_REFRESH_TOKEN_PATTERN, AuthSource.DOUYIN.refresh(), clientId, refreshToken);
    }

}
