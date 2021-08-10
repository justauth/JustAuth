package me.zhyd.oauth.config;

import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.AuthDefaultRequest;
import me.zhyd.oauth.request.AuthExtendRequest;
import me.zhyd.oauth.request.AuthRequest;

/**
 * 测试自定义实现{@link AuthSource}接口后的枚举类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.12.0
 */
public enum AuthExtendSource implements AuthSource {

    OTHER (AuthExtendRequest.class){
        /**
         * 授权的api
         *
         * @return url
         */
        @Override
        public String authorize() {
            return "http://authorize";
        }

        /**
         * 获取accessToken的api
         *
         * @return url
         */
        @Override
        public String accessToken() {
            return "http://accessToken";
        }

        /**
         * 获取用户信息的api
         *
         * @return url
         */
        @Override
        public String userInfo() {
            return null;
        }

        /**
         * 取消授权的api
         *
         * @return url
         */
        @Override
        public String revoke() {
            return null;
        }

        /**
         * 刷新授权的api
         *
         * @return url
         */
        @Override
        public String refresh() {
            return null;
        }
    };

    private Class<? extends AuthDefaultRequest> targetClass;

    AuthExtendSource(Class<? extends AuthDefaultRequest> targetClass) {
        this.targetClass = targetClass;
    }

    public AuthRequest getAuthRequestInstance(AuthConfig authConfig) {
        return this.getAuthRequestInstance(authConfig,null);
    }

    public AuthRequest getAuthRequestInstance(AuthConfig authConfig, AuthStateCache authStateCache) {
        try {
            if(authStateCache==null){
                return this.targetClass.getDeclaredConstructor(AuthConfig.class).newInstance(authConfig);
            }else{
                return this.targetClass.getDeclaredConstructor(AuthConfig.class, AuthStateCache.class).newInstance(authConfig, authStateCache);
            }
        } catch (Exception e) {
            throw new AuthException("未获取到有效的Auth配置");
        }
    }

}
