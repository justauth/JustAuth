本文将就JustAuth中涉及到的一些配置、关键词做一下简单说明，方便使用者理解、使用。

## 本文相关名词

- `开发者` 指使用`JustAuth`的开发者
- `第三方` 指开发者对接的第三方网站，比如：QQ平台、微信平台、微博平台
- `用户` 指最终服务的真实用户

## JustAuth中的关键词

以下内容了解后，将会使你更容易地上手JustAuth。

- `clientId` 客户端身份标识符（应用id），一般在申请完Oauth应用后，由**第三方平台颁发**，唯一
- `clientSecret` 客户端密钥，一般在申请完Oauth应用后，由**第三方平台颁发**
- `redirectUri` **开发者项目中的有效api地址**。用户在确认第三方平台授权（登录）后，第三方平台会重定向到该地址，并携带code等参数
- `state` 用来保持授权会话流程完整性，防止CSRF攻击的安全的随机的参数，由**开发者生成**
- `alipayPublicKey`  支付宝公钥。当选择支付宝登录时，必传该值，由**开发者生成**
- `unionId`  是否需要申请unionid，目前只针对**qq登录**。注：qq授权登录时，获取unionid需要单独发送邮件申请权限。如果个人开发者账号中申请了该权限，可以将该值置为true，在获取openId时就会同步获取unionId。参考链接：[UnionID介绍](http://wiki.connect.qq.com/unionid%E4%BB%8B%E7%BB%8D)
- `stackOverflowKey` Stack Overflow 登陆时需单独提供的key，由**第三方平台颁发**
- `agentId`  企业微信登陆时需单独提供该值，由**第三方平台颁发**，为授权方的网页应用ID
- `source` JustAuth支持的第三方平台，比如：GITHUB、GITEE等

## 参考资料

关于OAuth2相关的内容、原理可以自行参阅以下资料：

- [The OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749)
- [OAuth 2.0](https://oauth.net/2/)

