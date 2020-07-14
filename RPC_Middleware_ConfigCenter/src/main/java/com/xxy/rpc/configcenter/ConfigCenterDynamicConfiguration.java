package com.xxy.rpc.configcenter;

import com.xxy.rpc.common.*;
import com.xxy.rpc.common.config.configcenter.ConfigurationListener;
import com.xxy.rpc.common.config.configcenter.DynamicConfiguration;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.impl.ConfigFetchClientImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XXY
 * @Date: 2020/5/31 22:26
 */
public class ConfigCenterDynamicConfiguration implements DynamicConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigCenterDynamicConfiguration.class);
    private ConfigFetchClient configClient;
    private List<ConfigurationListener> listeners = new ArrayList<>();
    public ConfigCenterDynamicConfiguration(URL url){
        configClient = new ConfigFetchClientImpl(url);
    }
    @Override
    public void addListener(String key, String group, ConfigurationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(String key, String group, ConfigurationListener listener) {
        listeners.remove(listener);
    }

    @Override
    public String getConfig(String key, String group, long timeout) throws IllegalStateException {
        CommonResponse config;
        try {
            config = configClient.getConfig();
        }catch (Exception e){
            LOGGER.error("pull config failed,caused by:", e);
            throw new IllegalStateException(e);
        }
        return config.getResult().toString();
    }

    public final class ConfigListener implements DataListener {

        @Override
        public void dataChanged(DataEventType dataEventType) {
            listeners.forEach(listener -> listener.process(null));
        }
    }


}
