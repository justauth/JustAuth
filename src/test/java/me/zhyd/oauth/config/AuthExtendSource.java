package me.zhyd.oauth.config;

import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.AuthExtendRequest;
import me.zhyd.oauth.request.AuthRequest;

import java.lang.reflect.Constructor;

/**
 * 测试自定义实现{@link AuthSource}接口后的枚举类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.12.0
 */
public enum AuthExtendSource implements AuthSource {

    OTHER {
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

        @Override
        public AuthRequest getAuthRequestInstance(AuthConfig authConfig) {
            return getAuthRequestInstance(authConfig,null);
        }

        @Override
        public AuthRequest getAuthRequestInstance(AuthConfig authConfig, AuthStateCache authStateCache) {
            try {
                AuthRequest request;
                Class<?> clazz = Class.forName(AuthExtendRequest.class.getName());
                Constructor constructor;
                if(authStateCache==null){
                    constructor = clazz.getDeclaredConstructor(AuthConfig.class);
                    constructor.setAccessible(true);
                    request = (AuthRequest) constructor.newInstance(authConfig);
                }else{
                    constructor = clazz.getDeclaredConstructor(AuthConfig.class, AuthStateCache.class);
                    constructor.setAccessible(true);
                    request = (AuthRequest) constructor.newInstance(authConfig, authStateCache);
                }
                return request;
            } catch (Exception e) {
                throw new AuthException("未获取到有效的Auth配置");
            }
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
    }
}
