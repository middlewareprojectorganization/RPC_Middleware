package com.xxy.rpc.configcenter;

import com.xxy.rpc.common.config.configcenter.ConfigurationListener;
import com.xxy.rpc.common.config.configcenter.DynamicConfiguration;

/**
 * @Author: XXY
 * @Date: 2020/5/31 22:26
 */
public class ConfigCenterDynamicConfiguration implements DynamicConfiguration {
    @Override
    public void addListener(String key, String group, ConfigurationListener listener) {

    }

    @Override
    public void removeListener(String key, String group, ConfigurationListener listener) {

    }

    @Override
    public String getConfig(String key, String group, long timeout) throws IllegalStateException {
        return null;
    }

    @Override
    public Object getInternalProperty(String key) {
        return null;
    }
}
