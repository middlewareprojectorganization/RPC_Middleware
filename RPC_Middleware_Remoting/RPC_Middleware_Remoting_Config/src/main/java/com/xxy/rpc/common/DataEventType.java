package com.xxy.rpc.common;

import com.xxy.rpc.common.config.ConfigChangeType;

/**
 * @Author: XXY
 * @Date: 2020/7/14 8:01
 */
public class DataEventType {
    private final String content;
    private final ConfigChangeType configChangeType;

    public DataEventType(String content, ConfigChangeType configChangeType) {
        this.content = content;
        this.configChangeType = configChangeType;
    }

    public String getContent() {
        return content;
    }

    public ConfigChangeType getConfigChangeType() {
        return configChangeType;
    }
}
