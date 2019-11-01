# 关于OAuth

请先查阅以下资料：

- [The OAuth 2.0 Authorization Framework](https://tools.ietf.org/html/rfc6749)
- [OAuth 2.0](https://oauth.net/2/)

## OAuth 2 的授权流程

### 参与的角色

- `Resource Owner` 资源所有者，即代表授权客户端访问本身资源信息的用户（User），也就是应用场景中的“**开发者A**”
- `Resource Server` 资源服务器，托管受保护的**用户账号信息**，比如Github
- `Authorization Server` 授权服务器，**验证用户身份**然后为客户端派发资源访问令牌，比如Github
  - `Resource Server`和`Authorization Server` 可以是同一台服务器，也可以是不同的服务器，视具体的授权平台而有所差异
- `Client` 客户端，即代表意图访问受限资源的**第三方应用**

### 授权流程
```html
     +--------+                               +---------------+
     |        |--(A)- Authorization Request ->|   Resource    |
     |        |                               |     Owner     |
     |        |<-(B)-- Authorization Grant ---|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(C)-- Authorization Grant -->| Authorization |
     | Client |                               |     Server    |
     |        |<-(D)----- Access Token -------|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(E)----- Access Token ------>|    Resource   |
     |        |                               |     Server    |
     |        |<-(F)--- Protected Resource ---|               |
     +--------+                               +---------------+
```

上面的流程图取自[The OAuth 2.0 Authorization Framework#1.2](https://tools.ietf.org/html/rfc6749#section-1.2)

**流程解析**

- (A)  用户打开**客户端**以后，**客户端**要求**用户**给予授权。
- (B)  **用户**同意给予**客户端**授权。
- (C)  **客户端**使用上一步获得的授权，向**认证服务器**申请令牌。
- (D)  **认证服务器**对**客户端**进行认证以后，确认无误，同意发放令牌
- (E)  **客户端**使用令牌，向**资源服务器**申请获取资源。
- (F)  **资源服务器**确认令牌无误，同意向**客户端**开放资源。

### 授权许可 `Authorization Grant`

- Authorization Code
  - 结合普通服务器端应用使用(**web**端常用的授权方式)
- Implicit
  - 结合移动应用或 Web App 使用
- Resource Owner Password Credentials
  - 适用于受信任客户端应用，例如同个组织的内部或外部应用
- Client Credentials
  - 适用于客户端调用主服务API型应用（比如百度API Store）
  
## 直白话 OAuth 2 流程

以上流程理解起来可能有些难度，这儿我们给出一个白话版的流程图

这儿引入三个角色：
- 用户A：可以理解成你自己
- 网站B：可以理解成 Oschina
- 第三方C：可以理解成 Github

需求：你想通过Github第三方登录Oschina

<div class="mermaid">
    sequenceDiagram
      用户A->>网站B: 1.我想登录你
      网站B->>用户A: 2.我不认识你
      用户A->>第三方C: 3.老铁我去过你那儿，咱俩认识，你帮我授权给网站B
      第三方C->>网站B: 4.用户A是我老铁，给你他的授权码
      网站B->>第三方C: 5.这个授权码是你那儿的人吗？是的话给我他的令牌
      第三方C->>网站B: 6.是我这儿的人，让他登录吧
      网站B->>用户A: 7.得嘞，您走着
      用户A->>网站B: 8.登录成功
</div>
