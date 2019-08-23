在前面有介绍到，JustAuth的特点之一就是**简**，极简主义，不给使用者造成不必要的障碍。

既然牛皮吹下了， 那么如何才能用JustAuth实现第三方登录呢？

使用JustAuth总共分三步（**这三步也适合于任何一个支持的平台**）：

1. 申请注册第三方平台的开发者账号
2. 创建第三方平台的应用，获取配置信息(id, secret, callbackUrl)
3. 使用该工具实现授权登陆


- 引入依赖
```xml
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>1.10.1</version>
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
authRequest.authorize();
// 授权登录后会返回code（auth_code（仅限支付宝））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的参数
// 注：JustAuth默认保存state的时效为3分钟，3分钟内未使用则会自动清除过期的state
authRequest.login(callback);
```