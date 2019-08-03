## ~~升级到1.8.0后如何启用state？~~

~~在原api使用方法的基础上，为config追加一个state即可。~~
```
AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
        .clientId("clientId")
        .clientSecret("clientSecret")
        .redirectUri("redirectUri")
        .state("state") // 就是这儿
        .build());
```

## ~~升级到1.8.0后login方法报错？~~

~~这是因为1.8.0版本中新增了[AuthCallback](https://gitee.com/yadong.zhang/JustAuth/blob/master/src/main/java/me/zhyd/oauth/model/AuthCallback.java)类，这个类封装了所有可能的回调参数。目前包含以下三个参数：~~
- ~~`code`: 访问AuthorizeUrl后回调时带的参数code，用来换取token~~
- ~~`auth_code`: 支付宝授权登陆时不会返回code而是返回`auth_code`参数~~
- ~~`state`: 访问AuthorizeUrl后回调时带的参数state，用于和请求AuthorizeUrl前的state比较，防止CSRF攻击~~

~~1.8.0版本之后的api，可以直接用AuthCallback类作为回调方法的入参，比如：~~
```
@RequestMapping("/callback/{source}")
public Object login(@PathVariable("source") String source, AuthCallback callback) {
	System.out.println("进入callback：" + source + " callback params：" + JSONObject.toJSONString(callback));
	AuthRequest authRequest = getAuthRequest(source);
	AuthResponse response = authRequest.login(callback);
	System.out.println(JSONObject.toJSONString(response));
	return response;
}
```
 ~~_代码截取自_ ：https://gitee.com/yadong.zhang/JustAuth-demo~~

## ~~升级到1.8.0后对于state参数有什么特殊要求吗？~~

~~理论上没有，stata只是用来保持会话状态，因为http协议的无状态性，从授权到回调，无法感知具体是哪个用户触发的。所以可以使用state作为校验。注：state参数每次完整的授权链中只可用一次！（也是为了防止不必要的危险）~~

~~作者建议state命名格式如下：~~
- ~~授权登录：`{source}_{ip}_{random}`~~
- ~~账号绑定：`{source}_{userId}_{ip}_{random}`~~

~~其中`source`表示授权平台，可以直接去JustAuth中的source，`ip`为当前用户的ip（部分情况可能不适用），`random`为随机字符串，`userId`为当前登录用户的id。~~

~~注：`authorize`和`login`（不是指回调传回的`state`，而是声明`request`时传入的`state`）中传的`state`务必保证一致~~

## 升级到1.9.3+版本后编译失败

主要明显的就是`IpUtils.getIp`和request的`.state`报错。

这是因为从`v1.9.3`版本开始，对项目进行了一些优化，具体优化内容参考：[v1.9.3](https://gitee.com/yadong.zhang/JustAuth/releases/v1.9.3)和[v1.9.4](https://gitee.com/yadong.zhang/JustAuth/releases/v1.9.4)。

新版本的使用方式，参考[JustAuth-demo](https://gitee.com/yadong.zhang/JustAuth-demo/blob/master/src/main/java/me/zhyd/justauth/RestAuthController.java)
```
@RequestMapping("/render/{source}")
public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
	AuthRequest authRequest = getAuthRequest(source);
	String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
	response.sendRedirect(authorizeUrl);
}
@RequestMapping("/callback/{source}")
public Object login(@PathVariable("source") String source, AuthCallback callback) {
	AuthRequest authRequest = getAuthRequest(source);
	AuthResponse response = authRequest.login(callback);
	return response;
}
```

## 升级到最新版本后为什么支付宝登录不能用了？

在升级到新版后，使用支付宝登录会提示`ClassNotFoundExcption`异常，这是因为从`1.9.4`版本开始，JustAuth将不在强依赖`alipay-sdk-java`，如果你需要用到Alipay的授权登陆，那么你还需要添加以下依赖：

```
<dependency>
	<groupId>com.alipay.sdk</groupId>
	<artifactId>alipay-sdk-java</artifactId>
	<version>3.7.4.ALL</version>
</dependency>
```
