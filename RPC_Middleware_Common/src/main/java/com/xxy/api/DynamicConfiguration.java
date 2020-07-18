package com.xxy.api;

/**
 * @Author: XXY
 * @Date: 2020/7/18 8:08
 */
public interface DynamicConfiguration {
    String getConfig();

    void addListener(ConfigurationListener listener);
}
