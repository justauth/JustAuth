package me.zhyd.oauth.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import me.zhyd.oauth.config.AuthConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class AuthStateTest {

    /**
     * step1 生成state: 预期创建一个新的state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV9yM3ll
     *
     * step2 重复生成state: 预期从bucket中返回一个可用的state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV9yM3ll
     *
     * step3 获取state: 预期获取上面生成的state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV9yM3ll
     *
     * step4 删除state: 预期删除掉上面创建的state...
     *
     * step5 重新获取state: 预期返回null...
     * null
     */
    @Test
    public void usage() {
        String source = "github";
        System.out.println("\nstep1 生成state: 预期创建一个新的state...");
        String state = AuthState.create(source);
        System.out.println(state);

        System.out.println("\nstep2 重复生成state: 预期从bucket中返回一个可用的state...");
        String recreateState = AuthState.create(source);
        System.out.println(recreateState);
        Assert.assertEquals(state, recreateState);

        System.out.println("\nstep3 获取state: 预期获取上面生成的state...");
        String stateByBucket = AuthState.get(source);
        System.out.println(stateByBucket);
        Assert.assertEquals(state, stateByBucket);

        System.out.println("\nstep4 删除state: 预期删除掉上面创建的state...");
        AuthState.delete(source);

        System.out.println("\nstep5 重新获取state: 预期返回null...");
        String deletedState = AuthState.get(source);
        System.out.println(deletedState);
        Assert.assertNull(deletedState);
    }

    /**
     * 通过随机字符串生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV9wdnAy
     *
     * 通过传入自定义的字符串生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV/ov5nmmK/kuIDkuKrlrZfnrKbkuLI=
     *
     * 通过传入数字生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV8xMTE=
     *
     * 通过传入日期生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV8xNTQ2MzE1OTMyMDAw
     *
     * 通过传入map生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV97InVzZXJUb2tlbiI6Inh4eHh4IiwidXNlcklkIjoxfQ==
     *
     * 通过传入List生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV9bInh4eHgiLCJ4eHh4eHh4eCJd
     *
     * 通过传入实体类生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV97ImNsaWVudElkIjoieHh4eHgiLCJjbGllbnRTZWNyZXQiOiJ4eHh4eCIsInVuaW9uSWQiOmZhbHNlfQ==
     */
    @Test
    public void create() {
        String source = "github";
        System.out.println("\n通过随机字符串生成state...");
        String state = AuthState.create(source);
        System.out.println(state);
        AuthState.delete(source);

        System.out.println("\n通过传入自定义的字符串生成state...");
        String stringBody = "这是一个字符串";
        String stringState = AuthState.create(source, stringBody);
        System.out.println(stringState);
        AuthState.delete(source);

        System.out.println("\n通过传入数字生成state...");
        Integer numberBody = 111;
        String numberState = AuthState.create(source, numberBody);
        System.out.println(numberState);
        AuthState.delete(source);

        System.out.println("\n通过传入日期生成state...");
        Date dateBody = DateUtil.parse("2019-01-01 12:12:12", DatePattern.NORM_DATETIME_PATTERN);
        String dateState = AuthState.create(source, dateBody);
        System.out.println(dateState);
        AuthState.delete(source);

        System.out.println("\n通过传入map生成state...");
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("userId", 1);
        mapBody.put("userToken", "xxxxx");
        String mapState = AuthState.create(source, mapBody);
        System.out.println(mapState);
        AuthState.delete(source);

        System.out.println("\n通过传入List生成state...");
        List<String> listBody = new ArrayList<>();
        listBody.add("xxxx");
        listBody.add("xxxxxxxx");
        String listState = AuthState.create(source, listBody);
        System.out.println(listState);
        AuthState.delete(source);

        System.out.println("\n通过传入实体类生成state...");
        AuthConfig entityBody = AuthConfig.builder()
                .clientId("xxxxx")
                .clientSecret("xxxxx")
                .build();
        String entityState = AuthState.create(source, entityBody);
        System.out.println(entityState);
        AuthState.delete(source);
    }

    /**
     * 通过随机字符串生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV9kaWNn
     * dicg
     *
     * 通过传入自定义的字符串生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV/ov5nmmK/kuIDkuKrlrZfnrKbkuLI=
     * 这是一个字符串
     *
     * 通过传入数字生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV8xMTE=
     * 111
     *
     * 通过传入日期生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV8xNTQ2MzE1OTMyMDAw
     * Tue Jan 01 12:12:12 CST 2019
     *
     * 通过传入map生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV97InVzZXJUb2tlbiI6Inh4eHh4IiwidXNlcklkIjoxfQ==
     * {userToken=xxxxx, userId=1}
     *
     * 通过传入List生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV9bInh4eHgiLCJ4eHh4eHh4eCJd
     * [xxxx, xxxxxxxx]
     *
     * 通过传入实体类生成state...
     * Z2l0aHViXzE5Mi4xNjguMTkuMV97ImNsaWVudElkIjoieHh4eHgiLCJjbGllbnRTZWNyZXQiOiJ4eHh4eCIsInVuaW9uSWQiOmZhbHNlfQ==
     * me.zhyd.oauth.config.AuthConfig@725bef66
     */
    @Test
    public void getBody() {
        String source = "github";
        System.out.println("\n通过随机字符串生成state...");
        String state = AuthState.create(source);
        System.out.println(state);
        String body = AuthState.getBody(source, state, String.class);
        System.out.println(body);
        AuthState.delete(source);

        System.out.println("\n通过传入自定义的字符串生成state...");
        String stringBody = "这是一个字符串";
        String stringState = AuthState.create(source, stringBody);
        System.out.println(stringState);
        stringBody = AuthState.getBody(source, stringState, String.class);
        System.out.println(stringBody);
        AuthState.delete(source);

        System.out.println("\n通过传入数字生成state...");
        Integer numberBody = 111;
        String numberState = AuthState.create(source, numberBody);
        System.out.println(numberState);
        numberBody = AuthState.getBody(source, numberState, Integer.class);
        System.out.println(numberBody);
        AuthState.delete(source);

        System.out.println("\n通过传入日期生成state...");
        Date dateBody = DateUtil.parse("2019-01-01 12:12:12", DatePattern.NORM_DATETIME_PATTERN);
        String dateState = AuthState.create(source, dateBody);
        System.out.println(dateState);
        dateBody = AuthState.getBody(source, dateState, Date.class);
        System.out.println(dateBody);
        AuthState.delete(source);

        System.out.println("\n通过传入map生成state...");
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("userId", 1);
        mapBody.put("userToken", "xxxxx");
        String mapState = AuthState.create(source, mapBody);
        System.out.println(mapState);
        mapBody = AuthState.getBody(source, mapState, Map.class);
        System.out.println(mapBody);
        AuthState.delete(source);

        System.out.println("\n通过传入List生成state...");
        List<String> listBody = new ArrayList<>();
        listBody.add("xxxx");
        listBody.add("xxxxxxxx");
        String listState = AuthState.create(source, listBody);
        System.out.println(listState);
        listBody = AuthState.getBody(source, listState, List.class);
        System.out.println(listBody);
        AuthState.delete(source);

        System.out.println("\n通过传入实体类生成state...");
        AuthConfig entityBody = AuthConfig.builder()
                .clientId("xxxxx")
                .clientSecret("xxxxx")
                .build();
        String entityState = AuthState.create(source, entityBody);
        System.out.println(entityState);
        entityBody = AuthState.getBody(source, entityState, AuthConfig.class);
        System.out.println(entityBody);
        AuthState.delete(source);
    }

    @Test
    public void getErrorStateBody() {
        String source = "github";
        String state = "1111111111111111111111111111111";
        String body = AuthState.getBody(source, state, String.class);
        System.out.println(body);
        AuthState.delete(source);
    }
}