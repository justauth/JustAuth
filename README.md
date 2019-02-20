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
		<img src="https://img.shields.io/badge/License-GPL%20v3-yellow.svg" ></img>
	</a>
	<a target="_blank" href="https://www.oracle.com/technetwork/java/javase/downloads/index.html">
		<img src="https://img.shields.io/badge/JDK-1.8+-green.svg" ></img>
	</a>
</p>

<center>
    <table>
        <thead>
            <tr>
                <td align="center" width="200"><a href="https://gitee.com/"><img src="https://gitee.com/logo_icon.png" width="30"></a></td>
                <td align="center" width="200"><a href="https://github.com"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/github.png" width="30"></a></td>
                <td align="center" width="200"><a href="https://weibo.com"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/weibo.png" width="30"></a></td>
                <td align="center" width="200"><a href="https://www.dingtalk.com"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/dingding.png" width="30"></a></td>
                <td align="center" width="200"><a href="https://www.dingtalk.com"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/baidu.png" width="30"></a></td>
                <td align="center" width="200"><a href="https://www.csdn.net/"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/csdn.png" width="30"></a></td>
                <td align="center" width="200"><a href="https://connect.qq.com/devuser.html#/"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/qq.png" width="30"></a></td>
                <td align="center" width="200"><a href="https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN"><img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/wechats.png" width="30"></a></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td align="center" width="200"><a href="#Gitee">Gitee</a></td>
                <td align="center" width="200"><a href="#Github">Github</a></td>
                <td align="center" width="200"><a href="#Weibo">Weibo</a></td>
                <td align="center" width="200"><a href="#钉钉">钉钉</a></td>
                <td align="center" width="200"><a href="#百度">百度</a></td>
                <td align="center" width="200"><a href="#CSDN">CSDN</a></td>
                <td align="center" width="200"><a href="#QQ">QQ</a></td>
                <td align="center" width="200"><a href="#微信">微信</a></td>
            </tr>
        </tbody>
    </table>
</center>

-------------------------------------------------------------------------------



JustAuth，如你所见，它仅仅是一个**第三方授权登录**的**工具类库**，它可以让我们脱离繁琐的第三方登录SDK，让登录变得**So easy!**

## 快速开始
- 引入依赖
```xml
<dependency>
    <groupId>me.zhyd.oauth</groupId>
    <artifactId>JustAuth</artifactId>
    <version>1.0.0</version>
</dependency>
```
- 调用api
```java
AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
        .clientId("clientId")
        .clientSecret("clientSecret")
        .redirectUri("redirectUri")
        .build());
// 自动跳转到授权页面
authRequest.authorize(response);
// 返回授权页面，可自行跳转
authRequest.authorize();
// 授权登录后会返回一个code，用这个code进行登录
authRequest.login("code");
```

#### API列表
|  :computer: 平台  |  :coffee: API类  |  :page_facing_up: SDK  |
|:------:|:-------:|:-------:|
|  <img src="https://gitee.com/logo_icon.png" width="20">  |  [AuthGiteeRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  | [参考文档](https://github.com/settings/developers) |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/github.png" width="20">  |  [AuthGithubRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  | [参考文档](https://gitee.com/api/v5/oauth_doc#list_1)  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/weibo.png" width="20">  |  [AuthWeiboRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthGiteeRequest.java)  |  [参考文档](https://open.weibo.com/apps)  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/dingding.png" width="20">  |  [AuthDingTalkRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthDingTalkRequest.java)  |  [参考文档](https://open-doc.dingtalk.com/microapp/serverapi2/kymkv6)  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/baidu.png" width="20">  |  [AuthBaiduRequest](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/request/AuthBaiduRequest.java)  |  [参考文档](https://developer.baidu.com/)  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/csdn.png" width="20">  |  AuthCsdnRequest  |  [参考文档](https://connect.qq.com/)  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/qq.png" width="20">  |  AuthQqRequest  |  待续  |
|  <img src="https://gitee.com/yadong.zhang/static/raw/master/JustAuth/wechats.png" width="20">  |  AuthWechatRequest  |  待续  |

### 参考图例

##### Gitee

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/141459_bab67c9a_784199.png "gitee.png")

##### Github

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/141510_eaae5c8a_784199.png "github.png")

##### Weibo

待续

##### 钉钉

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/141534_251721b6_784199.png "dingding.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/141546_51ee1fc6_784199.jpeg "dingding.jpg")

##### 百度

![输入图片说明](https://images.gitee.com/uploads/images/2019/0220/141600_e796c8cf_784199.png "baidu.png")

##### CSDN

待续

##### 微信

待续
