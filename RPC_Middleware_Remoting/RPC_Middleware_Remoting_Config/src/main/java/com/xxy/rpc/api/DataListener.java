package com.xxy.rpc.api;

/**
 * @Author: XXY
 * @Date: 2020/7/1 8:57
 */
public interface DataListener {
    void dataChanged(String key, Object value, EventType eventType);
}
