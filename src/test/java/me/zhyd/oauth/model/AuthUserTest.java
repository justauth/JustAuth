package me.zhyd.oauth.model;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

public class AuthUserTest {

    @Test
    public void serialize() {

        AuthUser user = AuthUser.builder()
            .nickname("test")
            .build();
        String json = JSON.toJSONString(user);
        Assert.assertEquals(json, "{\"nickname\":\"test\"}");

    }

    @Test
    public void deserialize() {
        AuthUser user = AuthUser.builder()
            .nickname("test")
            .build();
        String json = JSON.toJSONString(user);

        AuthUser deserializeUser = JSON.parseObject(json, AuthUser.class);
        Assert.assertEquals(deserializeUser.getNickname(), "test");
    }

}
