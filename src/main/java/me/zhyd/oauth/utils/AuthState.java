package me.zhyd.oauth.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.ResponseStatus;

import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;

/**
 * state工具，负责创建、获取和删除state
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @since 1.8
 */
@Slf4j
public class AuthState {

    /**
     * 空字符串
     */
    private static final String EMPTY_STR = "";

    /**
     * state存储器
     */
    private static ConcurrentHashMap<String, String> stateBucket = new ConcurrentHashMap<>();

    /**
     * 生成随机的state
     *
     * @param source oauth平台
     * @return state
     */
    public static String create(String source) {
        return create(source, RandomUtil.randomString(4));
    }

    /**
     * 创建state
     *
     * @param source oauth平台
     * @param body   希望加密到state的消息体
     * @return state
     */
    public static String create(String source, Object body) {
        return create(source, JSON.toJSONString(body));
    }

    /**
     * 创建state
     *
     * @param source oauth平台
     * @param body   希望加密到state的消息体
     * @return state
     */
    public static String create(String source, String body) {
        String currentIp = getCurrentIp();
        String simpleKey = ((source + currentIp));
        String key = Base64.encode(simpleKey.getBytes(Charset.forName("UTF-8")));
        log.debug("Create the state: ip={}, platform={}, simpleKey={}, key={}, body={}", currentIp, source, simpleKey, key, body);

        if (stateBucket.containsKey(key)) {
            log.debug("Get from bucket: {}", stateBucket.get(key));
            return stateBucket.get(key);
        }

        String simpleState = source + "_" + currentIp + "_" + body;
        String state = Base64.encode(simpleState.getBytes(Charset.forName("UTF-8")));
        log.debug("Create a new state: {}", state, simpleState);
        stateBucket.put(key, state);
        return state;
    }

    /**
     * 获取state
     *
     * @param source oauth平台
     * @return state
     */
    public static String get(String source) {
        String currentIp = getCurrentIp();
        String simpleKey = ((source + currentIp));
        String key = Base64.encode(simpleKey.getBytes(Charset.forName("UTF-8")));
        log.debug("Get state by the key[{}], current ip[{}]", key, currentIp);
        return stateBucket.get(key);
    }

    /**
     * 获取state中保存的body内容
     *
     * @param source oauth平台
     * @param state  加密后的state
     * @param clazz  body的实际类型
     * @param <T>    需要转换的具体的class类型
     * @return state
     */
    public static <T> T getBody(String source, String state, Class<T> clazz) {
        if (StringUtils.isEmpty(state) || null == clazz) {
            return null;
        }
        log.debug("Get body from the state[{}] of the {} and convert it to {}", state, source, clazz.toString());
        String currentIp = getCurrentIp();
        String decodedState = Base64.decodeStr(state);
        log.debug("The decoded state is [{}]", decodedState);
        if (!decodedState.startsWith(source)) {
            return null;
        }
        String noneSourceState = decodedState.substring(source.length() + 1);
        if (!noneSourceState.startsWith(currentIp)) {
            // ip不相同，可能为非法的请求
            throw new AuthException(ResponseStatus.ILLEGAL_REQUEST);
        }
        String body = noneSourceState.substring(currentIp.length() + 1);
        log.debug("body is [{}]", body);
        if (clazz == String.class) {
            return (T) body;
        }
        if (clazz == Integer.class) {
            return (T) Integer.valueOf(Integer.parseInt(body));
        }
        if (clazz == Long.class) {
            return (T) Long.valueOf(Long.parseLong(body));
        }
        if (clazz == Short.class) {
            return (T) Short.valueOf(Short.parseShort(body));
        }
        if (clazz == Double.class) {
            return (T) Double.valueOf(Double.parseDouble(body));
        }
        if (clazz == Float.class) {
            return (T) Float.valueOf(Float.parseFloat(body));
        }
        if (clazz == Boolean.class) {
            return (T) Boolean.valueOf(Boolean.parseBoolean(body));
        }
        if (clazz == Byte.class) {
            return (T) Byte.valueOf(Byte.parseByte(body));
        }
        return JSON.parseObject(body, clazz);
    }

    /**
     * 登录成功后，清除state
     *
     * @param source oauth平台
     */
    public static void delete(String source) {
        String currentIp = getCurrentIp();

        String simpleKey = ((source + currentIp));
        String key = Base64.encode(simpleKey.getBytes(Charset.forName("UTF-8")));
        log.debug("Delete used state[{}] by the key[{}], current ip[{}]", stateBucket.get(key), key, currentIp);
        stateBucket.remove(key);
    }

    private static String getCurrentIp() {
        String currentIp = IpUtils.getIp();
        return StringUtils.isEmpty(currentIp) ? EMPTY_STR : currentIp;
    }
}
