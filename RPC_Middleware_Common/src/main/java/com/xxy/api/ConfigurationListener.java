package com.xxy.api;

import com.xxy.listener.ConfigChangeEvent;

/**
 * @Author: XXY
 * @Date: 2020/7/18 1:22
 */
public interface ConfigurationListener {
    void process(ConfigChangeEvent configChangeEvent);
}
