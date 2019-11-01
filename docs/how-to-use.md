# 如何使用

在前面有介绍到，JustAuth的特点之一就是**简**，极简主义，不给使用者造成不必要的障碍。

既然牛皮吹下了， 那么如何才能用JustAuth实现第三方登录呢？

## 使用步骤

使用JustAuth总共分三步（**这三步也适合于JustAuth支持的任何一个平台**）：

1. 申请注册第三方平台的开发者账号
2. 创建第三方平台的应用，获取配置信息(`accessKey`, `secretKey`, `redirectUri`)
3. 使用该工具实现授权登陆

## 使用方式

- 引入依赖
```xml
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>${latest.version}</version>
</dependency>
```
- 调用api
```java
// 创建授权request
AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
        .clientId("clientId")
        .clientSecret("clientSecret")
        .redirectUri("redirectUri")
        .build());
// 生成授权页面
authRequest.authorize("state");
// 授权登录后会返回code（auth_code（仅限支付宝））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的参数
// 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
authRequest.login(callback);
```

## API分解

**JustAuth**的核心就是一个个的`request`，每个平台都对应一个具体的`request`类，所以在使用之前，需要就具体的授权平台创建响应的`request`

```java
// 创建授权request
AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
        .clientId("clientId")
        .clientSecret("clientSecret")
        .redirectUri("redirectUri")
        .build());
```

所有可用的`Request`列表请参考：[已集成的平台](https://docs.justauth.whnb.wang/#/README?id=已集成的平台)

### 获取授权链接

```java
String authorizeUrl = authRequest.authorize("state");
```
获取到`authorizeUrl`后，可以手动实现redirect到`authorizeUrl`上

**伪代码**

```java
/**
 * 
 * @param source 第三方授权平台，以本例为参考，该值为gitee（因为上面声明的AuthGiteeRequest）
 */
@RequestMapping("/render/{source}")
public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
    AuthRequest authRequest = getAuthRequest(source);
    String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
    response.sendRedirect(authorizeUrl);
}
```

注：`state`建议必传！`state`在`OAuth`的流程中的主要作用就是保证请求完整性，防止**CSRF**风险，此处传的`state`将在回调时传回

### 登录(获取用户信息)

```java
AuthResponse response = authRequest.login(callback);
```

授权登录后会返回code（auth_code（仅限支付宝）、authorization_code（仅限华为））、state，1.8.0版本后，用`AuthCallback`类作为回调接口的入参

**伪代码**

```java
/**
 * 
 * @param source 第三方授权平台，以本例为参考，该值为gitee（因为上面声明的AuthGiteeRequest）
 */
@RequestMapping("/callback/{source}")
public Object login(@PathVariable("source") String source, AuthCallback callback) {
    AuthRequest authRequest = getAuthRequest(source);
    AuthResponse response = authRequest.login(callback);
    return response;
}
```

**注：第三方平台中配置的授权回调地址，以本文为例，在创建授权应用时的回调地址应为：`[host]/callback/gitee`**

### 刷新token

注：`refresh`功能，并不是每个平台都支持

```java
AuthResponse response = authRequest.refresh(AuthToken.builder().refreshToken(token).build());
```

**伪代码**

```java
/**
 * 
 * @param source 第三方授权平台，以本例为参考，该值为gitee（因为上面声明的AuthGiteeRequest）
 * @param token  login成功后返回的refreshToken
 */
@RequestMapping("/refresh/{source}")
public Object refreshAuth(@PathVariable("source") String source, String token){
    AuthRequest authRequest = getAuthRequest(source);
    return authRequest.refresh(AuthToken.builder().refreshToken(token).build());
}
```

### 取消授权

注：`revoke`功能，并不是每个平台都支持

```java
AuthResponse response = authRequest.revoke(AuthToken.builder().accessToken(token).build());
```

**伪代码**

```java
/**
 * 
 * @param source 第三方授权平台，以本例为参考，该值为gitee（因为上面声明的AuthGiteeRequest）
 * @param token  login成功后返回的accessToken
 */
@RequestMapping("/revoke/{source}/{token}")
public Object revokeAuth(@PathVariable("source") String source, @PathVariable("token") String token) throws IOException {
    AuthRequest authRequest = getAuthRequest(source);
    return authRequest.revoke(AuthToken.builder().accessToken(token).build());
}
```

## 参考文章

- [实现Gitee授权登录](http://t.cn/ExDKxQs)
- [实现Github授权登录](http://t.cn/EJ0Fxqo)
- [Spring Boot 快速集成第三方登录功能](http://t.cn/AiWWx5kH) : QQ、Github、微信、谷歌、微软、小米、企业微信
- [集成企业微信](https://mp.weixin.qq.com/s?__biz=MzA3NDk3OTIwMg==&mid=2450633170&idx=2&sn=456b70742a86948a193c691f3e47b72e&chksm=8892933fbfe51a29c1da386a2252d4bf91bfbd14e1ac0b99b783763a0d12e2e4b2d7c4369933&token=482455242&lang=zh_CN#rd)

## 配套项目

**配套Demo**：
- [JustAuth-demo](https://github.com/justauth/JustAuth-demo)：普通版springboot项目demo
- [jFinal版](https://github.com/xkcoding/jfinal-justauth-demo): Jfinal集成JustAuth的demo by [xkcoding](https://github.com/xkcoding)
- [ActFramework版](https://github.com/xkcoding/act-justauth-demo): ActFramework 集成 JustAuth 的 demo by [xkcoding](https://github.com/xkcoding)
- [Nutzboot版](https://github.com/EggsBlue/nutzboot-justauth-demo): NutzBoot集成JustAuth的demo  by [蛋蛋](https://github.com/EggsBlue)
- [Blade版](https://github.com/justauth/blade-justauth-demo): Blade集成JustAuth的demo

## springboot starter插件
- [justauth-spring-boot-starter](https://github.com/xkcoding/justauth-spring-boot-starter): Spring Boot 集成 JustAuth 的最佳实践 by [xkcoding](https://github.com/xkcoding)
- [justauth-spring-boot-starter-demo](https://github.com/justauth/justauth-spring-boot-starter-demo): Spring Boot 使用 justauth-spring-boot-starter 快速集成 JustAuth by [xkcoding](https://github.com/xkcoding)
