package com.concurrent.study.study13;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王文涛
 * @date 2025/3/17
 * @description
 **/
public class ThreadLocalUtil {

    private static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    public static void setUserId(String userId) {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put("userId", userId);
    }

    public static String getUserId() {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            return null;
        }
        return map.get("userId");
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void setInstId(String instId) {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put("instId", instId);
    }

    public static String getInstId() {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            return null;
        }
        return map.get("instId");
    }
}
