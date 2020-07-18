package com.xxy.configcenter;

/**
 * @Author: XXY
 * @Date: 2020/7/18 9:33
 */
public interface ConfigClient {
    String getConfig(String... var);

    void addListener(Listener listener);
}
