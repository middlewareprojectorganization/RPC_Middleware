package com.xxy.listener;

/**
 * @Author: XXY
 * @Date: 2020/7/18 1:24
 */
public class ConfigChangeEvent {
    //配置变更内容
    private final String content;
    //配置变更类型 rpc配置，限流配置等配置变更
    private final ConfigChangeDesc configChangeDesc;

    //配置变更类型，增删查改
    private final ConfigChangeType configChangeType;

    public ConfigChangeEvent(String content, ConfigChangeType configChangeType, ConfigChangeDesc configChangeDesc){
        this.content = content;
        this.configChangeDesc = configChangeDesc;
        this.configChangeType = configChangeType;
    }

    public String getContent() {
        return content;
    }

    public ConfigChangeDesc getConfigChangeDesc() {
        return configChangeDesc;
    }

    public ConfigChangeType getConfigChangeType() {
        return configChangeType;
    }
}
