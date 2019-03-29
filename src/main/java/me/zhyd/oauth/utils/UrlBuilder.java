package me.zhyd.oauth.utils;

import me.zhyd.oauth.consts.ApiUrl;

import java.text.MessageFormat;

/**
 * Url构建工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.0
 */
public class UrlBuilder {

    private static final String GITHUB_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&code={3}&redirect_uri={4}";
    private static final String GITHUB_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String GITHUB_AUTHORIZE_PATTERN = "{0}?client_id={1}&state=1&redirect_uri={2}";

    private static final String WEIBO_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String WEIBO_USER_INFO_PATTERN = "{0}?{1}";
    private static final String WEIBO_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}";

    private static final String GITEE_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String GITEE_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String GITEE_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}";

    private static final String DING_TALK_QRCONNECT_PATTERN = "{0}?appid={1}&response_type=code&scope=snsapi_login&state=STATE&redirect_uri={2}";
    private static final String DING_TALK_USER_INFO_PATTERN = "{0}?signature={1}&timestamp={2}&accessKey={3}";

    private static final String BAIDU_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String BAIDU_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String BAIDU_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&display=popup";
    private static final String BAIDU_REVOKE_PATTERN = "{0}?access_token={1}";

    private static final String CSDN_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}";
    private static final String CSDN_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String CSDN_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}";

    private static final String CODING_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}";
    private static final String CODING_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String CODING_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&scope=user";

    private static final String TENCENT_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}";
    private static final String TENCENT_USER_INFO_PATTERN = "{0}?access_token={1}";
    private static final String TENCENT_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}&scope=user";

    private static final String OSCHINA_ACCESS_TOKEN_PATTERN = "{0}?client_id={1}&client_secret={2}&grant_type=authorization_code&code={3}&redirect_uri={4}&dataType=json";
    private static final String OSCHINA_USER_INFO_PATTERN = "{0}?access_token={1}&dataType=json";
    private static final String OSCHINA_AUTHORIZE_PATTERN = "{0}?client_id={1}&response_type=code&redirect_uri={2}";

    private static final String ALIPAY_AUTHORIZE_PATTERN = "{0}?app_id={1}&scope=auth_user&redirect_uri={2}&state=init";

    /**
     * 获取githubtoken的接口地址
     *
     * @param clientId     github应用的Client ID
     * @param clientSecret github应用的Client Secret
     * @param code         github授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getGithubAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(GITHUB_ACCESS_TOKEN_PATTERN, ApiUrl.GITHUB.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取github用户详情的接口地址
     *
     * @param token github 应用的token
     * @return full url
     */
    public static String getGithubUserInfoUrl(String token) {
        return MessageFormat.format(GITHUB_USER_INFO_PATTERN, ApiUrl.GITHUB.userInfo(), token);
    }

    /**
     * 获取github授权地址
     *
     * @param clientId    github 应用的Client ID
     * @param redirectUrl github 应用授权成功后的回调地址
     * @return full url
     */
    public static String getGithubAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(GITHUB_AUTHORIZE_PATTERN, ApiUrl.GITHUB.authorize(), clientId, redirectUrl);
    }

    /**
     * 获取weibo token的接口地址
     *
     * @param clientId     weibo应用的App Key
     * @param clientSecret weibo应用的App Secret
     * @param code         weibo授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getWeiboAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(WEIBO_ACCESS_TOKEN_PATTERN, ApiUrl.WEIBO.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取weibo用户详情的接口地址
     *
     * @param token weibo 应用的token
     * @return full url
     */
    public static String getWeiboUserInfoUrl(String token) {
        return MessageFormat.format(WEIBO_USER_INFO_PATTERN, ApiUrl.WEIBO.userInfo(), token);
    }

    /**
     * 获取weibo授权地址
     *
     * @param clientId    weibo 应用的Client ID
     * @param redirectUrl weibo 应用授权成功后的回调地址
     * @return full url
     */
    public static String getWeiboAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(WEIBO_AUTHORIZE_PATTERN, ApiUrl.WEIBO.authorize(), clientId, redirectUrl);
    }

    /**
     * 获取gitee token的接口地址
     *
     * @param clientId     gitee应用的Client ID
     * @param clientSecret gitee应用的Client Secret
     * @param code         gitee授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getGiteeAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(GITEE_ACCESS_TOKEN_PATTERN, ApiUrl.GITEE.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取gitee用户详情的接口地址
     *
     * @param token gitee 应用的token
     * @return full url
     */
    public static String getGiteeUserInfoUrl(String token) {
        return MessageFormat.format(GITEE_USER_INFO_PATTERN, ApiUrl.GITEE.userInfo(), token);
    }

    /**
     * 获取gitee授权地址
     *
     * @param clientId    gitee 应用的Client ID
     * @param redirectUrl gitee 应用授权成功后的回调地址
     * @return json
     */
    public static String getGiteeAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(GITEE_AUTHORIZE_PATTERN, ApiUrl.GITEE.authorize(), clientId, redirectUrl);
    }

    /**
     * 获取钉钉登录二维码的地址
     *
     * @param clientId    钉钉 应用的App Id
     * @param redirectUrl 钉钉 应用授权成功后的回调地址
     * @return full url
     */
    public static String getDingTalkQrConnectUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(DING_TALK_QRCONNECT_PATTERN, ApiUrl.DINGTALK.authorize(), clientId, redirectUrl);
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
        return MessageFormat.format(DING_TALK_USER_INFO_PATTERN, ApiUrl.DINGTALK.userInfo(), signature, timestamp, accessKey);
    }

    /**
     * 获取baidu token的接口地址
     *
     * @param clientId     baidu应用的API Key
     * @param clientSecret baidu应用的Secret Key
     * @param code         baidu授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getBaiduAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(BAIDU_ACCESS_TOKEN_PATTERN, ApiUrl.BAIDU.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取baidu用户详情的接口地址
     *
     * @param token baidu 应用的token
     * @return full url
     */
    public static String getBaiduUserInfoUrl(String token) {
        return MessageFormat.format(BAIDU_USER_INFO_PATTERN, ApiUrl.BAIDU.userInfo(), token);
    }

    /**
     * 获取baidu授权地址
     *
     * @param clientId    baidu 应用的API Key
     * @param redirectUrl baidu 应用授权成功后的回调地址
     * @return json
     */
    public static String getBaiduAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(BAIDU_AUTHORIZE_PATTERN, ApiUrl.BAIDU.authorize(), clientId, redirectUrl);
    }

    /**
     * 获取收回baidu授权的地址
     *
     * @param accessToken baidu授权登录后的token
     * @return json
     */
    public static String getBaiduRevokeUrl(String accessToken) {
        return MessageFormat.format(BAIDU_REVOKE_PATTERN, ApiUrl.BAIDU.revoke(), accessToken);
    }

    /**
     * 获取csdn token的接口地址
     *
     * @param clientId     csdn应用的App Key
     * @param clientSecret csdn应用的App Secret
     * @param code         csdn授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getCsdnAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(CSDN_ACCESS_TOKEN_PATTERN, ApiUrl.CSDN.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取csdn用户详情的接口地址
     *
     * @param token csdn 应用的token
     * @return full url
     */
    public static String getCsdnUserInfoUrl(String token) {
        return MessageFormat.format(CSDN_USER_INFO_PATTERN, ApiUrl.CSDN.userInfo(), token);
    }

    /**
     * 获取csdn授权地址
     *
     * @param clientId    csdn 应用的Client ID
     * @param redirectUrl csdn 应用授权成功后的回调地址
     * @return full url
     */
    public static String getCsdnAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(CSDN_AUTHORIZE_PATTERN, ApiUrl.CSDN.authorize(), clientId, redirectUrl);
    }

    /**
     * 获取coding token的接口地址
     *
     * @param clientId     coding应用的App Key
     * @param clientSecret coding应用的App Secret
     * @param code         coding授权前的code，用来换token
     * @return full url
     */
    public static String getCodingAccessTokenUrl(String clientId, String clientSecret, String code) {
        return MessageFormat.format(CODING_ACCESS_TOKEN_PATTERN, ApiUrl.CODING.accessToken(), clientId, clientSecret, code);
    }

    /**
     * 获取coding用户详情的接口地址
     *
     * @param token coding 应用的token
     * @return full url
     */
    public static String getCodingUserInfoUrl(String token) {
        return MessageFormat.format(CODING_USER_INFO_PATTERN, ApiUrl.CODING.userInfo(), token);
    }

    /**
     * 获取coding授权地址
     *
     * @param clientId    coding 应用的Client ID
     * @param redirectUrl coding 应用授权成功后的回调地址
     * @return full url
     */
    public static String getCodingAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(CODING_AUTHORIZE_PATTERN, ApiUrl.CODING.authorize(), clientId, redirectUrl);
    }

    /**
     * 获取腾讯云开发者平台 token的接口地址
     *
     * @param clientId     coding应用的App Key
     * @param clientSecret coding应用的App Secret
     * @param code         coding授权前的code，用来换token
     * @return full url
     */
    public static String getTencentCloudAccessTokenUrl(String clientId, String clientSecret, String code) {
        return MessageFormat.format(TENCENT_ACCESS_TOKEN_PATTERN, ApiUrl.TENCENTCLOUD.accessToken(), clientId, clientSecret, code);
    }

    /**
     * 获取腾讯云开发者平台用户详情的接口地址
     *
     * @param token coding 应用的token
     * @return full url
     */
    public static String getTencentCloudUserInfoUrl(String token) {
        return MessageFormat.format(TENCENT_USER_INFO_PATTERN, ApiUrl.TENCENTCLOUD.userInfo(), token);
    }

    /**
     * 获取腾讯云开发者平台授权地址
     *
     * @param clientId    coding 应用的Client ID
     * @param redirectUrl coding 应用授权成功后的回调地址
     * @return full url
     */
    public static String getTencentCloudAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(TENCENT_AUTHORIZE_PATTERN, ApiUrl.TENCENTCLOUD.authorize(), clientId, redirectUrl);
    }

    /**
     * 获取oschina token的接口地址
     *
     * @param clientId     oschina应用的App Key
     * @param clientSecret oschina应用的App Secret
     * @param code         oschina授权前的code，用来换token
     * @param redirectUri  待跳转的页面
     * @return full url
     */
    public static String getOschinaAccessTokenUrl(String clientId, String clientSecret, String code, String redirectUri) {
        return MessageFormat.format(OSCHINA_ACCESS_TOKEN_PATTERN, ApiUrl.OSCHINA.accessToken(), clientId, clientSecret, code, redirectUri);
    }

    /**
     * 获取oschina用户详情的接口地址
     *
     * @param token oschina 应用的token
     * @return full url
     */
    public static String getOschinaUserInfoUrl(String token) {
        return MessageFormat.format(OSCHINA_USER_INFO_PATTERN, ApiUrl.OSCHINA.userInfo(), token);
    }

    /**
     * 获取oschina授权地址
     *
     * @param clientId    oschina 应用的Client ID
     * @param redirectUrl oschina 应用授权成功后的回调地址
     * @return full url
     */
    public static String getOschinaAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(OSCHINA_AUTHORIZE_PATTERN, ApiUrl.OSCHINA.authorize(), clientId, redirectUrl);
    }

    /**
     * 获取alipay授权地址
     *
     * @param clientId    alipay 应用的Client ID
     * @param redirectUrl alipay 应用授权成功后的回调地址
     * @return full url
     */
    public static String getAlipayAuthorizeUrl(String clientId, String redirectUrl) {
        return MessageFormat.format(ALIPAY_AUTHORIZE_PATTERN, ApiUrl.ALIPAY.authorize(), clientId, redirectUrl);
    }
}
