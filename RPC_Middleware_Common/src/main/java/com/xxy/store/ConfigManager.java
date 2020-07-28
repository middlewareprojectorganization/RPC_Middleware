package com.xxy.store;

import com.xxy.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.xxy.config.AbstractConfig.getTagName;
import static java.util.Collections.emptyMap;

/**
 * @Author: XXY
 * @Date: 2020/7/18 21:02
 */
public class ConfigManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigManager.class);
    private final Map<String, Map<String, AbstractConfig>> configsCache = new HashMap<>();
    public void addConfigCenter(ConfigCenterConfig configCenterConfig){
        addConfig(configCenterConfig);
    }
    public void addRegistryConfig(RegistryConfig registryConfig){
        addConfig(registryConfig);
    }
    public void addService(ServiceConfigBase serviceConfig){
        addConfig(serviceConfig);
    }
    public void addReference(ReferenceConfigBase referenceConfig){
        addConfig(referenceConfig);
    }
    public Collection<ReferenceConfigBase> getReferences(){
        return getConfigs(getTagName(ReferenceConfigBase.class));
    }

    public RegistryConfig getRegistry(){
        return getConfig(getTagName(RegistryConfig.class));
    }

    protected  <C extends AbstractConfig> Collection<C> getConfigs(String configType){
        Map<String, C> map = (Map)configsCache.getOrDefault(configType, emptyMap());
        return map.values();
    }
    public void addConfig(AbstractConfig config){
        Map<String, AbstractConfig> configsMap = configsCache.computeIfAbsent(getTagName(config.getClass()), type -> new HashMap<>());
        configsMap.put(config.getClass().getName(), config);
    }
    public ConfigCenterConfig getConfigCenter(){
        return getConfig(getTagName(ConfigCenterConfig.class));
    }
    protected <C extends AbstractConfig> C getConfig(String configType){
        Map<String, C> configsMap = (Map) configsCache.getOrDefault(configType, emptyMap());
        int size = configsMap.size();
        if (size < 1) {
//                throw new IllegalStateException("No such " + configType.getName() + " is found");
            return null;
        } else if (size > 1) {
            LOGGER.warn("Expected single matching of " + configType + ", but found " + size + " instances, will randomly pick the first one.");
        }

        return configsMap.values().iterator().next();
    }
}
