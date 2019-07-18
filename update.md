### 2019/07/18
1. 合并github上[@pengisgood](https://github.com/pengisgood) 的[pr#19](https://github.com/zhangyd-c/JustAuth/pull/19)，集成人人
2. 合并github上[@pengisgood](https://github.com/pengisgood) 的[pr#20](https://github.com/zhangyd-c/JustAuth/pull/20)，集成Pinterest
2. 合并github上[@pengisgood](https://github.com/pengisgood) 的[pr#21](https://github.com/zhangyd-c/JustAuth/pull/21)，集成StackOverflow

### 2019/07/17
1. 优化代码
2. 集成Teambition登录

### 2019/07/16
1. 重构UrlBuilder类
2. 将CSDN相关的类置为`Deprecated`，后续可能会删除，也可能一直保留。毕竟CSDN的openAPI已经不对外开放了。
3. `BaseAuthRequest` 改名为 `AuthDefaultRequest`
3. `ResponseStatus` 改名为 `AuthResponseStatus` 并且移动到 `me.zhyd.oauth.model`

### 2019/07/15
1. 新增 `AuthState` 类，内置默认的state生成规则和校验规则

### 2019/07/12
1. 合并[Braavos96](https://github.com/Braavos96)提交的[PR#16](https://github.com/zhangyd-c/JustAuth/pull/16)

### 2019/06/28
1. 修复百度登录获取不到token失效时间的问题
2. 增加state参数校验，预防CSRF。**强烈建议启用state**！

### 2019/06/27
1. 修改login方法的参数为AuthCallback，封装回调返回的参数
2. 支持state参数
3. 增加code和state参数校验

### 2019/06/25
qq授权登录时，需要获取`openId`作为`uuid`，在`1.6.1-beta`和`1.7.0`版本中，引入了`unionId`这一属性。获取`unionid`需要单独向qq团队**发送邮件**申请权限，鉴于这一申请权限的步骤比较麻烦（需要填写的内容比较多），所以在`AuthConfig`中增加了一个`unionId`属性，当为**true**时才会获取unionid，当为false时只获取openId。如果你需要该功能， 则在自行申请了相关权限后，将该属性置为true即可。关于unionId的参考链接：[UnionID介绍](http://wiki.connect.qq.com/unionid%E4%BB%8B%E7%BB%8D)

### 2019/06/19
1. 合并[xkcoding](https://github.com/xkcoding)提交的[PR](https://github.com/zhangyd-c/JustAuth/pull/14)，重构了部分代码，jar包由原来的`130+kb`优化到现在的`110+kb`
2. 合并[skqing](https://gitee.com/skqing)提交的[PR](https://gitee.com/yadong.zhang/JustAuth/pulls/3)， 解决抖音登录失败问题 

### 2019/06/18 
1. 解决Issue [#IY2HW](https://gitee.com/yadong.zhang/JustAuth/issues/IY2HW)
2. 解决Issue [#IY2OH](https://gitee.com/yadong.zhang/JustAuth/issues/IY2OH)
3. 解决Issue [#IY2FV](https://gitee.com/yadong.zhang/JustAuth/issues/IY2FV)
4. 修复部分注释、拼写错误
5. 解决Issue [#IY1QR](https://gitee.com/yadong.zhang/JustAuth/issues/IY1QR) 增加对Config属性的校验功能，主要校验redirect uri的合法性
6. 合并[skqing](https://gitee.com/skqing)提交的[PR](https://gitee.com/yadong.zhang/JustAuth/pulls/2)，解决一些BUG

### 2019/06/06
1. 增加今日头条的授权登陆
2. 发布1.6.0-beta版本，今日头条开发者暂时不能认证， 所以无法做测试，等测试通过后，正式发布release版本

### 2019/05/28
1. 增加小米账号和微软的授权登陆
2. 发布1.5.0版本

### 2019/05/26
1. 增加抖音和Linkedin的授权登陆
2. 修改部分图片命名
3. 优化部分代码
4. 修复`AuthSource`中腾讯云开发平台的拼写错误：`TENCEN_CLOUD`->`TENCENT_CLOUD`
5. 修复支付宝登陆时用户名为空的问题


### 2019/05/24
1. 修复一些问题
2. 升级api，在AuthUser中增加`uuid`属性，可以通过`uuid` + `source`唯一确定一个用户，此举解决了用户身份归属的问题。
3. 发布1.3.3版本的jar包到公开仓库（1.3.2忘记发布了，( ╯□╰ )）
4. 重要：经咨询官方客服得知，CSDN的授权开放平台已经下线，如果以前申请过的应用，可以继续使用，但是不再支持申请新的应用。so, 本项目中的CSDN登录只能针对少部分用户使用了

### 2019/05/23
1. 修复QQ登录的问题
2. 发布1.3.1版本的jar包到公开仓库

### 2019/05/21
1. 新增google授权登录
2. 新增facebook授权登录
3. 发布1.3.0版本的jar包到公开仓库

### 2019/05/18 
1. 发布1.1.0版本的jar包到公开仓库（支持qq和微信登录）
2. 支持淘宝登录
3. 修改`AuthUser.java`类中的`accessToken`属性，由原本的~~accessToken (String)~~改为`token (AuthToken)`
4. 修复一些bug
5. 发布1.2.0版本的jar包到公开仓库（支持淘宝登录）

### 2019/05/17 
1. 增加qq和微信的授权登录
2. 修改getAccessToken方法的返回值