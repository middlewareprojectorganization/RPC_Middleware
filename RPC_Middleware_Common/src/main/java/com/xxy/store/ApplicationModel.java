package com.xxy.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: XXY
 * @Date: 2020/7/17 21:04
 */
public class ApplicationModel {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ApplicationModel.class);

    private static final ConfigManager configManager;
    private static final ServiceRepository serviceRepository;

    static {
        configManager = new ConfigManager();
        serviceRepository = new ServiceRepository();
    }

    public static ConfigManager getConfigManager(){
        return configManager;
    }
    public static ServiceRepository getServiceRepository(){
        return serviceRepository;
    }
}
