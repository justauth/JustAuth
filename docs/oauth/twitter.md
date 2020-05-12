## 1. 申请应用

参考文章：[twitter三方登录的实现](https://my.oschina.net/u/3361217/blog/1438877)，只需关注创建应用部分即可。

copy以下三个信息：`App ID`、`App Key`和`网站回调域`。

> 友情提示：twitter现不支持个人用户创建应用

## 2. 集成JustAuth


### 2.1 引入依赖

```xml
<dependency>
  <groupId>me.zhyd.oauth</groupId>
  <artifactId>JustAuth</artifactId>
  <version>${latest.version}</version>
</dependency>
```

`${latest.version}`表示当前最新的版本，可以在[这儿](https://github.com/justauth/JustAuth/releases)获取最新的版本信息。

### 2.2 创建Request

```java
// 国外平台 目前必须要手动配置代理
System.setProperty("proxyPort", "10080");
System.setProperty("proxyHost", "127.0.0.1");
AuthRequest authRequest = new AuthTwitterRequest(AuthConfig.builder()
                .clientId("App ID")
                .clientSecret("App Key")
                .redirectUri("网站回调域")
                .build());
```

> 特别注意：所有国外平台都无法直接通过java进行访问API，目前[simple-http](https://github.com/xkcoding/simple-http) Release版本，暂不支持添加代理，所以目前需要手动开启代理。

代理开启的方式：
```java
System.setProperty("proxyPort", "10080");
System.setProperty("proxyHost", "127.0.0.1");
```
以上代码可以在声明 `AuthRequest` 时创建，也可以全局执行。

本地如果支持科学上网，就用自己本地的代理端口即可，如果不支持科学上网，可以去网上找一些免费的代理IP进行测试（请自行操作）。

### 2.3 生成授权地址

我们可以直接使用以下方式生成第三方平台的授权链接：
```java
String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
```
这个链接我们可以直接后台重定向跳转，也可以返回到前端后，前端控制跳转。前端控制的好处就是，可以将第三方的授权页嵌入到iframe中，适配网站设计。


### 2.4 以上完整代码如下

```java
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthTwitterRequest;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/oauth")
public class RestAuthController {

    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public Object login(AuthCallback callback) {
        AuthRequest authRequest = getAuthRequest();
        return authRequest.login(callback);
    }

    private AuthRequest getAuthRequest() {
        // 国外平台 目前必须要手动配置代理
        System.setProperty("proxyPort", "10080");
        System.setProperty("proxyHost", "127.0.0.1");
        return new AuthTwitterRequest(AuthConfig.builder()
                .clientId("App ID")
                .clientSecret("App Key")
                .redirectUri("网站回调域")
                .build());
    }
}
```

## 3. 授权结果

暂无