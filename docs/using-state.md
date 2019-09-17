# 使用State

## state使用的流程

在JustAuth中`state`参数的使用流程如下：

1. 获取`authorizeUrl`时创建`state`(开发者创建，如果不创建则系统默认生成)
2. 缓存`state`（JustAuth执行）
3. 内置的缓存调度器自动清除已过期的`state`（JustAuth执行）

## 创建state（开发者）
`state`在OAuth授权流程中是一个**非必要但很重要**的参数，就如[名词解释](https://docs.justauth.whnb.wang/#/explain?id=justauth中的关键词)中描述的：`state`是用来保持授权会话流程完整性，防止CSRF攻击的安全的随机的参数，**由开发者生成**。

在JustAuth中提供了一个默认的创建state的方法，使用方式：

```java
String state = AuthStateUtils.createState()
```

`createState`的内部实现其实就是生成了一个UUID（采用 jdk 9 的形式，优化性能），该工具是直接copy自[mica](https://github.com/lets-mica/mica/blob/master/mica-core/src/main/java/net/dreamlu/mica/core/utils/StringUtil.java)（`mica`是一个SpringBoot微服务高效开发工具集，开源地址：[https://github.com/lets-mica/mica](https://github.com/lets-mica/mica)），关于mica uuid生成方式的压测结果，可以参考：https://github.com/lets-mica/mica-jmh/wiki/uuid。

除此之外，开发者还可以自己生成特定的`state`参数。

## 缓存state（JustAuth）

在JustAuth中，内置了一个基于map的state缓存器，默认缓存有效期为3分钟（缓存配置见`AuthCacheConfig.java`）。`AuthCacheConfig`中包含两个配置参数：

- `timeout` 缓存过期时间，默认3分钟
- `schedulePrune` 是否开启定时清理过期state的任务，默认开启。如果不开启，则需要开发者自己对state做处理，防止map存入过多内容

缓存state的操作是在`getRealState`中触发的，不需要开发者自己处理
```java
/**
 * 获取state，如果为空， 则默认取当前日期的时间戳
 *
 * @param state 原始的state
 * @return 返回不为null的state
 */
protected String getRealState(String state) {
    if (StringUtils.isEmpty(state)) {
        state = UuidUtils.getUUID();
    }
    // 缓存state
    authStateCache.cache(state, state);
    return state;
}
```

注：关于自定义缓存，请参考下节内容。

## 清理state（JustAuth）

JustAuth内置了一个缓存调度器，默认3分钟清理一次过期的`state`，缓存清理时间可以通过`AuthCacheConfig.timeout`进行修改，不建议修改太大。
