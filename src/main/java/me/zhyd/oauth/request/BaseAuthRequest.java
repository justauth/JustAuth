package me.zhyd.oauth.request;

import lombok.Data;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthConfigChecker;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Data
public abstract class BaseAuthRequest implements AuthRequest {
    protected AuthConfig config;
    protected AuthSource source;

    public BaseAuthRequest(AuthConfig config, AuthSource source) {
        this.config = config;
        this.source = source;
        if (!AuthConfigChecker.isSupportedAuth(config)) {
            throw new AuthException(ResponseStatus.PARAMETER_INCOMPLETE);
        }
    }

    protected abstract AuthToken getAccessToken(String code);

    protected abstract AuthUser getUserInfo(AuthToken authToken);

    @Override
    public AuthResponse login(String code) {
        try {
            AuthUser user = this.getUserInfo(this.getAccessToken(code));
            return AuthResponse.builder().data(user).build();
        } catch (Exception e) {
            return AuthResponse.builder().code(500).msg(e.getMessage()).build();
        }
    }

    @Override
    public String authorize() {
        String authorizeUrl = null;
        switch (source) {
            case WEIBO:
                authorizeUrl = UrlBuilder.getWeiboAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case BAIDU:
                authorizeUrl = UrlBuilder.getBaiduAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case DINGTALK:
                authorizeUrl = UrlBuilder.getDingTalkQrConnectUrl(config.getClientId(), config.getRedirectUri());
                break;
            case GITEE:
                authorizeUrl = UrlBuilder.getGiteeAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case GITHUB:
                authorizeUrl = UrlBuilder.getGithubAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case CSDN:
                authorizeUrl = UrlBuilder.getCsdnAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case CODING:
                authorizeUrl = UrlBuilder.getCodingAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case TENCEN_CLOUD:
                authorizeUrl = UrlBuilder.getTencentCloudAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case OSCHINA:
                authorizeUrl = UrlBuilder.getOschinaAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case ALIPAY:
                authorizeUrl = UrlBuilder.getAlipayAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case QQ:
                authorizeUrl = UrlBuilder.getQqAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case WECHAT:
                authorizeUrl = UrlBuilder.getWeChatAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case TAOBAO:
                authorizeUrl = UrlBuilder.getTaobaoAuthorizeUrl(config.getClientId(), config.getRedirectUri());
                break;
            case GOOGLE:
                break;
            default:
                break;
        }
        return authorizeUrl;
    }
}
