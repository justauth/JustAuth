package me.zhyd.oauth.request;

import lombok.Data;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthSource;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthConfigChecker;
import me.zhyd.oauth.utils.UrlBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/2/18 13:11
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
            throw new AuthException(ResponseStatus.UNSUPPORTED);
        }
    }

    protected String getAccessToken(String code) {
        return null;
    }

    protected abstract AuthUser getUserInfo(String accessToken);

    @Override
    public AuthResponse login(String code) {
        return AuthResponse.builder()
                .data(this.getUserInfo(this.getAccessToken(code)))
                .build();
    }

    @Override
    public void authorize(HttpServletResponse response) throws IOException {
        response.sendRedirect(this.authorize());
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
            case QQ:
                break;
            case WECHAT:
                break;
            case GOOGLE:
                break;
            default:
                break;
        }
        return authorizeUrl;
    }
}
