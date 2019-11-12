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
