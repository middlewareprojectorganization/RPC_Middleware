package com.xxy.configcenter;

import com.xxy.api.ConfigurationListener;

/**
 * @Author: XXY
 * @Date: 2020/7/18 9:36
 */
public interface Listener {
    void receiveConfigInfo(String content);

    void addListener(ConfigurationListener configurationListener);
}
