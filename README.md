<p align="center">
	<a href="https://justauth.wiki"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/Justauth.png" width="400"></a>
</p>
<p align="center">
	<strong>Login, so easy.</strong>
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/search?q=JustAuth">
		<img src="https://img.shields.io/badge/Maven%20Central-1.16.0-blue" ></img>
	</a>
	<a target="_blank" href="https://gitee.com/yadong.zhang/JustAuth/blob/master/LICENSE">
		<img src="https://img.shields.io/apm/l/vim-mode.svg?color=yellow" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href="https://apidoc.gitee.com/yadong.zhang/JustAuth/" title="API文档">
		<img src="https://img.shields.io/badge/Api%20Docs-1.16.0-orange" ></img>
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

QQ 群：230017570    
微信群：justauth （备注`justauth`或者`ja`）    
帮助文档：[justauth.wiki](https://justauth.wiki)    

## 什么是 JustAuth？

JustAuth，如你所见，它仅仅是一个**第三方授权登录**的**工具类库**，它可以让我们脱离繁琐的第三方登录 SDK，让登录变得**So easy!**

JustAuth 集成了诸如：Github、Gitee、支付宝、新浪微博、微信、Google、Facebook、Twitter、StackOverflow等国内外数十家第三方平台。更多请参考<a href="https://justauth.wiki" target="_blank">已集成的平台</a>

## 有哪些特点？

1. **全**：已集成十多家第三方平台（国内外常用的基本都已包含），仍然还在持续扩展中（[开发计划](https://gitee.com/yadong.zhang/JustAuth/issues/IUGRK)）！
2. **简**：API就是奔着最简单去设计的（见后面`快速开始`），尽量让您用起来没有障碍感！

## 有哪些功能？

- 集成国内外数十家第三方平台，实现快速接入。<a href="https://justauth.wiki/quickstart/how-to-use.html" target="_blank">参考文档</a>
- 自定义 State 缓存，支持各种分布式缓存组件。<a href="https://justauth.wiki/features/customize-the-state-cache.html" target="_blank">参考文档</a>
- 自定义 OAuth 平台，更容易适配自有的 OAuth 服务。<a href="https://justauth.wiki/features/customize-the-oauth.html" target="_blank">参考文档</a>
- 自定义 Http 实现，选择权完全交给开发者，不会单独依赖某一具体实现。<a href="https://justauth.wiki/quickstart/how-to-use.html#%E4%BD%BF%E7%94%A8%E6%96%B9%E5%BC%8F" target="_blank">参考文档</a>
- 自定义 Scope，支持更完善的授权体系。<a href="https://justauth.wiki/features/customize-scopes.html" target="_blank">参考文档</a>
- 更多...<a href="https://justauth.wiki" target="_blank">参考文档</a>

## 快速开始

- 引入依赖
```xml
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>1.16.0</version>
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

如下**任选一种** HTTP 工具 依赖，_项目内如果已有，请忽略。另外需要特别注意，如果项目中已经引入了低版本的依赖，请先排除低版本以后来，引入高版本或者最新版本的依赖_

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

## 赞助和支持

感谢以下赞助商的支持：

<a href="https://www.duohui.cn?utm_source=justauth" target="_blank"><img src="https://docs.duohui.cn/brand_source/img/std.svg" alt="多会 - 专业活动管理系统" style="height: 54px;" height="54px" /></a>

## JustAuth 的用户
有很多公司、组织和个人把 JustAuth 用于学习、研究、生产环境和商业产品中，包括（但不限于）：
![](docs/users/4ca0177c.png)


怎么没有我？[加入]()

## 开源推荐
- `spring-boot-demo` 深度学习并实战 spring boot 的项目: [https://github.com/xkcoding/spring-boot-demo](https://github.com/xkcoding/spring-boot-demo)
- `mica` SpringBoot 微服务高效开发工具集: [https://github.com/lets-mica/mica](https://github.com/lets-mica/mica)
- `pig` 微服务认证授权脚手架(架构师必备): [https://gitee.com/log4j/pig](https://gitee.com/log4j/pig)
- `SpringBlade` 完整的线上解决方案（企业开发必备）: [https://gitee.com/smallc/SpringBlade](https://gitee.com/smallc/SpringBlade)
- `MaxKey` 马克思的钥匙，寓意是最大钥匙,是用户单点登录认证系统（Sigle Sign On System）,OAuth 2.0/OpenID Connect、SAML 2.0、JWT、CAS等标准化的开放协议，使用JustAuth集成OAuth第三方认证。: [https://shimingxy.github.io/MaxKey/](https://shimingxy.github.io/MaxKey/)
- `YurunOAuthLogin` PHP 第三方登录授权 SDK：[YurunOAuthLogin](https://gitee.com/yurunsoft/YurunOAuthLogin)

## 鸣谢
- 感谢 JetBrains 提供的免费开源 License：
<img src="https://images.gitee.com/uploads/images/2020/0406/220236_f5275c90_5531506.png" alt="图片引用自lets-mica" style="float:left;">

<a href="https://www.producthunt.com/posts/justauth?utm_source=badge-featured&utm_medium=badge&utm_souce=badge-justauth" target="_blank"><img src="https://api.producthunt.com/widgets/embed-image/v1/featured.svg?post_id=196886&theme=dark" alt="JustAuth - Login, so easy! | Product Hunt Embed" style="width: 250px; height: 54px;" width="250px" height="54px" /></a>

## 其他
- [CONTRIBUTORS](https://justauth.wiki/contributors.html)
- [CHANGELOGS](https://justauth.wiki/update.html)
- [PLAN](https://gitee.com/yadong.zhang/JustAuth/issues/IUGRK)

## 👪 贡献者列表

[![contributors](https://whnb.wang/contributors/yadong.zhang/JustAuth)](https://whnb.wang)

## Stars 趋势

### Gitee

[![Stargazers over time](https://whnb.wang/img/yadong.zhang/JustAuth?e=604800)](https://whnb.wang/yadong.zhang/JustAuth?e=604800)

### Github

[![Stargazers over time](https://starchart.cc/justauth/JustAuth.svg)](https://starchart.cc/justauth/JustAuth)

