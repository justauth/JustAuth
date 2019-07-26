package me.zhyd.oauth.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 其他测试方法
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 */
public class CustomTest {

    /**
     * 1000000: 23135ms
     * 100000: 3016ms
     * 10000: 328ms
     * 1000: 26ms
     */
    @Test
    public void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            callMethod();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

    }

    /**
     * 1000000: 19058ms
     * 100000: 2772ms
     * 10000: 323ms
     * 1000: 29ms
     */
    @Test
    public void test2() {
        long end = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            callMethod2();
        }
        long end2 = System.currentTimeMillis();
        System.out.println((end2 - end) + "ms");

    }

    public String callMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//        for (StackTraceElement stackTraceElement : stackTrace) {
//            System.out.println(stackTraceElement.getMethodName());
//        }
        return stackTrace[2].getMethodName();
    }

    public String callMethod2() {
        StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
//        for (StackTraceElement stackTraceElement : stackTrace) {
//            System.out.println(stackTraceElement.getMethodName());
//        }
        return stackTrace[2].getMethodName();
    }

    @Test
    public void jsonpath() {
        List<Map<String, Map<String, Object>>> list = new ArrayList<>();

        Map<String, Map<String, Object>> map = new HashMap<>();
        Map<String, Object> node = new HashMap<>();
        node.put("emailAddress", "xxxx");
        map.put("handle~", node);
        list.add(map);


        Map<String, Object> master = new HashMap<>();
//        master.put("elements", list);
        JSONObject emailObj = JSONObject.parseObject(JSON.toJSONString(master));
        Object object = JSONPath.eval(emailObj, "$['elements'][0]['handle~']['emailAddress']");
        System.out.println(object);
    }
}
