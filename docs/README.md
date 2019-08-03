<p align="center">
	<a href="https://www.justauth.cn/"><img src="./_media/cover.png" width="400"></a>
</p>
<p align="center">
	<strong>Login, so easy!</strong>
</p>
<p align="center">
	<strong>史上最全的整合第三方登录的开源库</strong>
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/search?q=JustAuth">
		<img src="https://img.shields.io/badge/Maven Central-1.9.5-blue.svg" ></img>
	</a>
	<a target="_blank" href="https://gitee.com/yadong.zhang/JustAuth/blob/master/LICENSE">
		<img src="https://img.shields.io/apm/l/vim-mode.svg?color=yellow" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
	<a target="_blank" href="https://apidoc.gitee.com/yadong.zhang/JustAuth/">
		<img src="https://img.shields.io/badge/Docs-1.9.5-orange.svg" ></img>
	</a>
	<a href="https://codecov.io/gh/zhangyd-c/JustAuth">
		<img src="https://codecov.io/gh/zhangyd-c/JustAuth/branch/master/graph/badge.svg" />
	</a>
	<a href='https://gitee.com/yadong.zhang/JustAuth/stargazers'>
	  <img src='https://gitee.com/yadong.zhang/JustAuth/badge/star.svg?theme=white' alt='star'></img>
	</a>
	<a target="_blank" href='https://github.com/zhangyd-c/JustAuth'>
		<img src="https://img.shields.io/github/stars/zhangyd-c/JustAuth.svg?style=social" alt="github star"></img>
	</a>
</p>
<p align="center">
	<strong>开源地址：</strong> <a target="_blank" href='https://gitee.com/yadong.zhang/JustAuth'>Gitee</a> | <a target="_blank" href='https://github.com/zhangyd-c/JustAuth'>Github</a>
</p>
<p align="center">
    <strong>QQ群：</strong>230017570
</p>
<p align="center"> 
    <strong>文档更新日期：</strong> {docsify-updated}
</p>

## 简介

JustAuth，如你所见，它仅仅是一个**第三方授权登录**的**工具类库**，它可以让我们脱离繁琐的第三方登录SDK，让登录变得**So easy!**

## 特点

废话不多说，就俩字：

1. **全**：已集成十多家第三方平台（国内外常用的基本都已包含），后续依然还有扩展计划！
2. **简**：API就是奔着最简单去设计的，尽量让您用起来没有障碍感！

## 已集成的平台

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
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/teambition.png" width="20">  | [AuthTeambitionRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthTeambitionRequest.java) | <a href="https://docs.teambition.com/" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/renren.png" width="20">  | [AuthRenrenRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthRenrenRequest.java) | <a href="http://open.renren.com/wiki/OAuth2.0" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/pinterest.png" width="20">  | [AuthPinterestRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthPinterestRequest.java) | <a href="https://developers.pinterest.com/docs/api/overview/?" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/stackoverflow.png" width="20">  | [AuthStackOverflowRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthStackOverflowRequest.java) | <a href="https://api.stackexchange.com/docs/authentication" target="_blank">参考文档</a> |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/csdn.png" width="20">  |  [AuthCsdnRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthCsdnRequest.java)  |  无 |


## 快速开始

- 引入依赖
```xml
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>1.9.5</version>
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

## 贡献代码

1. fork本项目到自己的repo
2. 把fork过去的项目也就是你仓库中的项目clone到你的本地
3. 修改代码
4. commit后push到自己的库
5. 发起PR（pull request） 请求，提交到`dev`分支
6. 等待作者合并

_注：JustAuth只接受集成oauth2.0的平台_

## 捐赠

| 支付宝  | 微信  |
| :------------: | :------------: |
| <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/zfb_code.png" width="200"/> | <img src="https://gitee.com/yadong.zhang/static/raw/master/qrcode/wx_code.png" width="200" /> |

