package me.zhyd.oauth.request;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.security.AbstractJwk;
import lombok.Data;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.enums.scope.AuthAppleScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthScopeUtils;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AuthAppleRequest extends AuthDefaultRequest {

    private static final String AUD = "https://appleid.apple.com";

    private volatile PrivateKey privateKey;

    public AuthAppleRequest(AuthConfig config) {
        super(config, AuthDefaultSource.APPLE);
    }

    public AuthAppleRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.APPLE, authStateCache);
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state))
            .queryParam("response_mode", "form_post")
            .queryParam("scope", this.getScopes(" ", false, AuthScopeUtils.getDefaultScopes(AuthAppleScope.values())))
            .build();
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        if (authCallback.getError() != null) {
            throw new AuthException(authCallback.getError());
        }
        this.config.setClientSecret(this.getToken());
        // if failed will throw AuthException
        String response = doPostAuthorizationCode(authCallback.getCode());
        JSONObject accessTokenObject = JSONObject.parseObject(response);
        // https://developer.apple.com/documentation/sign_in_with_apple/tokenresponse
        AuthToken.AuthTokenBuilder builder = AuthToken.builder()
            .accessToken(accessTokenObject.getString("access_token"))
            .expireIn(accessTokenObject.getIntValue("expires_in"))
            .refreshToken(accessTokenObject.getString("refresh_token"))
            .tokenType(accessTokenObject.getString("token_type"))
            .idToken(accessTokenObject.getString("id_token"));
        if (!StringUtils.isEmpty(authCallback.getUser())) {
            try {
                AppleUserInfo userInfo = JSONObject.parseObject(response, AppleUserInfo.class);
                builder.username(userInfo.getName().getFirstName() + " " + userInfo.getName().getLastName());
            } catch (Exception ignored) {
            }
        }
        return builder.build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        Base64.Decoder urlDecoder = Base64.getUrlDecoder();
        String[] idToken = authToken.getIdToken().split("\\.");
        String payload = new String(urlDecoder.decode(idToken[1]));
        JSONObject object = JSONObject.parseObject(payload);
        // https://developer.apple.com/documentation/sign_in_with_apple/sign_in_with_apple_rest_api/authenticating_users_with_sign_in_with_apple#3383773
        return AuthUser.builder()
            .rawUserInfo(object)
            .uuid(object.getString("sub"))
            .email(object.getString("email"))
            .username(authToken.getUsername())
            .token(authToken)
            .source(source.toString())
            .build();
    }

    @Override
    protected void checkConfig(AuthConfig config) {
        super.checkConfig(config);
        if (StringUtils.isEmpty(config.getClientId())) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_CLIENT_ID, source);
        }
        if (StringUtils.isEmpty(config.getClientSecret())) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_CLIENT_SECRET, source);
        }
        if (StringUtils.isEmpty(config.getKid())) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_KID, source);
        }
        if (StringUtils.isEmpty(config.getTeamId())) {
            throw new AuthException(AuthResponseStatus.ILLEGAL_TEAM_ID, source);
        }
    }

    /**
     * 获取token
     * @see <a href="https://developer.apple.com/documentation/accountorganizationaldatasharing/creating-a-client-secret">creating-a-client-secret</a>
     * @return jwt token
     */
    private String getToken() {
        return Jwts.builder().header().add(AbstractJwk.KID.getId(), this.config.getKid()).and()
            .issuer(this.config.getTeamId())
            .subject(this.config.getClientId())
            .audience().add(AUD).and()
            .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(3)))
            .issuedAt(new Date())
            .signWith(getPrivateKey())
            .compact();
    }

    private PrivateKey getPrivateKey() {
        if (this.privateKey == null) {
            synchronized (this) {
                if (this.privateKey == null) {
                    try (PEMParser pemParser = new PEMParser(new StringReader(this.config.getClientSecret()))) {
                        JcaPEMKeyConverter pemKeyConverter = new JcaPEMKeyConverter();
                        PrivateKeyInfo keyInfo = (PrivateKeyInfo) pemParser.readObject();
                        this.privateKey = pemKeyConverter.getPrivateKey(keyInfo);
                    } catch (IOException e) {
                        throw new AuthException("Failed to get apple private key", e);
                    }
                }
            }
        }
        return this.privateKey;
    }

    @Data
    static class AppleUserInfo {
        private AppleUsername name;
        private String email;
    }

    @Data
    static class AppleUsername {
        private String firstName;
        private String lastName;
    }
}
