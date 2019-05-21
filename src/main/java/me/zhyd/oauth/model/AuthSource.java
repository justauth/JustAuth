package me.zhyd.oauth.model;

/**
 * 授权来源（平台）
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
public enum AuthSource {
    GITHUB,
    GITEE,
    WEIBO,
    DINGTALK,
    BAIDU,
    CSDN,
    CODING,
    OSCHINA,
    TENCEN_CLOUD,
    ALIPAY,
    TAOBAO,
    QQ,
    WECHAT,
    /**
     * 谷歌登录，参考文档：https://developers.google.com/identity/protocols/OpenIDConnect
     */
    GOOGLE,
}
