package me.zhyd.oauth.utils;

import org.junit.Test;

public class UuidUtilsTest {

    @Test
    public void getUUID() {

        String uuid = UuidUtils.getUUID();
        System.out.println(uuid);
    }
}
