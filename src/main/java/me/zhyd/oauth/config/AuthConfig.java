package me.zhyd.oauth.config;

import com.xkcoding.http.config.HttpConfig;
import lombok.*;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.utils.StringUtils;

import java.util.List;

/**
 * JustAuth配置类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @since 1.8
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthConfig {

    /**
     * 客户端id：对应各平台的appKey
     */
    private String clientId;

    /**
     * 客户端Secret：对应各平台的appSecret
     */
    private String clientSecret;

    /**
     * 登录成功后的回调地址
     */
    private String redirectUri;

    /**
     * 支付宝公钥：当选择支付宝登录时，该值可用
     * 对应“RSA2(SHA256)密钥”中的“支付宝公钥”
     */
    private String alipayPublicKey;

    /**
     * 是否需要申请unionid，目前只针对qq登录
     * 注：qq授权登录时，获取unionid需要单独发送邮件申请权限。如果个人开发者账号中申请了该权限，可以将该值置为true，在获取openId时就会同步获取unionId
     * 参考链接：http://wiki.connect.qq.com/unionid%E4%BB%8B%E7%BB%8D
     * <p>
     * 1.7.1版本新增参数
     */
    private boolean unionId;

    /**
     * Stack Overflow Key
     * <p>
     *
     * @since 1.9.0
     */
    private String stackOverflowKey;

    /**
     * 企业微信，授权方的网页应用ID
     *
     * @since 1.10.0
     */
    private String agentId;

    /**
     * 域名前缀。
     * <p>
     * 使用 Coding 登录和 Okta 登录时，需要传该值。
     * <p>
     * Coding 登录：团队域名前缀，比如以“ https://justauth.coding.net ”为例，{@code domainPrefix} = justauth
     * <p>
     * Okta 登录：Okta 账号域名前缀，比如以“ https://justauth.okta.com ”为例，{@code domainPrefix} = justauth
     *
     * @since 1.16.0
     */
    private String domainPrefix;

    /**
     * 针对国外服务可以单独设置代理
     * HttpConfig config = new HttpConfig();
     * config.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)));
     * config.setTimeout(15000);
     *
     * @since 1.15.5
     */
    private HttpConfig httpConfig;

    /**
     * 忽略校验 {@code state} 参数，默认不开启。当 {@code ignoreCheckState} 为 {@code true} 时，
     * {@link me.zhyd.oauth.request.AuthDefaultRequest#login(AuthCallback)} 将不会校验 {@code state} 的合法性。
     * <p>
     * 使用场景：当且仅当使用自实现 {@code state} 校验逻辑时开启
     * <p>
     * 以下场景使用方案仅作参考：
     * 1. 授权、登录为同端，并且全部使用 JustAuth 实现时，该值建议设为 {@code false};
     * 2. 授权和登录为不同端实现时，比如前端页面拼装 {@code authorizeUrl}，并且前端自行对{@code state}进行校验，
     * 后端只负责使用{@code code}获取用户信息时，该值建议设为 {@code true};
     *
     * <strong>如非特殊需要，不建议开启这个配置</strong>
     * <p>
     * 该方案主要为了解决以下类似场景的问题：
     *
     * @see <a href="https://github.com/justauth/JustAuth/issues/83">https://github.com/justauth/JustAuth/issues/83</a>
     * @since 1.15.6
     */
    private boolean ignoreCheckState;

    /**
     * 支持自定义授权平台的 scope 内容
     *
     * @since 1.15.7
     */
    private List<String> scopes;

    /**
     * 设备ID, 设备唯一标识ID
     *
     * @since 1.15.8
     */
    private String deviceId;

    /**
     * 喜马拉雅：客户端操作系统类型，1-iOS系统，2-Android系统，3-Web
     *
     * @since 1.15.9
     */
    private Integer clientOsType;

    /**
     * 喜马拉雅：客户端包名，如果 {@link AuthConfig#clientOsType} 为1或2时必填。对Android客户端是包名，对IOS客户端是Bundle ID
     *
     * @since 1.15.9
     */
    private String packId;

    /**
     * 是否开启 PKCE 模式，该配置仅用于支持 PKCE 模式的平台，针对无服务应用，不推荐使用隐式授权，推荐使用 PKCE 模式
     *
     * @since 1.15.9
     */
    private boolean pkce;

    /**
     * Okta 授权服务器的 ID， 默认为 default。如果要使用自定义授权服务，此处传实际的授权服务器 ID（一个随机串）
     * <p>
     * 创建自定义授权服务器，请参考：
     * <p>
     * ① https://developer.okta.com/docs/concepts/auth-servers
     * <p>
     * ② https://developer.okta.com/docs/guides/customize-authz-server
     *
     * @since 1.16.0
     */
    private String authServerId;
    /**
     * 忽略校验 {@code redirectUri} 参数，默认不开启。当 {@code ignoreCheckRedirectUri} 为 {@code true} 时，
     * {@link me.zhyd.oauth.utils.AuthChecker#checkConfig(AuthConfig, AuthSource)} 将不会校验 {@code redirectUri} 的合法性。
     *
     * @since 1.16.1
     */
    private boolean ignoreCheckRedirectUri;

    /**
     * 适配 builder 模式 set 值的情况
     *
     * @return authServerId
     */
    public String getAuthServerId() {
        return StringUtils.isEmpty(authServerId) ? "default" : authServerId;
    }
}
