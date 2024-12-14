## 1.16.7

### 2024/12/14

- 新增
  - 添加`微信小程序`登录能力，对接文档：[点击查看](https://justauth.cn/guide/oauth/wechat_mini_program/)。
  - 添加`支付宝证书模式`登录能力（原支持的公钥登录模式依然可用），对接文档：[点击查看](https://justauth.cn/guide/oauth/alipay_cert)。
  - 添加`appleid`社交登录能力，对接文档：[点击查看](https://justauth.cn/guide/oauth/appleid/)。 [Github#192](https://github.com/justauth/JustAuth/pull/192)
  - 添加`QQ小程序`社交登录能力。 [Github#223](https://github.com/justauth/JustAuth/pull/223)
  - 添加`figma`社交登录能力。 [Gitee#41](https://gitee.com/yadong.zhang/JustAuth/pulls/41)
  - 添加新版`企业微信扫码`登录能力，对接文档：[点击查看](https://justauth.cn/guide/oauth/wechat_enterprise_qrcode_v2/)。 [Github Issue#165](https://github.com/justauth/JustAuth/issues/165)
  - 添加新版`钉钉扫码`登录能力，对接文档：[点击查看](https://justauth.cn/guide/oauth/dingtalk_v2/)。 [Gitee Issue#I73FZL](https://gitee.com/yadong.zhang/JustAuth/issues/I73FZL)
  - 添加新版`华为`登录能力，对接文档：[点击查看](https://justauth.cn/guide/oauth/huawei_v3/)，原`AuthHuaweiRequest`会在后面版本被弃用，如有使用，请切换到`AuthHuaweiV3Request`
- 优化
  - 修复文档错误。[Github #222](https://github.com/justauth/JustAuth/pull/222)
  - 更新 Google 端点地址。[Github #198](https://github.com/justauth/JustAuth/pull/198)
  - Amazon PKCE 中的 `code_verifier` 基于 `state` 缓存
  - `AuthRequest`响应时携带泛型，避免二次解析。[Gitee#38](https://gitee.com/yadong.zhang/JustAuth/pulls/38)
  - 优化业务调用方式：`getAccessToken`和`getUserInfo`两个方法从`AuthDefaultRequest`提升至`AuthRequest`中，部分场景下可以减少一次网络请求。[Github Issue#194](https://github.com/justauth/JustAuth/issues/194)
    - ***注意：如果有基于 JustAuth 规范自定义实现的三方平台 Request（[自定义第三方平台的OAuth](https://justauth.cn/features/customize-the-oauth/)），需要注意`getAccessToken`和`getUserInfo`接口的访问级别是否正确！！！***
- 其他
  -  补充单侧，[Gitee#39](https://gitee.com/yadong.zhang/JustAuth/pulls/39)

## 1.16.6

### 2023/12/03
- 优化
  - 微信公众平台支持返回快照标识（快照标识为 true 时，标识当前获取到的微信用户信息都是虚拟的）
  - 企业微信网页登录--获取用户敏感信。 [Github #155](https://github.com/justauth/JustAuth/pull/155)
  - 添加飞书单元测试。 [Github #159](https://github.com/justauth/JustAuth/pull/159)
  - 升级fastjson版本到1.2.83，1.2.83版本之前存在代码执行漏洞风险 ，CVE-2022-25845。[Gitee PR #31](https://gitee.com/yadong.zhang/JustAuth/pulls/31)
- 新增
  - 添加微软中国(世纪华联)第三方登录，新增微软方式登录的redirectUri校验。[Gitee PR #33](https://gitee.com/yadong.zhang/JustAuth/pulls/33)
  - 新增爱发电平台 [Gitee #35](https://gitee.com/yadong.zhang/JustAuth/pulls/35)
  - 微软平台适配 AzureAD（目前改名为 Microsoft Entra ID）登录认证
- Fixed
  - 修复 twitter 平台在 Java11 环境下登录失败的问题。[#174](https://github.com/justauth/JustAuth/issues/174)
  - 修复 Facebook 平台无法登录的问题（facebook 平台 API 进行了升级）
  - 修复微信公众平台 scope 为 snsapi_base 登录报错的问题 [181](https://github.com/justauth/JustAuth/issues/181)

## 1.16.5

### 2021/10/18

- 合并 PR [Github #138](https://github.com/justauth/JustAuth/pull/138)
- 升级alipay-sdk-version的依赖版本
- 修改用户logo的尺寸
- 修复 alipay 登录失败的 BUG [Gitee Issue #I4E4ML](https://gitee.com/yadong.zhang/JustAuth/issues/I4E4ML)


## 1.16.4

### 2021/9/22

- 合并 PR 
  - [Github #134](https://github.com/justauth/JustAuth/pull/134)
  - [Github #133](https://github.com/justauth/JustAuth/pull/133)
  - [Github #132](https://github.com/justauth/JustAuth/pull/132)
  - [Github #131](https://github.com/justauth/JustAuth/pull/131)
- 添加微软中国(世纪华联)第三方登录，新增微软方式登录的redirectUri校验。[Gitee PR #28](https://gitee.com/yadong.zhang/JustAuth/pulls/28)
- 升级第三方的依赖
  -  simple-http > 1.0.5 
  -  lombok > 1.18.20
  -  junit > 4.13.2
  -  fastjson > 1.2.78
  -  alipay-sdk > 4.16.38.ALL

## 1.16.3

### 2021/8/15

- 发布 v1.16.3
- 新增
  - 集成“企业微信的第三方应用”平台登录
- PR
  - `AuthRequst` 增加 `Builder` 构建方式，使用起来更简单。 ([#27](https://gitee.com/yadong.zhang/JustAuth/pulls/27))
  - 使用 Github Action 添加发布快照的 workflow。 ([#126](https://github.com/justauth/JustAuth/pull/126))
  - 新增了企业微信的第三方应用登录，`AuthWeChatEnterpriseThirdQrcodeRequest`。 ([#127](https://github.com/justauth/JustAuth/pull/127))
  - 添加快照版本对应更详细的文档。 ([#128](https://github.com/justauth/JustAuth/pull/128))
- 修改
  - 在 Gitee PR ([#27](https://gitee.com/yadong.zhang/JustAuth/pulls/27)) 的基础上重构代码，增加 Builder 方式创建 AuthRequest
  - 解决 Line 登录的错误。[#122](https://github.com/justauth/JustAuth/issues/122)
  

## 1.16.2

### 2021/7/28

- 发布 v1.16.2
- 新增
  - 集成“程序员客栈”平台登录
- 修改
  - 更新文档
  - 修复“淘宝”平台授权登录后没有`uid`的问题、增加刷新token的功能
  - 修复“Twitter”平台授权登录后获取不到用户邮箱的问题

## 1.16.1

### 2021/4/19

- 发布 v1.16.1
- Fix Github issue [#114](https://github.com/justauth/JustAuth/issues/114): 解决企业微信授权后,回调地址中原有的参数丢失的问题
- Fix Github issue [#82](https://github.com/justauth/JustAuth/issues/82): 抖音平台支持自定义 scope
- Fix Github issue [#92](https://github.com/justauth/JustAuth/issues/92): 增加忽略校验 redirectUri 的配置
- Merge Github PR [#115](https://github.com/justauth/JustAuth/pull/115)
- 升级 `fastjson` 到 `v1.2.76`
  
## 1.16.0

### 2021/4/7

- 发布 v1.16.0
- 新增
  - 集成 Amazon 平台登录
  - 集成 Slack 平台登录
  - 集成 LINE 平台登录
  - 集成 Okta 平台登录
  - 集成钉钉账号登录
- 修改 
  - 【**重要**】 `AuthConfig`中的`codingGroupName`参数更名为`domainPrefix`，针对此类平台提供通用的配置。
  - 修改 `AuthFacebookScope` 中的默认 scope，解决 justauth-demo 项目中使用 facebook 报错的问题
  - 升级 facebook 的 api 到 v10.0 版本
  - 优化部分代码
  - 优化 Map 声明时的初始容量，避免频繁扩容
  - 更新 README 文档
- PR
  - 合并 [Github #110](https://github.com/justauth/JustAuth/pull/110) 
  - 合并 [Gitee #22](https://gitee.com/yadong.zhang/JustAuth/pulls/22) 

## 1.15.9

### 2021/1/1

- 发布 v1.15.9
- 新增
    - 修复并正式启用 飞书 平台的第三方登录
    - AuthToken 类中新增 `refreshTokenExpireIn` 记录 refresh token 的有效期
- PR
    - 合并 [Github #101](https://github.com/justauth/JustAuth/pull/101) ：支持喜马拉雅登录
    - 合并 [Github #105](https://github.com/justauth/JustAuth/pull/105) ：支持企业微信网页授权登录
    - 合并 [Github #107](https://github.com/justauth/JustAuth/pull/107) ：添加AuthAlipayRequest网络代理构造器，解决 Github Issue [#102](https://github.com/justauth/JustAuth/issues/102)
- 修改
    - 修改喜马拉雅配置参数，将`ClientOsType`参数提到 AuthConfig 中
    - AuthChecker 中增加对喜马拉雅平台的校验
    - 升级 facebook api 版本到 v9.0，解决 Gitee Issue [#I2AR5S](https://gitee.com/yadong.zhang/JustAuth/issues/I2AR5S)
    - ！！！**注意**！！！修改原来的企业微信 Request 类名为 `AuthWeChatEnterpriseQrcodeRequest`，升级后注意该点

注意：可能有些开发者对于 JA 集成的四个微信平台不太理解，这儿统一说明：
- 按照类名
    - AuthWeChatEnterpriseQrcodeRequest：企业微信二维码登录
    - AuthWeChatEnterpriseWebRequest：企业微信网页登录
    - AuthWeChatOpenRequest：微信开放平台
    - AuthWeChatMpRequest：微信公众平台
- 按照枚举
    - WECHAT_ENTERPRISE：企业微信二维码登录
    - WECHAT_ENTERPRISE_WEB：企业微信网页登录
    - WECHAT_OPEN：微信开放平台
    - WECHAT_MP：微信公众平台
    
## 1.15.8

### 2020/10/25

- Release version 1.15.8
- Merge the pr. [#95](https://github.com/justauth/JustAuth/pull/95) [#96](https://github.com/justauth/JustAuth/pull/96)

## 1.15.7

### 2020/09/11

- Release version 1.15.7
- Upgrade the use of access token when obtaining resources on the github platform.Reference from [Deprecating API authentication through query parameters](https://developer.github.com/changes/2020-02-10-deprecating-auth-through-query-param/)
- Fixed issue. [#89](https://github.com/justauth/JustAuth/issues/89)

## 1.15.7-beta.3

### 2020/08/24

- Fixing a bug for wechat.
- Improve the Microsoft platform's questions about scope.

## 1.15.7-beta.1

### 2020/08/05

- 新增
    - 以下平台支持自定义 Scope 参数：百度、coding、Facebook、gitee、github、gitlab、google、华为、京东·宙斯、酷家乐、领英、微软、小米、Pinterest、QQ、人人网、StackOverflow、微博、微信公众平台
    - 添加 PR 和 ISSUE 规范和 CODE_OF_CONDUCT 文档
- 合并
    - 合并 Gitee [PR#19](https://gitee.com/yadong.zhang/JustAuth/pulls/19)，修复通过google登录一次后，重新用google登录无法切换谷歌账户的问题。

## 1.15.6

### 2020/06/30
- 文档
    - 新增 [百度登录](oauth/baidu.md)文档
    - 新增 [钉钉登录](oauth/alipay.md)文档
    - 新增 [开源中国登录](contributors.md)文档
    - 新增 [领英登录](references.md)文档
    - 新增 [Google 登录](references.md)文档
    - 新增 [微信企业版登录](oauth/wechatEnterprise.md)文档
    - 新增 [Facebook 登录](oauth/facebook.md)文档
    - 完善 [JustAuth 使用者](users.md)文档
    - 替换“帮助文档”域名，由[https://docs.justauth.whnb.wang](https://docs.justauth.whnb.wang)迁移到[https://www.justauth.cn](https://www.justauth.cn)
- 新增
    - 增加阿里云授权登录中刷新授权token的接口，by “QQ群用户需求”
    - AuthConfig 增加忽略校验 state 的参数，详情参考：[Github#Issue#83](https://github.com/justauth/JustAuth/issues/83)
    - 移除领英刷新token的接口。参考官网：`To refresh an access token, go through the authorization process again to fetch a new token.`

## 1.15.5
### 2020/06/24
- BUG
    - 解决 `Microsoft` 授权失败的 BUG
    - 解决 `Coding` 个人账号授权失败的 BUG（目前只能使用团队模式进行授权，需要传入团队名，参考`AuthConfig#codingGroupName`）
    - 解决 `AuthLinkedinRequest#getAvatar` NPE 的问题。（领英用户没有头像时，原代码会报 NPE）
    - 解决抖音登录获取用户地址异常的问题。
    - 解决人人网登录时 token 中带有 `|` 引起的异常，[Gitee!15](https://gitee.com/yadong.zhang/JustAuth/pulls/15)
- 新增
    - 支持阿里云授权登录，[Github#81](https://github.com/justauth/JustAuth/pull/81)
    - AuthUser 中新增 `rawUserInfo`，用来存放第三方平台返回的原始用户数据。注：淘宝平台的`rawUserInfo`为一个空 JSON
    - 支持 Http 级的代理配置，使用方式：
```java
new AuthGoogleRequest(AuthConfig.builder()
    .clientId("")
    .clientSecret("")
    .redirectUri("http://127.0.0.1:8443/oauth/callback/google")
    // 针对国外平台配置代理
    .httpConfig(HttpConfig.builder()
            .timeout(15000)
            .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
            .build())
    .build());
```
- 修改
    - 升级 [simple-http](https://github.com/xkcoding/simple-http) 到 `v1.0.2`
    - 升级 `fastjson` 到 `v1.2.71`
- 删除
    - :boom: 删除**腾讯云登录**。coding 已并入 腾讯云，因此只保留 coding 登录 :boom:
- 文档
    - 新增 [Coding登录](oauth/coding.md)文档
    - 完善 [支付宝登录](oauth/alipay.md)文档
    - 完善 [贡献者名单](contributors.md)文档
    - 完善 [参考文档](references.md)文档
- PR
    - 合并 [Gitee!17](https://gitee.com/yadong.zhang/JustAuth/pulls/17)
    - 合并 [Gitee!15](https://gitee.com/yadong.zhang/JustAuth/pulls/15)
    - 合并 [Github#81](https://github.com/justauth/JustAuth/pull/81)
- Issues
    - 解决 [Github#80](https://github.com/justauth/JustAuth/issues/80)
    - 解决 [Github#75](https://github.com/justauth/JustAuth/issues/75)
    
## 1.15.4-alpha
### 2020/05/13
- 修复
    - 解决 Twitter 授权失败的BUG
- 文档
    - 完善 [https://www.justauth.cn](https://www.justauth.cn/) 的404引导页内容
    - 增加名词解释： `uuid`
    - 补充 [Q&A](Q&A.md)
    - 新增 [参考文档](references.md)，包含 OAuth 授权和第三方平台的API文档等内容
    - 新增 [推特登录](oauth/twitter.md) 的说明文档

> 特别注意：所有国外平台都无法直接通过java进行访问API，目前[simple-http](https://github.com/xkcoding/simple-http) Release版本，暂不支持添加代理，所以目前需要手动开启代理。

代理开启的方式：
```java
System.setProperty("proxyPort", "10080");
System.setProperty("proxyHost", "127.0.0.1");
```
以上代码可以在声明 `AuthRequest` 时创建，也可以全局执行。

本地如果支持科学上网，就用自己本地的代理端口即可，如果不支持科学上网，可以去网上找一些免费的代理IP进行测试（请自行操作）。


## 1.15.2-alpha
### 2020/05/10
- 修改
    - 修复使用领英登录时无法获取token的问题
    - 解决Gitee [Issue-I1GPIB](https://gitee.com/yadong.zhang/JustAuth/issues/I1GPIB)

【声明】：当引用 OkHttp 时，无法调用领英的授权登录，可能会抛出 400 异常。如遇此问题，请先切换到 hutool 或者 httpclient 依赖。
该问题尚在修复中，给各位带来的不便，深表歉意。

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

## v1.15.1(~~v1.15.0~~)
### 2020/04/10
- 修改
    - 更新开发文档
    - 合并Gitee [PR-10](https://gitee.com/yadong.zhang/JustAuth/pulls/10)，集成京东登录
    - 合并Gitee [PR-14](https://gitee.com/yadong.zhang/JustAuth/pulls/14)，修改帮助文档，加入JA的使用者信息
    - 合并Github [PR-69](https://github.com/justauth/JustAuth/pull/69)，升级fastjson的最新版本
    - 解决使用apache-httpclient时的403bug，升级simple-http，感谢QQ群的`不瘦十斤不改名字`反馈该问题
    - 修复其他一些问题

【声明】：由于本人的失误，发布了一个错误的版本（1.15.0），目前1.15.0已发布，但是请不要使用，请直接升级到`1.15.1`。

给各位造成的不便，深表歉意。
    
## v1.14.0
### 2020/03/17
- 修改
    - 合并[PR-59](https://github.com/justauth/JustAuth/pull/59)，抽取HTTP，具体实现交给开发者，解耦 hutool-http，开发者可以视自己项目的依赖决定使用何种HTTP方式。详情请参考：https://github.com/xkcoding/simple-http
    - 合并[PR-65](https://github.com/justauth/JustAuth/pull/65)，修改错误文案
    - 修复其他一些问题
    
## v1.14.0
### 2020/03/17
- 修改
    - 合并[PR-59](https://github.com/justauth/JustAuth/pull/59)，抽取HTTP，具体实现交给开发者，解耦 hutool-http，开发者可以视自己项目的依赖决定使用何种HTTP方式。详情请参考：https://github.com/xkcoding/simple-http
    - 合并[PR-65](https://github.com/justauth/JustAuth/pull/65)，修改错误文案
    - 修复其他一些问题

## v1.13.2
### 2019/12/24
- 新增
    - 增加微信、QQ、支付宝、微博授权登录的帮助文档
    - 合并[PR#57](https://github.com/justauth/JustAuth/pull/57)，增加微信公众号登录 by [@xkcoding](https://github.com/xkcoding)
    - [帮助文档](https://www.justauth.cn)中增加自定义的404页面
    - [帮助文档](https://www.justauth.cn)中增加Gittalk插件
    - [帮助文档](https://www.justauth.cn)中增加Java代码高亮的插件
    - 增加`AuthUserGender#getWechatRealGender`方法，兼容获取微信平台的用户性别
- 修改
    - 修复抖音登录取值取错层级的问题（[issue#I15SIG@Gitee](https://gitee.com/yadong.zhang/JustAuth/issues/I15SIG)）
    - 完善异常提示的逻辑，支持传入Source（平台），发生异常时显示对应的source（平台）
    - `checkState`方法从`AuthDefaultRequest`中提出到`AuthChecker`中
    - `AuthResponseStatus`枚举类中增加`ILLEGAL_STATUS`、`REQUIRED_REFRESH_TOKEN`两个枚举值
    - `AuthSource`接口中增加`getName`方法，用来对外提供实际`source`的字符串值
    - `AuthWeiboRequest`微博授权登录中实现`revoke`方法，支持手动回收授权
    - [帮助文档](https://www.justauth.cn)中修复[腾讯云登录]链接错误的问题
- 升级
    - 升级相关依赖：lombok@v1.18.10，hutool@5.0.5，fastjson@1.2.62，alipay@4.8.10.ALL（[PR#11@Gitee](https://gitee.com/yadong.zhang/JustAuth/pulls/11)）


## v1.13.1
### 2019/11/12

- 修复[Issue#52](https://github.com/justauth/JustAuth/issues/52)，解决AuthCallback异常的问题。请正在使用 `v1.13.0`的朋友升级到`v1.13.1`

## v1.13.0
### 2019/11/01

- 集成“推特”
- 完善文档

## v1.12.0
### 2019/09/06

- 集成“美团”授权登录
- 集成“饿了么”授权登录
- 升级Fastjson依赖到1.2.60，预防[“Fastjson ＜ 1.2.60 远程拒绝服务漏洞预警”](https://card.weibo.com/article/m/show/id/2309404413257925394542)
- 添加Nutzboot版的demo
- 提取公共的Source接口，支持自定义扩展第三方平台的授权登录，具体扩展例子可参考[AuthExtendRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/test/java/me/zhyd/oauth/request/AuthExtendRequest.java)

## v1.11.0
### 2019/09/03

- 集成“Gitlab”授权登录

### 2019/09/02

- 集成“酷家乐”授权登录

## v1.10.1
### 2019/08/17

- AuthUser添加构造函数，支持反序列化

### 2019/08/08

- 项目迁移到组织[justauth](https://github.com/justauth)

## v1.10.0
### 2019/08/06

- 合并[PR-34](https://github.com/zhangyd-c/JustAuth/pull/34)，添加StringUtil单元测试，修复bug
- 合并[PR-35](https://github.com/zhangyd-c/JustAuth/pull/35)，集成企业微信

### 2019/08/05

- 集成华为登录
- 修改`AuthChecker#checkCode`方法，对于不同平台使用不同参数接受code的情况统一做处理

### 2019/08/03

合并github上[xkcoding](https://github.com/xkcoding) 的[pr#32](https://github.com/zhangyd-c/JustAuth/pull/32)，抽取 cache 接口，方便用户自行集成 cache

### 2019/08/02

- 增加`AuthCache`配置类`AuthCacheConfig.java`，可以自定义缓存有效期以及是否开启定时任务
- 去掉`slf4j`依赖，封装`Log.java`工具类
- 规范测试类

## v1.9.5
### 2019/07/31 

`v1.9.4`版本发布失败，请升级到`1.9.5`版本！

由此给您带来的不便，敬请谅解！

## v1.9.4
### 2019/07/30 

1. 升级`hutool-http`版本到`v4.6.1`
2. 去除`AuthCallback`中增加的默认的校验state的方法，挪到`AuthDefaultRequest`中做统一处理
3. `alipay-sdk-java`依赖改为`provided`，如果需要使用支付宝登录，需要使用方手动引入相关依赖，具体操作方式，见项目WIKI；
4. 规范注释

## v1.9.3
### 2019/07/30 

1. 规范注释
2. 增加State缓存，`AuthCallback`中增加默认的校验state的方法
3. 增加默认的state生成方法，参考`AuthStateUtils.java`和`UuidUtils.java`
4. 升级`hutool-http`版本到`v4.6.0`
5. 修复其他一些问题

### 2019/07/27

1. `IpUtils.getIp`改名为`IpUtils.getLocalIp`
2. 规范注释

### 2019/07/25

1. `AuthConfig`类中去掉state参数
2. 删除`AuthState`类
3. 增加`authorize(String)`方法，并且使用`@Deprecated`标记`authorize()`方法

## v1.9.2
### 2019/07/22  
1. 合并github上[xkcoding](https://github.com/xkcoding) 的[pr#26](https://github.com/zhangyd-c/JustAuth/pull/26)，AuthConfig类添加lombok注解，方便 [justauth-spring-boot-starter](https://github.com/xkcoding/justauth-spring-boot-starter) 直接使用 

## v1.9.1
### 2019/07/22  
1. 增加`stackoverflow`参数校验
2. 解决`Pinterest`获取用户失败的问题
3. 添加注释

## v1.9.0
### 2019/07/19  

1. 合并github上[@dyc12ii](https://github.com/dyc12ii) 的[pr#25](https://github.com/zhangyd-c/JustAuth/pull/25)，升级fastjson版本至1.2.58,避免安全漏洞
2. `AuthUserGender`枚举类挪到`enums`包下
3. 删除`AuthBaiduErrorCode`和`AuthDingTalkErrorCode`枚举类
4. 优化百度授权流程，增加refresh token的方法
5. 优化`AuthConfig`、`AuthResponse`类，去掉不必要的lombonk注解，减少编译后的代码量
6. 使用lombok注解优化枚举类
7. `AuthQqRequest`增加refresh方法
8. 修复google登录无法获取用户信息的问题
9. 优化代码

### 2019/07/18

1. 合并github上[@pengisgood](https://github.com/pengisgood) 的[pr#19](https://github.com/zhangyd-c/JustAuth/pull/19)，集成人人
2. 合并github上[@pengisgood](https://github.com/pengisgood) 的[pr#20](https://github.com/zhangyd-c/JustAuth/pull/20)，集成Pinterest
3. 合并github上[@pengisgood](https://github.com/pengisgood) 的[pr#21](https://github.com/zhangyd-c/JustAuth/pull/21)，集成StackOverflow
4. 合并github上[@xkcoding](https://github.com/xkcoding) 的[pr#23](https://github.com/zhangyd-c/JustAuth/pull/23)，重构代码、新增编辑器规范，规范PR代码风格

### 2019/07/17
1. 优化代码
2. 集成Teambition登录

### 2019/07/16
1. 重构UrlBuilder类
2. 将CSDN相关的类置为`Deprecated`，后续可能会删除，也可能一直保留。毕竟CSDN的openAPI已经不对外开放了。
3. `BaseAuthRequest` 改名为 `AuthDefaultRequest`
4. `ResponseStatus` 改名为 `AuthResponseStatus` 并且移动到 `me.zhyd.oauth.model`
5. 合并github上[@xkcoding](https://github.com/xkcoding) 的[pr#18](https://github.com/zhangyd-c/JustAuth/pull/18)，修复小米回调错误问题 同时 支持微信获取unionId 

## v1.8.1
### 2019/07/15 
1. 新增 `AuthState` 类，内置默认的state生成规则和校验规则

### 2019/07/12
1. 合并[Braavos96](https://github.com/Braavos96)提交的[PR#16](https://github.com/zhangyd-c/JustAuth/pull/16)

## v1.8.0
### 2019/06/28 
1. 修复百度登录获取不到token失效时间的问题
2. 增加state参数校验，预防CSRF。**强烈建议启用state**！

### 2019/06/27
1. 修复百度登录获取不到token失效时间的问题
2. 增加state参数校验，预防CSRF。**强烈建议启用state**！
3. 修改login方法的参数为AuthCallback，封装回调返回的参数
4. 支持state参数
5. 增加code和state参数校验

由于state安全问题，1.8.0以前的版本都有隐藏的CSRF漏洞问题，所以强烈建议正在使用JustAuth的朋友升级到1.8.0版本！

## v1.7.1
### 2019/06/25  
qq授权登录时，需要获取`openId`作为`uuid`，在`1.6.1-beta`和`1.7.0`版本中，引入了`unionId`这一属性。获取`unionid`需要单独向qq团队**发送邮件**申请权限，鉴于这一申请权限的步骤比较麻烦（需要填写的内容比较多），所以在`AuthConfig`中增加了一个`unionId`属性，当为**true**时才会获取unionid，当为false时只获取openId。如果你需要该功能， 则在自行申请了相关权限后，将该属性置为true即可。关于unionId的参考链接：[UnionID介绍](http://wiki.connect.qq.com/unionid%E4%BB%8B%E7%BB%8D)

## v1.7.0
### 2019/06/19  
1. 合并[xkcoding](https://github.com/xkcoding)提交的[PR](https://github.com/zhangyd-c/JustAuth/pull/14)，重构了部分代码，jar包由原来的`130+kb`优化到现在的`110+kb`
2. 合并[skqing](https://gitee.com/skqing)提交的[PR](https://gitee.com/yadong.zhang/JustAuth/pulls/3)， 解决抖音登录失败问题 

## v1.6.1-beta
### 2019/06/18  
1. 解决Issue [#IY2HW](https://gitee.com/yadong.zhang/JustAuth/issues/IY2HW)
2. 解决Issue [#IY2OH](https://gitee.com/yadong.zhang/JustAuth/issues/IY2OH)
3. 解决Issue [#IY2FV](https://gitee.com/yadong.zhang/JustAuth/issues/IY2FV)
4. 修复部分注释、拼写错误
5. 解决Issue [#IY1QR](https://gitee.com/yadong.zhang/JustAuth/issues/IY1QR) 增加对Config属性的校验功能，主要校验redirect uri的合法性
6. 合并[skqing](https://gitee.com/skqing)提交的[PR](https://gitee.com/yadong.zhang/JustAuth/pulls/2)，解决一些BUG

## v1.6.0-beta
### 2019/06/06  
1. 增加今日头条的授权登陆
2. 发布1.6.0-beta版本，今日头条开发者暂时不能认证， 所以无法做测试，等测试通过后，正式发布release版本

## v1.5.0
### 2019/05/28  
1. 增加小米账号和微软的授权登陆
2. 发布1.5.0版本

## v1.4.0
### 2019/05/26 
1. 增加抖音和Linkedin的授权登陆
2. 修改部分图片命名
3. 优化部分代码
4. 修复`AuthSource`中腾讯云开发平台的拼写错误：`TENCEN_CLOUD`->`TENCENT_CLOUD`
5. 修复支付宝登陆时用户名为空的问题

## v1.3.3
### 2019/05/24  
1. 修复一些问题
2. 升级api，在AuthUser中增加`uuid`属性，可以通过`uuid` + `source`唯一确定一个用户，此举解决了用户身份归属的问题。
3. 发布1.3.3版本的jar包到公开仓库（1.3.2忘记发布了，( ╯□╰ )）
4. 重要：经咨询官方客服得知，CSDN的授权开放平台已经下线，如果以前申请过的应用，可以继续使用，但是不再支持申请新的应用。so, 本项目中的CSDN登录只能针对少部分用户使用了

## v1.3.1
### 2019/05/23  
1. 修复QQ登录的问题
2. 发布1.3.1版本的jar包到公开仓库

## v1.3.0
### 2019/05/21  
1. 新增google授权登录
2. 新增facebook授权登录
3. 发布1.3.0版本的jar包到公开仓库

## v1.1.0
### 2019/05/18  
1. 发布1.1.0版本的jar包到公开仓库（支持qq和微信登录）
2. 支持淘宝登录
3. 修改`AuthUser.java`类中的`accessToken`属性，由原本的~~accessToken (String)~~改为`token (AuthToken)`
4. 修复一些bug
5. 发布1.2.0版本的jar包到公开仓库（支持淘宝登录）

### 2019/05/17 
1. 增加qq和微信的授权登录
2. 修改getAccessToken方法的返回值

## v1.0.1
### 2019/03/27  
集成 支付宝授权登录

## v1.0.0
### 2019/03/25  
史上最全的整合第三方登录的工具,目前已支持Github、Gitee、微博、钉钉和百度、Coding、腾讯云开发者平台和OSChina登录。 Login, so easy!
