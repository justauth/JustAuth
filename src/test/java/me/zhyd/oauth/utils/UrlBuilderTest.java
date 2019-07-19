package me.zhyd.oauth.utils;

import org.junit.Assert;
import org.junit.Test;

public class UrlBuilderTest {

    @Test
    public void testGetGithub() {
        Assert.assertEquals("https://github.com/login/oauth/access_token?client_id=1&client_secret=foo&code=bar&redirect_uri=baz",
                UrlBuilder.getGithubAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://api.github.com/user?access_token=foo",
                UrlBuilder.getGithubUserInfoUrl("foo"));
        Assert.assertEquals("https://github.com/login/oauth/authorize?client_id=1&redirect_uri=foo&state=bar",
                UrlBuilder.getGithubAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetWeibo() {
        Assert.assertEquals("https://api.weibo.com/oauth2/access_token?client_id=1&client_secret=foo&grant_type=authorization_code&code=bar&redirect_uri=baz",
                UrlBuilder.getWeiboAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://api.weibo.com/2/users/show.json?foo",
                UrlBuilder.getWeiboUserInfoUrl("foo"));
        Assert.assertEquals("https://api.weibo.com/oauth2/authorize?client_id=1&response_type=code&redirect_uri=foo&state=bar",
                UrlBuilder.getWeiboAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetGitee() {
        Assert.assertEquals("https://gitee.com/oauth/token?client_id=1&client_secret=foo&grant_type=authorization_code&code=bar&redirect_uri=baz",
                UrlBuilder.getGiteeAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://gitee.com/api/v5/user?access_token=foo",
                UrlBuilder.getGiteeUserInfoUrl("foo"));
        Assert.assertEquals("https://gitee.com/oauth/authorize?client_id=1&response_type=code&redirect_uri=foo&state=bar",
                UrlBuilder.getGiteeAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetDingTalk() {
        Assert.assertEquals("https://oapi.dingtalk.com/connect/qrconnect?appid=1&response_type=code&scope=snsapi_login&redirect_uri=foo&state=bar",
                UrlBuilder.getDingTalkQrConnectUrl("1", "foo", "bar"));
        Assert.assertEquals("https://oapi.dingtalk.com/sns/getuserinfo_bycode?signature=1&timestamp=foo&accessKey=bar",
                UrlBuilder.getDingTalkUserInfoUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetBaidu() {
        Assert.assertEquals("https://openapi.baidu.com/oauth/2.0/token?client_id=1&client_secret=foo&grant_type=authorization_code&code=bar&redirect_uri=baz",
                UrlBuilder.getBaiduAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://openapi.baidu.com/rest/2.0/passport/users/getInfo?access_token=foo",
                UrlBuilder.getBaiduUserInfoUrl("foo"));
        Assert.assertEquals("https://openapi.baidu.com/oauth/2.0/authorize?client_id=1&response_type=code&redirect_uri=foo&display=popup&state=bar",
                UrlBuilder.getBaiduAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://openapi.baidu.com/rest/2.0/passport/auth/revokeAuthorization?access_token=foo",
                UrlBuilder.getBaiduRevokeUrl("foo"));
    }

    @Test
    public void testGetCsdn() {
        Assert.assertEquals("https://api.csdn.net/oauth2/access_token?client_id=1&client_secret=foo&grant_type=authorization_code&code=bar&redirect_uri=baz",
                UrlBuilder.getCsdnAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://api.csdn.net/user/getinfo?access_token=foo",
                UrlBuilder.getCsdnUserInfoUrl("foo"));
        Assert.assertEquals("https://api.csdn.net/oauth2/authorize?client_id=1&response_type=code&redirect_uri=foo&state=bar",
                UrlBuilder.getCsdnAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetCoding() {
        Assert.assertEquals("https://coding.net/api/oauth/access_token?client_id=1&client_secret=foo&grant_type=authorization_code&code=bar",
                UrlBuilder.getCodingAccessTokenUrl("1", "foo", "bar"));
        Assert.assertEquals("https://coding.net/api/account/current_user?access_token=foo",
                UrlBuilder.getCodingUserInfoUrl("foo"));
        Assert.assertEquals("https://coding.net/oauth_authorize.html?client_id=1&response_type=code&redirect_uri=foo&scope=user&state=bar",
                UrlBuilder.getCodingAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetTencent() {
        Assert.assertEquals("https://dev.tencent.com/api/oauth/access_token?client_id=1&client_secret=foo&grant_type=authorization_code&code=bar",
                UrlBuilder.getTencentCloudAccessTokenUrl("1", "foo", "bar"));
        Assert.assertEquals("https://dev.tencent.com/api/account/current_user?access_token=foo",
                UrlBuilder.getTencentCloudUserInfoUrl("foo"));
        Assert.assertEquals("https://dev.tencent.com/oauth_authorize.html?client_id=1&response_type=code&redirect_uri=foo&scope=user&state=bar",
                UrlBuilder.getTencentCloudAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetOschina() {
        Assert.assertEquals("https://www.oschina.net/action/openapi/token?client_id=1&client_secret=foo&grant_type=authorization_code&code=bar&redirect_uri=baz&dataType=json",
                UrlBuilder.getOschinaAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://www.oschina.net/action/openapi/user?access_token=foo&dataType=json",
                UrlBuilder.getOschinaUserInfoUrl("foo"));
        Assert.assertEquals("https://www.oschina.net/action/oauth2/authorize?client_id=1&response_type=code&redirect_uri=foo&state=bar",
                UrlBuilder.getOschinaAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetQq() {
        Assert.assertEquals("https://graph.qq.com/oauth2.0/token?client_id=1&client_secret=foo&grant_type=authorization_code&code=bar&redirect_uri=baz",
                UrlBuilder.getQqAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://graph.qq.com/user/get_user_info?oauth_consumer_key=1&access_token=foo&openid=bar",
                UrlBuilder.getQqUserInfoUrl("1", "foo", "bar"));
        Assert.assertEquals("https://graph.qq.com/oauth2.0/authorize?client_id=1&response_type=code&redirect_uri=foo&state=bar",
                UrlBuilder.getQqAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("http://www.foo.bar?access_token=foo&unionid=1",
                UrlBuilder.getQqOpenidUrl("http://www.foo.bar", "foo", true));
    }

    @Test
    public void testGetAlipayAuthorizeUrl() {
        Assert.assertEquals("https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=1&scope=auth_user&redirect_uri=foo&state=bar",
                UrlBuilder.getAlipayAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetWeChat() {
        Assert.assertEquals("https://open.weixin.qq.com/connect/qrconnect?appid=1&redirect_uri=foo&response_type=code&scope=snsapi_login&state=bar#wechat_redirect",
                UrlBuilder.getWeChatAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://api.weixin.qq.com/sns/oauth2/access_token?appid=1&secret=foo&code=bar&grant_type=authorization_code",
                UrlBuilder.getWeChatAccessTokenUrl("1", "foo", "bar"));
        Assert.assertEquals("https://api.weixin.qq.com/sns/userinfo?access_token=1&openid=foo&lang=zh_CN",
                UrlBuilder.getWeChatUserInfoUrl("1", "foo"));
        Assert.assertEquals("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=1&grant_type=refresh_token&refresh_token=foo",
                UrlBuilder.getWeChatRefreshUrl("1", "foo"));
    }

    @Test
    public void testGetTaobao() {
        Assert.assertEquals("https://oauth.taobao.com/token?client_id=1&client_secret=foo&code=bar&redirect_uri=baz&grant_type=authorization_code",
                UrlBuilder.getTaobaoAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://oauth.taobao.com/authorize?response_type=code&client_id=1&redirect_uri=foo&state=bar&view=web",
                UrlBuilder.getTaobaoAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetGoogle() {
        Assert.assertEquals("https://accounts.google.com/o/oauth2/v2/auth?client_id=1&response_type=code&scope=openid%20email%20profile&redirect_uri=foo&state=bar",
                UrlBuilder.getGoogleAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://www.googleapis.com/oauth2/v4/token?client_id=1&client_secret=foo&code=bar&redirect_uri=baz&grant_type=authorization_code",
                UrlBuilder.getGoogleAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://oauth2.googleapis.com/tokeninfo?id_token=foo",
                UrlBuilder.getGoogleUserInfoUrl("foo"));
        Assert.assertEquals("https://www.facebook.com/v3.3/dialog/oauth?client_id=1&redirect_uri=foo&state=bar&response_type=code&scope=",
                UrlBuilder.getFacebookAuthorizeUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetFacebook() {
        Assert.assertEquals("https://www.facebook.com/v3.3/dialog/oauth?client_id=1&redirect_uri=foo&state=bar&response_type=code&scope=",
                UrlBuilder.getFacebookAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://graph.facebook.com/v3.3/oauth/access_token?client_id=1&client_secret=foo&code=bar&redirect_uri=baz&grant_type=authorization_code",
                UrlBuilder.getFacebookAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://graph.facebook.com/v3.3/me?access_token=foo&fields=id,name,birthday,gender,hometown,email,devices,picture.width(400)",
                UrlBuilder.getFacebookUserInfoUrl("foo"));
    }

    @Test
    public void testGetDouyin() {
        Assert.assertEquals("https://open.douyin.com/platform/oauth/connect?client_key=1&redirect_uri=foo&state=bar&response_type=code&scope=user_info",
                UrlBuilder.getDouyinAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://open.douyin.com/oauth/access_token/?client_key=1&client_secret=foo&code=bar&grant_type=authorization_code",
                UrlBuilder.getDouyinAccessTokenUrl("1", "foo", "bar"));
        Assert.assertEquals("https://open.douyin.com/oauth/userinfo/?access_token=1&open_id=foo",
                UrlBuilder.getDouyinUserInfoUrl("1", "foo"));
        Assert.assertEquals("https://open.douyin.com/oauth/refresh_token/?client_key=1&refresh_token=foo&grant_type=refresh_token",
                UrlBuilder.getDouyinRefreshUrl("1", "foo"));
    }

    @Test
    public void testGetLinkedin() {
        Assert.assertEquals("https://www.linkedin.com/oauth/v2/authorization?client_id=1&redirect_uri=foo&state=bar&response_type=code&scope=r_liteprofile%20r_emailaddress%20w_member_social",
                UrlBuilder.getLinkedinAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://www.linkedin.com/oauth/v2/accessToken?client_id=1&client_secret=foo&code=bar&redirect_uri=baz&grant_type=authorization_code",
                UrlBuilder.getLinkedinAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://api.linkedin.com/v2/me?projection=(id,firstName,lastName,profilePicture(displayImage~:playableStreams))",
                UrlBuilder.getLinkedinUserInfoUrl());
        Assert.assertEquals("https://www.linkedin.com/oauth/v2/accessToken?client_id=1&client_secret=foo&refresh_token=bar&grant_type=refresh_token",
                UrlBuilder.getLinkedinRefreshUrl("1", "foo", "bar"));
    }

    @Test
    public void testGetMicrosoft() {
        Assert.assertEquals("https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=1&response_type=code&redirect_uri=foo&response_mode=query&scope=offline_access%20user.read%20mail.read&state=bar",
                UrlBuilder.getMicrosoftAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://login.microsoftonline.com/common/oauth2/v2.0/token?client_id=1&client_secret=foo&scope=user.read%20mail.read&redirect_uri=bar&code=baz&grant_type=authorization_code",
                UrlBuilder.getMicrosoftAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://graph.microsoft.com/v1.0/me",
                UrlBuilder.getMicrosoftUserInfoUrl());
        Assert.assertEquals("https://login.microsoftonline.com/common/oauth2/v2.0/token?client_id=1&client_secret=foo&scope=user.read%20mail.read&redirect_uri=bar&refresh_token=baz&grant_type=refresh_token",
                UrlBuilder.getMicrosoftRefreshUrl("1", "foo", "bar", "baz"));
    }

    @Test
    public void testGetMiRefreshUrl() {
        Assert.assertEquals("https://account.xiaomi.com/oauth2/authorize?client_id=1&redirect_uri=foo&response_type=code&scope=user/profile%20user/openIdV2%20user/phoneAndEmail&state=bar&skip_confirm=false",
                UrlBuilder.getMiAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://account.xiaomi.com/oauth2/token?client_id=1&client_secret=foo&redirect_uri=bar&code=baz&grant_type=authorization_code",
                UrlBuilder.getMiAccessTokenUrl("1", "foo", "bar", "baz"));
        Assert.assertEquals("https://open.account.xiaomi.com/user/profile?clientId=1&token=foo",
                UrlBuilder.getMiUserInfoUrl("1", "foo"));
        Assert.assertEquals("https://account.xiaomi.com/oauth2/token?client_id=1&client_secret=foo&redirect_uri=bar&refresh_token=baz&grant_type=refresh_token",
                UrlBuilder.getMiRefreshUrl("1", "foo", "bar", "baz"));
    }

    @Test
    public void testGetToutiao() {
        Assert.assertEquals("https://open.snssdk.com/auth/authorize?client_key=1&redirect_uri=foo&state=bar&response_type=code&auth_only=1&display=0",
                UrlBuilder.getToutiaoAuthorizeUrl("1", "foo", "bar"));
        Assert.assertEquals("https://open.snssdk.com/auth/token?client_key=1&client_secret=foo&code=bar&grant_type=authorize_code",
                UrlBuilder.getToutiaoAccessTokenUrl("1", "foo", "bar"));
        Assert.assertEquals("https://open.snssdk.com/data/user_profile?client_key=1&access_token=foo",
                UrlBuilder.getToutiaoUserInfoUrl("1", "foo"));
    }
}
