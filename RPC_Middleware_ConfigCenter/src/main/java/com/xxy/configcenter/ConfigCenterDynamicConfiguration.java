package com.xxy.configcenter;

import com.xxy.URL;
import com.xxy.api.ConfigurationListener;
import com.xxy.api.DynamicConfiguration;
import com.xxy.configcenter.server.ConfigListenerServer;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: XXY
 * @Date: 2020/7/18 9:23
 */
public class ConfigCenterDynamicConfiguration implements DynamicConfiguration {

    private final Listener configCenterListener;

    public ConfigCenterDynamicConfiguration(URL url){
        configCenterListener = new ConfigCenterListener();
    }

    @Override
    public String getConfig() {
        return null;
    }

    @Override
    public void addListener(ConfigurationListener listener) {
        configCenterListener.addListener(listener);
    }

    public class ConfigCenterListener implements Listener{
        private final Set<ConfigurationListener> listeners = new CopyOnWriteArraySet<>();


        @Override
        public void receiveConfigInfo(String content) {

        }
        public void addListener(ConfigurationListener configurationListener) {

            this.listeners.add(configurationListener);
        }

    }
}
