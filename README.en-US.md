<p align="center">
	<a href="https://justauth.wiki"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/Justauth.png" width="400"></a>
</p>
<p align="center">
	<strong>Login, so easy.</strong>
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/search?q=JustAuth">
		<img src="https://img.shields.io/badge/Maven%20Central-1.16.1-blue" ></img>
	</a>
	<a target="_blank" href="https://gitee.com/yadong.zhang/JustAuth/blob/master/LICENSE">
		<img src="https://img.shields.io/apm/l/vim-mode.svg?color=yellow" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href="https://apidoc.gitee.com/yadong.zhang/JustAuth/" title="API文档">
		<img src="https://img.shields.io/badge/Api%20Docs-1.16.1-orange" ></img>
	</a>
	<a target="_blank" href="https://justauth.wiki" title="参考文档">
		<img src="https://img.shields.io/badge/Docs-latest-blueviolet.svg" ></img>
	</a>
	<a href="https://codecov.io/gh/justauth/JustAuth">
		<img src="https://codecov.io/gh/justauth/JustAuth/branch/master/graph/badge.svg?token=zYiAqd9aFz" />
	</a>
	<a href='https://gitee.com/yadong.zhang/JustAuth/stargazers'>
	  <img src='https://gitee.com/yadong.zhang/JustAuth/badge/star.svg?theme=gvp' alt='star'></img>
	</a>
	<a target="_blank" href='https://github.com/zhangyd-c/JustAuth'>
		<img src="https://img.shields.io/github/stars/zhangyd-c/JustAuth.svg?style=social" alt="github star"></img>
	</a>
</p>

-------------------------------------------------------------------------------
<p align="center">
<img src='./docs/media/75a3c076.png' alt='star'></img>
</p>

-------------------------------------------------------------------------------



`JustAuth`, as you see, It is just a Java library of third-party authorized login, It's smaller and easier to use. JustAuth is the best third-party login tool written in JAVA.

Source Code：[gitee](https://gitee.com/yadong.zhang/JustAuth) | [github](https://github.com/zhangyd-c/JustAuth)    
Docs：[Reference Doc](https://justauth.wiki)

## Features

1. **Multiple platform**: Has integrated more than a dozen third-party platforms.([plan](https://gitee.com/yadong.zhang/JustAuth/issues/IUGRK))
2. **Minimalist**: The minimalist design is very simple to use.

## Quick start

- Add maven dependency

These artifacts are available from Maven Central:
```xml
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>1.16.1</version>
</dependency>
```
- Using JustAuth
```java
// Create authorization request
AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
        .clientId("clientId")
        .clientSecret("clientSecret")
        .redirectUri("redirectUri")
        .build());
// Generate authorization url
authRequest.authorize("state");
// After authorization to login, it will return: code(auth_code(Alipay only)),state, After version 1.8.0, you can use the AuthCallback as a parameter to the callback interface
// Note: JustAuth saves state for 3 minutes by default. If it is not used within 3 minutes, the expired state will be cleared automatically.
authRequest.login(callback);
```

Note, that since [v1.14.0](https://gitee.com/yadong.zhang/JustAuth/releases/v1.14.0) JustAuth has been integrated by default with [simple-http](https://github.com/xkcoding/simple-http) as the HTTP general interface (see the update [JustAuth 1.14.0 release! Perfect decoupling of HTTP tools](https://mp.weixin.qq.com/s?__biz=MzA3NDk3OTIwMg==&mid=2450633197&idx=1&sn=11e625b307db62b2f1c4e82f7744b2a2&chksm=88929300bfe51a16562b45592a264482ae2c74c6dbfa4a3aa9611ad4fea4a9be5b1f0545527d&token=1093833287&lang=zh_CN#rd)). Since most projects already integrate HTTP tools such as OkHttp3, apache HttpClient, and hutool-http), in order to reduce unnecessary dependencies,Starting from [v1.14.0](https://gitee.com/yadong.zhang/JustAuth/releases/v1.14.0), JustAuth will not integrate hutool-http by default. If the developer's project is new or there is no integrated HTTP implementation tool in the project, please add the corresponding HTTP implementation class by yourself. Alternative dependencies are as follows:


- hutool-http

  ```xml
  <dependency>
      <groupId>cn.hutool</groupId>
      <artifactId>hutool-http</artifactId>
      <version>5.2.5</version>
  </dependency>
  ```

- httpclient

  ```xml
  <dependency>
  	<groupId>org.apache.httpcomponents</groupId>
    	<artifactId>httpclient</artifactId>
    	<version>4.5.12</version>
  </dependency>
  ```

- okhttp

  ```xml
  <dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>4.4.1</version>
  </dependency>
  ```

## Contributions

1. Fork this project to your repository
2. Clone the project after fork.
3. Modify the code (either to fix issue, or to add new features)
4. Commit and push code to a remote repository
5. Create a new PR (pull request), and select `dev` branch
6. Waiting for author to merge

I look forward to your joining us.


## Contributors

[contributors](https://justauth.wiki/contributors.html)

## Change Logs

[CHANGELOGS](https://justauth.wiki/update.html)

## Recommend

- `spring-boot-demo` In-depth study and actual combat of spring boot projects: [https://github.com/xkcoding/spring-boot-demo](https://github.com/xkcoding/spring-boot-demo)
- `mica` Efficient Development of scaffolding by Spring Cloud: [https://github.com/lets-mica/mica](https://github.com/lets-mica/mica)
- `pig` Cosmic strongest Micro Services Certified authorized scaffolding (essential for Architects): [https://gitee.com/log4j/pig](https://gitee.com/log4j/pig)
- `SpringBlade` Complete online solution (necessary for enterprise development): https://gitee.com/smallc/SpringBlade

## References

- [The OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749)
- [OAuth 2.0](https://oauth.net/2/)
