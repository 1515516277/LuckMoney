package com.practice.design.redPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DrowCountFactory {
    private static Map<String, DrowCountStrategy> map = new ConcurrentHashMap();

    public static DrowCountStrategy getDrowCountStrategy(String type) {
        return map.get(type);
    }

    public static void registryCount(String type, DrowCountStrategy drowCountStrategy) {
        map.put(type, drowCountStrategy);
    }
}
