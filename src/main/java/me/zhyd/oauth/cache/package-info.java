/**
 * JustAuth 缓存实现， 提供基础的基于ConcurrentHashMap + ScheduledExecutorService 实现的定时缓存。
 * 同时对外暴露{@code AuthStateCache}接口，可进行对缓存实现的自定义。
 */
package me.zhyd.oauth.cache;
