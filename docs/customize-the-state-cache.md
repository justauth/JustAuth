# 自定义state缓存
> starter 内置了2种缓存实现，一种是上一节演示的默认实现，另一种是用户自定义的扩展实现。
本节将会使用[JustAuth-demo](https://github.com/justauth/JustAuth-demo)Demo进行演示扩展Redis缓存的方式，当然了，你也可以自定义实现你自己的缓存。

## 添加 Redis 依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### 添加redis配置

```xml
# 集成redis实现自定义的state缓存
spring.redis.database=0
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=xxxx
```
### 实现state缓存接口

```java
package me.zhyd.justauth;

import me.zhyd.oauth.cache.AuthCacheConfig;
import me.zhyd.oauth.cache.AuthStateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 扩展Redis版的state缓存
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2019/10/24 13:38
 * @since 1.8
 */
@Component
public class AuthStateRedisCache implements AuthStateCache {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    /**
     * 存入缓存，默认3分钟
     *
     * @param key   缓存key
     * @param value 缓存内容
     */
    @Override
    public void cache(String key, String value) {
        valueOperations.set(key, value, AuthCacheConfig.timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 存入缓存
     *
     * @param key     缓存key
     * @param value   缓存内容
     * @param timeout 指定缓存过期时间（毫秒）
     */
    @Override
    public void cache(String key, String value, long timeout) {
        valueOperations.set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取缓存内容
     *
     * @param key 缓存key
     * @return 缓存内容
     */
    @Override
    public String get(String key) {
        return valueOperations.get(key);
    }

    /**
     * 是否存在key，如果对应key的value值已过期，也返回false
     *
     * @param key 缓存key
     * @return true：存在key，并且value没过期；false：key不存在或者已过期
     */
    @Override
    public boolean containsKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
```

### 获取Request

本节以Gitee为例

```java
// 1. 注入新添加的cache
@Autowired
private AuthStateRedisCache stateRedisCache;

// 2. 创建request时传入stateRedisCache
AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
      .clientId("clientId")
      .clientSecret("clientSecret")
      .redirectUri("redirectUri")
      .build(), stateRedisCache);// 此处传入自定义实现的类
```


到此已经完成了通过redis**扩展实现state缓存**的功能。当然，只要你愿意，你可以使用**任何一种**缓存技术去实现这个功能，并不局限于redis。
