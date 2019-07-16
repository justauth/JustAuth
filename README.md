<p align="center">
	<a href="https://www.justauth.cn/"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/logo.png" width="400"></a>
</p>
<p align="center">
	<strong>Login, so easy.</strong>
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/search?q=JustAuth">
		<img src="https://img.shields.io/badge/Maven Central-1.8.1-blue.svg" ></img>
	</a>
	<a target="_blank" href="https://gitee.com/yadong.zhang/JustAuth/blob/master/LICENSE">
		<img src="https://img.shields.io/apm/l/vim-mode.svg?color=yellow" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href="https://apidoc.gitee.com/yadong.zhang/JustAuth/">
		<img src="https://img.shields.io/badge/Docs-1.8.1-orange.svg" ></img>
	</a>
</p>

<center>
    <table>
        <tr>
            <td align="center" width="200"><a href="#授权gitee"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/gitee.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权github"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/github.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权weibo"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/weibo.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权钉钉"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/dingtalk.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权百度"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/baidu.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权coding"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/coding.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权腾讯云开发者平台"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/tencentCloud.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权oschina"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/oschina.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权支付宝"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/alipay.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权qq"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/qq.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权微信"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/wechat.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权淘宝"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/taobao.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权google"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/google.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权facebook"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/facebook.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权抖音"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/douyin.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权领英"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/linkedin.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权微软"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/microsoft.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权小米"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/mi.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权今日头条"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/toutiao.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权csdn"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/csdn.png" width="20"></a></td>
        </tr>
    </table>
</center>

-------------------------------------------------------------------------------



JustAuth，如你所见，它仅仅是一个**第三方授权登录**的**工具类库**，它可以让我们脱离繁琐的第三方登录SDK，让登录变得**So easy!**

项目开源地址：[gitee](https://gitee.com/yadong.zhang/JustAuth) | [github](https://github.com/zhangyd-c/JustAuth)

## 特点

废话不多说，就俩字：

1. **全**：已集成十多家第三方平台（国内外常用的基本都已包含），后续依然还有扩展计划！
2. **简**：API就是奔着最简单去设计的（见后面`快速开始`），尽量让您用起来没有障碍感！

## 快速开始

- 引入依赖
```xml
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>1.8.1</version>
</dependency>
```
- 调用api
```java
// 创建授权request
AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
        .clientId("clientId")
        .clientSecret("clientSecret")
        .redirectUri("redirectUri")
        .state("state")
        .build());
// 生成授权页面
authRequest.authorize();
// 授权登录后会返回code（auth_code（仅限支付宝））、state，1.8.0版本后，可以用AuthCallback类作为回调接口的参数
authRequest.login(callback);
```

注：`1.8.0`版本后，增加了`state`参数校验，用于防止[CSRF](https://zh.wikipedia.org/wiki/%E8%B7%A8%E7%AB%99%E8%AF%B7%E6%B1%82%E4%BC%AA%E9%80%A0)。强烈建议，保证单次流程内`state`的唯一性，且每个`state`只可用一次。

**配套Demo**：
- [Springboot版](https://gitee.com/yadong.zhang/JustAuth-demo)
- [jFinal版](https://github.com/zhangyd-c/jfinal-justauth-demo)

具体的例子可以参考：

- [实现Gitee授权登录](http://t.cn/ExDKxQs)
- [实现Github授权登录](http://t.cn/EJ0Fxqo)
- [Spring Boot 快速集成第三方登录功能](http://t.cn/AiWWx5kH)

#### API列表
|  :computer: 平台  |  :coffee: API类  |  :page_facing_up: SDK  |
|:------:|:-------:|:-------:|
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/gitee.png" width="20">  |  [AuthGiteeRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  | <a href="https://gitee.com/api/v5/oauth_doc#list_1" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/github.png" width="20">  |  [AuthGithubRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  |  <a href="https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/weibo.png" width="20">  |  [AuthWeiboRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  |  <a href="https://open.weibo.com/wiki/%E6%8E%88%E6%9D%83%E6%9C%BA%E5%88%B6%E8%AF%B4%E6%98%8E" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/dingtalk.png" width="20">  |  [AuthDingTalkRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthDingTalkRequest.java)  |  <a href="https://open-doc.dingtalk.com/microapp/serverapi2/kymkv6" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/baidu.png" width="20">  |  [AuthBaiduRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthBaiduRequest.java)  |  <a href="http://developer.baidu.com/wiki/index.php?title=docs/oauth" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/coding.png" width="25">  |  [AuthCodingRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthCodingRequest.java)  |  <a href="https://open.coding.net/references/oauth/" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/tencentCloud.png" width="25">  |  [AuthTencentCloudRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthTencentCloudRequest.java)  |  <a href="https://dev.tencent.com/help/doc/faq/b4e5b7aee786/oauth" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/oschina.png" width="20">  |  [AuthOschinaRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthOschinaRequest.java)  |  <a href="https://www.oschina.net/openapi/docs/oauth2_authorize" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/alipay.png" width="20">  |  [AuthAlipayRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthAlipayRequest.java)  |  <a href="https://alipay.open.taobao.com/docs/doc.htm?spm=a219a.7629140.0.0.336d4b70GUKXOl&treeId=193&articleId=105809&docType=1" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/qq.png" width="20">  |  [AuthQqRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthQqRequest.java)  |  <a href="https://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/wechat.png" width="20">  |  [AuthWeChatRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthWeChatRequest.java)   |  <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/taobao.png" width="20">  |  [AuthTaobaoRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthTaobaoRequest.java)   |  <a href="https://open.taobao.com/doc.htm?spm=a219a.7386797.0.0.4e00669acnkQy6&source=search&docId=105590&docType=1" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/google.png" width="20">  |  [AuthGoogleRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGoogleRequest.java)   |  <a href="https://developers.google.com/identity/protocols/OpenIDConnect" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/facebook.png" width="20">  |  [AuthFacebookRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthFacebookRequest.java)   |  <a href="https://developers.facebook.com/docs/facebook-login/manually-build-a-login-flow" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/douyin.png" width="20">  |  [AuthDouyinRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthDouyinRequest.java)   |  <a href="https://www.douyin.com/platform/doc/m-2-1-1" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/linkedin.png" width="20">  |  [AuthLinkedinRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthLinkedinRequest.java)   |  <a href="https://docs.microsoft.com/zh-cn/linkedin/shared/authentication/authorization-code-flow?context=linkedin/context" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/microsoft.png" width="20">  | [AuthMicrosoftRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthMicrosoftRequest.java) | <a href="https://docs.microsoft.com/zh-cn/graph/auth/" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/mi.png" width="20">  | [AuthMiRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthMiRequest.java) | <a href="https://dev.mi.com/console/doc/detail?pId=711" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/toutiao.png" width="20">  | [AuthToutiaoRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthToutiaoRequest.java) | <a href="https://open.mp.toutiao.com/#/resource?_k=y7mfgk" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/csdn.png" width="20">  |  [AuthCsdnRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthCsdnRequest.java)  |  无 |

_请知悉：经咨询CSDN官方客服得知，CSDN的授权开放平台已经下线。如果以前申请过的应用，可以继续使用，但是不再支持申请新的应用。so, 本项目中的CSDN登录只能针对少部分用户使用了_

## 后续开发计划

参考：[[开发计划] 待扩展的第三方平台](https://gitee.com/yadong.zhang/JustAuth/issues/IUGRK)

另外，期待您和我一起完善这个项目！

## 贡献代码

1. fork本项目到自己的repo
2. 把fork过去的项目也就是你仓库中的项目clone到你的本地
3. 修改代码
4. commit后push到自己的库
5. 发起PR（pull request） 请求
6. 等待作者合并

## 致谢

在项目立项初期，也对当前开源圈的一些相同类型的项目作过调研，同时本项目也参考过这些项目，再次感谢开源圈内的朋友。

[YurunOAuthLogin](https://gitee.com/yurunsoft/YurunOAuthLogin): PHP 第三方登录授权 SDK

[阿里妈妈MUX倾力打造的矢量图标库-iconfont](https://www.iconfont.cn/search/index): 本文档中的图标大部分取自该平台

## 关于OAuth

[The OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749)

## 关注&交流

|  公众号  |  微信(备注:加群)  |
| :------------: | :------------: |
| <img src="https://gitee.com/yadong.zhang/static/raw/master/wx/wechat_account.jpg" width="200" /> | <img src="https://gitee.com/yadong.zhang/static/raw/master/wx/wx.png" width="170"/> |

 **QQ群** 

- JustAuth交流群 （230017570）：专业交流该项目

- 开源总群 （190886500）：各个开源项目的都有，也有博客建设等方面的朋友。（注意，该群需付费进入，防止发垃圾广告、垃圾推广等人士）


## 请喝咖啡

| 支付宝  | 微信  |
| :------------: | :------------: |
| <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/zfb_code.png" width="200"/> | <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/wx_code.png" width="200" /> |