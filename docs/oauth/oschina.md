## 1. 申请应用

### 1.1 登录钉钉开发者中心

1. 登录开源中国：[开源中国](https://www.oschina.net/)
2. 点击访问应用管理页面：[应用管理](https://www.oschina.net/openapi/client)


### 1.2 创建第三方授权应用

1. 在开源中国应用管理页面，点击“创建应用”
2. 填写基本信息
![](doc/media/oauth/007acd70.png)
5. 创建后即可看到 应用ID 和 应用私钥。
![](doc/media/oauth/895f76da.png)

记录以下三个信息：`应用ID`、`应用私钥`和`回调地址`，后面我们会用到。


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
AuthRequest authRequest = new AuthOschinaRequest(AuthConfig.builder()
                .clientId("Client ID")
                .clientSecret("Client Secret")
                .redirectUri("应用回调地址")
                .build());
```

### 2.3 生成授权地址

我们可以直接使用以下方式生成第三方平台的授权链接：
```java
String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
```
这个链接我们可以直接后台重定向跳转，也可以返回到前端后，前端控制跳转。前端控制的好处就是，可以将第三方的授权页嵌入到iframe中，适配网站设计。


### 2.4 以上完整代码如下

```java
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthOschinaRequest;
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
        return new AuthOschinaRequest(AuthConfig.builder()
                .clientId("应用ID")
                .clientSecret("应用私钥")
                .redirectUri("回调地址")
                .build());
    }
}
```
授权链接访问成功后会看到以下页面内容：
![](doc/media/oauth/f403ad52.png) 

点击“连接”即可完成百度的 OAuth 登录。

## 3. 授权结果

注：数据已脱敏

```json
{
    "code":2000,
    "data":{
        "avatar":"https://oscimg.oschina.net/oscnet/up-2247e2b7c5c9907f70ba2648f9db112d.jpg!/both/50x50?t=1451008261000",
        "blog":"https://my.oschina.net/yadong0415",
        "email":"yadong.zhang0415@gmail.com",
        "gender":"FEMALE",
        "location":"北京 朝阳",
        "nickname":"HandsomeBoy丶",
        "rawUserInfo":{
            "gender":"female",
            "name":"HandsomeBoy丶",
            "location":"北京 朝阳",
            "id":2564550,
            "avatar":"https://oscimg.oschina.net/oscnet/up-2247e2b7c5c9907f70ba2648f9db112d.jpg!/both/50x50?t=1451008261000",
            "email":"yadong.zhang0415@gmail.com",
            "url":"https://my.oschina.net/yadong0415"
        },
        "source":"OSCHINA",
        "token":{
            "accessToken":"826c0ffaae486e45a657",
            "expireIn":604799,
            "refreshToken":"dc83aa5aaafc786cf",
            "uid":"2aaa0"
        },
        "username":"HandsomeBoy丶",
        "uuid":"2aa0"
    }
}
```

## 3. 推荐

官方推荐使用 [JustAuth-demo](https://github.com/justauth/JustAuth-demo) 示例项目进行测试。

使用步骤：
1. clone： [https://github.com/justauth/JustAuth-demo.git](https://github.com/justauth/JustAuth-demo.git)
2. 将上面申请的应用信息填入到`RestAuthController#getAuthRequest`方法的对应位置中：
![](doc/media/oauth/e1a40945.png)
3. 启动项目，访问 [http://localhost:8443](http://localhost:8443)
4. 选择对应的平台进行授权登录
![](doc/media/oauth/da2bc692.png)
5. 登录完成后，可以访问[http://localhost:8443/users](http://localhost:8443/users)查看已授权的用户
![](doc/media/oauth/dbe6bcae.png)

注：
1. 如果直接使用 JustAuth-demo 项目进行测试，那么在配置测试应用的“回调地址”时要严格按照以下格式配置：`http://localhost:8443/oauth/callback/{平台名}`
2. 平台名参考 `JustAuthPlatformInfo` 枚举类 `names`


