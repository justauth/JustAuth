package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.GlobalAuthUtil;
import me.zhyd.oauth.utils.UrlBuilder;

import java.util.Map;

/**
 * Github登录
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public class AuthGithubRequest extends BaseAuthRequest {

    public AuthGithubRequest(AuthConfig config) {
        super(config, AuthSource.GITHUB);
    }

    @Override
    protected AuthToken getAccessToken(String code) {
        String accessTokenUrl = UrlBuilder.getGithubAccessTokenUrl(config.getClientId(), config.getClientSecret(), code, config
                .getRedirectUri());
        HttpResponse response = HttpRequest.post(accessTokenUrl).execute();
        Map<String, String> res = GlobalAuthUtil.parseStringToMap(response.body());
        if (res.containsKey("error")) {
            throw new AuthException(res.get("error") + ":" + res.get("error_description"));
        }
        return AuthToken.builder().accessToken(res.get("access_token")).build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        HttpResponse response = HttpRequest.get(UrlBuilder.getGithubUserInfoUrl(accessToken)).execute();
        String userInfo = response.body();
        JSONObject object = JSONObject.parseObject(userInfo);
        return AuthUser.builder()
                .uuid(object.getString("id"))
                .username(object.getString("login"))
                .avatar(object.getString("avatar_url"))
                .blog(object.getString("blog"))
                .nickname(object.getString("name"))
                .company(object.getString("company"))
                .location(object.getString("location"))
                .email(object.getString("email"))
                .remark(object.getString("bio"))
                .token(authToken)
                .source(AuthSource.GITHUB)
                .build();
    }

    /**
     * 返回认证url，可自行跳转页面
     *
     * @return 返回授权地址
     */
    @Override
    public String authorize() {
        return UrlBuilder.getGithubAuthorizeUrl(config.getClientId(), config.getRedirectUri());
    }
}
