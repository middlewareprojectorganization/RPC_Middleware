package com.xxy.rpc.rpc.model;

import com.xxy.rpc.common.config.Environment;
import com.xxy.rpc.common.context.FrameworkExt;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.common.utils.ConcurrentHashSet;
import com.xxy.rpc.config.context.ConfigManager;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: XXY
 * @Date: 2020/6/23 8:53
 */
public class ApplicationModel {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ApplicationModel.class);
    public static final String NAME = "application";
    private static final Map<String, FrameworkExt> exts = new ConcurrentHashMap<>();
    static {
        exts.put(ConfigManager.NAME, new ConfigManager());
        exts.put(Environment.NAME, new Environment());
    }
    public static void iniFrameworkExts() {
        for (FrameworkExt ext : exts.values()) {
            ext.initialize();
        }
    }

    public static ConfigManager getConfigManager() {
        return (ConfigManager) exts.get(ConfigManager.NAME);
    }
}
