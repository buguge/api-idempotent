package com.sboot.controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TockenUtil {
    static Map<String, String> ticketMap = new ConcurrentHashMap<String, String>();

    static String getToken() {
        String s = UUID.randomUUID().toString().replace("-", "");
        ticketMap.put(s, s);
        return s;
    }

    synchronized static boolean existToken(String token) {
        if (ticketMap.containsKey(token)) {
            ticketMap.remove(token);
            return true;
        }
        return false;
    }
}
