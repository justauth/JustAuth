package me.zhyd.oauth.request;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
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
import me.zhyd.oauth.utils.GlobalAuthUtil;
import me.zhyd.oauth.utils.StringUtils;
import me.zhyd.oauth.utils.UrlBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

/**
 * 京东登录
 * link: http://open.jd.com/home/home#/doc/common?listId=717
 *
 * @author harry.lee (harryleexyz@qq.com)
 * @since
 */
public class AuthJdRequest extends AuthDefaultRequest {

    public AuthJdRequest(AuthConfig config) {
        super(config, AuthDefaultSource.JD);
    }

    public AuthJdRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthDefaultSource.JD, authStateCache);
    }

    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = HttpRequest.post(source.accessToken())
            .form("app_key", config.getClientId())
            .form("app_secret", config.getClientSecret())
            .form("grant_type", "authorization_code")
            .form("code", authCallback.getCode())
            .execute();
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        return AuthToken.builder()
            .accessToken(object.getString("access_token"))
            .expireIn(object.getIntValue("expires_in"))
            .refreshToken(object.getString("refresh_token"))
            .scope(object.getString("scope"))
            .openId(object.getString("open_id"))
            .build();
    }

    /**
     * link: http://jos.jd.com/api/showTools.htm?id=3051&groupId=106
     * postUrl: https://api.jd.com/routerjson?v=2.0&method=jingdong.user.getUserInfoByOpenId
     * &app_key=x&access_token=x&360buy_param_json={"openId":"x"}
     * &timestamp=2019-09-11 11:12:26&sign=DB5278CD12443BEA22C5E5EA05A30D2B
     * @param authToken token信息
     * @return
     */
    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        UrlBuilder urlBuilder = UrlBuilder.fromBaseUrl(source.userInfo())
            .queryParam("access_token", authToken.getAccessToken())
            .queryParam("app_key", config.getClientId())
            .queryParam("method", "jingdong.user.getUserInfoByOpenId")
            .queryParam("360buy_param_json", "{\"openId\":\"" + authToken.getOpenId() + "\"}")
            .queryParam("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .queryParam("v", "2.0");
        urlBuilder.queryParam("sign", sign(urlBuilder.getReadParams()));
        HttpResponse response = HttpRequest.post(urlBuilder.build(true)).execute();
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        JSONObject data = this.getUserDataJsonObject(object);

        return AuthUser.builder()
            .uuid(authToken.getOpenId())
            .username(data.getString("nickname"))
            .nickname(data.getString("nickname"))
            .avatar(data.getString("imageUrl"))
            .gender(AuthUserGender.getRealGender(data.getString("gendar")))
            .token(authToken)
            .source(source.toString())
            .build();
    }

    /**
     * 个人用户无法申请应用
     * 暂时只能参考官网给出的返回结果解析
     * link: http://open.jd.com/home/home#/doc/api?apiCateId=106&apiId=3051&apiName=jingdong.user.getUserInfoByOpenId
     *
     * @param object 请求返回结果
     * @return data JSONObject
     */
    private JSONObject getUserDataJsonObject(JSONObject object) {
        return object.getJSONObject("jingdong_user_getUserInfoByOpenId_response")
            .getJSONObject("getuserinfobyappidandopenid_result")
            .getJSONObject("data");
    }

    /**
     * 宙斯签名规则过程如下:
     * 将所有请求参数按照字母先后顺序排列，例如将access_token,app_key,method,timestamp,v 排序为access_token,app_key,method,timestamp,v
     * 1.把所有参数名和参数值进行拼接，例如：access_tokenxxxapp_keyxxxmethodxxxxxxtimestampxxxxxxvx
     * 2.把appSecret夹在字符串的两端，例如：appSecret+XXXX+appSecret
     * 3.使用MD5进行加密，再转化成大写
     * link: http://open.jd.com/home/home#/doc/common?listId=890
     * link: https://github.com/pingjiang/jd-open-api-sdk-src/blob/master/src/main/java/com/jd/open/api/sdk/DefaultJdClient.java
     *
     * @param params
     * @return
     */
    private String sign(Map<String, Object> params) {
        // 放入 TreeMap 排序
        Map<String, Object> treeMap = new TreeMap<>(params);
        String appSecret = config.getClientSecret();
        StringBuilder signBuilder = new StringBuilder(appSecret);
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            String name = entry.getKey();
            String value = (String) entry.getValue();
            if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(value)) {
                signBuilder.append(name).append(value);
            }
        }
        signBuilder.append(appSecret);
        try {
            return GlobalAuthUtil.jdMd5(signBuilder.toString());
        } catch (Exception e) {
            throw new AuthException("build sign to jdMd5 error");
        }
    }

    @Override
    public AuthResponse refresh(AuthToken oldToken) {
        HttpResponse response = HttpRequest.post(source.refresh())
            .form("app_key", config.getClientId())
            .form("app_secret", config.getClientSecret())
            .form("grant_type", "refresh_token")
            .form("refresh_token", oldToken.getRefreshToken())
            .execute();
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        return AuthResponse.builder()
            .code(AuthResponseStatus.SUCCESS.getCode())
            .data(AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .expireIn(object.getIntValue("expires_in"))
                .refreshToken(object.getString("refresh_token"))
                .scope(object.getString("scope"))
                .openId(object.getString("open_id"))
                .build())
            .build();
    }

    private void checkResponse(JSONObject object) {
        if (object.containsKey("msg")) {
            throw new AuthException(object.getString("msg"));
        }
        if (object.containsKey("error_response")) {
            throw new AuthException(object.getJSONObject("error_response").getString("zh_desc"));
        }
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
            .queryParam("app_key", config.getClientId())
            .queryParam("response_type", "code")
            .queryParam("redirect_uri", config.getRedirectUri())
            .queryParam("scope", "snsapi_base")
            .queryParam("state", getRealState(state))
            .build();
    }

}
