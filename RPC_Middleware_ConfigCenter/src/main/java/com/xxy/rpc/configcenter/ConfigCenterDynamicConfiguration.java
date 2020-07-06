package com.xxy.rpc.configcenter;

import com.xxy.rpc.api.response.CommandResponse;
import com.xxy.rpc.api.tansport.ConfigClient;
import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.config.configcenter.ConfigurationListener;
import com.xxy.rpc.common.config.configcenter.DynamicConfiguration;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.netty4.HttpConfigClient;

/**
 * @Author: XXY
 * @Date: 2020/5/31 22:26
 */
public class ConfigCenterDynamicConfiguration implements DynamicConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigCenterDynamicConfiguration.class);
    private ConfigClient configClient;
    public ConfigCenterDynamicConfiguration(URL url){
        configClient = new HttpConfigClient(url);
    }
    @Override
    public void addListener(String key, String group, ConfigurationListener listener) {

    }

    @Override
    public void removeListener(String key, String group, ConfigurationListener listener) {

    }

    @Override
    public String getConfig(String key, String group, long timeout) throws IllegalStateException {
        CommandResponse config;
        try {
            config = configClient.getConfig();
        }catch (Exception e){
            LOGGER.error("pull config failed,caused by:", e);
            throw new IllegalStateException(e);
        }
        return config.getResult().toString();
    }

    @Override
    public Object getInternalProperty(String key) {
        return null;
    }
}
