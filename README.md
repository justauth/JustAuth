<p align="center">
	<a href="https://www.justauth.cn/"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/logo.png" width="400"></a>
</p>
<p align="center">
	<strong>Login, so easy.</strong>
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/search?q=g:%22me.zhyd%22%20AND%20a:%22JustAuth%22">
		<img src="https://img.shields.io/badge/Maven Central-1.0.0-blue.svg" ></img>
	</a>
	<a target="_blank" href="https://gitee.com/yadong.zhang/JustAuth/blob/master/LICENSE">
		<img src="https://img.shields.io/apm/l/vim-mode.svg?color=yellow" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
</p>

<center>
    <table>
        <tr>
            <td align="center" width="200"><a href="#授权gitee"><img src="https://gitee.com/logo_icon.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权github"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/github.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权weibo"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/weibo.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权钉钉"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/dingding.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权百度"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/baidu.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权csdn"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/csdn.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权coding"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/coding.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权腾讯云开发者平台"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/tencent_cloud.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权oschina"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/oschinas.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权支付宝"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/alipay.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权qq"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/qq.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权微信"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/wechats.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权淘宝"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/taobao.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权google"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/google.png" width="20"></a></td>
            <td align="center" width="200"><a href="#授权facebook"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/facebook.png" width="20"></a></td>
        </tr>
    </table>
</center>

-------------------------------------------------------------------------------



JustAuth，如你所见，它仅仅是一个**第三方授权登录**的**工具类库**，它可以让我们脱离繁琐的第三方登录SDK，让登录变得**So easy!**

项目开源地址：[gitee](https://gitee.com/yadong.zhang/JustAuth) | [github](https://github.com/zhangyd-c/JustAuth)

## 快速开始
- 引入依赖
```xml
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>1.2.0</version>
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
// 授权登录后会返回一个code，用这个code进行登录
authRequest.login("code");
```

具体的例子可以参考：

- [实现Gitee授权登录](http://t.cn/ExDKxQs)
- [实现Github授权登录](http://t.cn/EJ0Fxqo)

#### API列表
|  :computer: 平台  |  :coffee: API类  |  :page_facing_up: SDK  |
|:------:|:-------:|:-------:|
|  <img src="https://gitee.com/logo_icon.png" width="20">  |  [AuthGiteeRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  | <a href="https://gitee.com/api/v5/oauth_doc#list_1" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/github.png" width="20">  |  [AuthGithubRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  |  <a href="https://github.com/settings/developers" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/weibo.png" width="20">  |  [AuthWeiboRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  |  <a href="https://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/dingding.png" width="20">  |  [AuthDingTalkRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthDingTalkRequest.java)  |  <a href="https://open-doc.dingtalk.com/microapp/serverapi2/kymkv6" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/baidu.png" width="20">  |  [AuthBaiduRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthBaiduRequest.java)  |  <a href="https://developer.baidu.com/" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/coding.png" width="25">  |  [AuthCodingRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthCodingRequest.java)  |  <a href="https://open.coding.net/references/oauth/" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/tencent_cloud.png" width="25">  |  [AuthTencentCloudRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthTencentCloudRequest.java)  |  <a href="https://dev.tencent.com/help/doc/faq/b4e5b7aee786/oauth" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/oschinas.png" width="20">  |  [AuthOschinaRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthOschinaRequest.java)  |  <a href="https://www.oschina.net/openapi/docs/openapi_user" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/alipay.png" width="20">  |  [AuthAlipayRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthAlipayRequest.java)  |  <a href="https://alipay.open.taobao.com/docs/doc.htm?spm=a219a.7629140.0.0.336d4b70GUKXOl&treeId=193&articleId=105809&docType=1" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/qq.png" width="20">  |  [AuthQqRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthQqRequest.java)  |  <a href="http://wiki.connect.qq.com/" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/csdn.png" width="20">  |  [AuthCsdnRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthCsdnRequest.java)  |  待续 |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/wechats.png" width="20">  |  [AuthWeChatRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthWeChatRequest.java)   |  <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/taobao.png" width="20">  |  [AuthTaobaoRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthTaobaoRequest.java)   |  <a href="https://open.taobao.com/doc.htm?spm=a219a.7386797.0.0.4e00669acnkQy6&source=search&docId=105590&docType=1" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/google.png" width="20">  |  [AuthGoogleRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGoogleRequest.java)   |  <a href="https://developers.google.com/identity/protocols/OpenIDConnect" target="_blank">参考文档</a>  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/facebook.png" width="20">  |  [AuthFacebookRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthFacebookRequest.java)   |  <a href="https://developers.facebook.com/docs/facebook-login/manually-build-a-login-flow" target="_blank">参考文档</a>  |

## 后续开发计划

参考：[[开发计划] 待扩展的第三方平台](https://gitee.com/yadong.zhang/JustAuth/issues/IUGRK)

另外，期待您和我一起完善这个项目！

## 致谢

在项目立项初期，也对当前开源圈的一些相同类型的项目作过调研，同时本项目也参考过这些项目，再次感谢开源圈内的朋友。

[YurunOAuthLogin](https://gitee.com/yurunsoft/YurunOAuthLogin): PHP 第三方登录授权 SDK


## 参考图例

#### 授权gitee

![Gitee授权登录](https://images.gitee.com/uploads/images/2019/0221/140015_4c09610e_784199.png "Gitee授权登录")

#### 授权github

![Github授权登录](https://images.gitee.com/uploads/images/2019/0221/140032_58f7dfb5_784199.png "Github授权登录")

#### 授权weibo

![微博授权登录](https://images.gitee.com/uploads/images/2019/0222/191210_67d5597c_784199.png "微博授权登录")

#### 授权钉钉

![钉钉授权登录](https://images.gitee.com/uploads/images/2019/0221/140540_8da8d959_784199.jpeg "钉钉授权登录")

#### 授权百度

![百度授权登录](https://images.gitee.com/uploads/images/2019/0221/140607_ebf1dcb6_784199.png "百度授权登录")

#### 授权coding

![Coding授权登录](https://images.gitee.com/uploads/images/2019/0224/192106_fd53b3d7_784199.png "Coding授权登录")

#### 授权腾讯云开发者平台

![腾讯云开发者平台授权登录](https://images.gitee.com/uploads/images/2019/0224/192128_db9e203b_784199.png "腾讯云开发者平台授权登录")

#### 授权oschina

![授权oschina登录](https://images.gitee.com/uploads/images/2019/0322/230652_05b4fd8a_784199.png "授权oschina")

#### 授权支付宝

![授权支付宝登录](https://images.gitee.com/uploads/images/2019/0327/183654_3d4b94eb_784199.png "授权支付宝登录")

#### 授权qq

待续

#### 授权csdn

待续

#### 授权微信

待续

#### 授权淘宝

![授权淘宝登录](https://images.gitee.com/uploads/images/2019/0518/154604_68b38305_784199.png "授权淘宝登录")


#### 授权Google

![授权google登录](https://images.gitee.com/uploads/images/2019/0521/190650_85c5f1c7_784199.png "授权google登录")

#### 授权Facebook

![授权facebook登录](https://images.gitee.com/uploads/images/2019/0521/233647_6a89fb45_784199.png "授权facebook登录")

# 交流

|  微信(备注:加群)  |  公众号  |
| :------------: | :------------: |
| <img src="https://gitee.com/yadong.zhang/static/raw/master/wx/wx.png" width="170"/> | <img src="https://gitee.com/yadong.zhang/static/raw/master/wx/wechat_account.jpg" width="200" /> |

 **QQ群** 

[![](https://images.gitee.com/uploads/images/2019/0129/191256_a40bceba_784199.png)](https://shang.qq.com/wpa/qunwpa?idkey=3571c554a143eff1e15807de033a240196c6b493b25b903d1d37571cfb6040aa)


## 请喝咖啡

| 支付宝  | 微信  | 
| :------------: | :------------: | 
| <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/zfb_code.png" width="200"/> | <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/wx_code.png" width="200" /> |

