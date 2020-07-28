package com.xxy.rpc;

import com.xxy.config.ConfigCenterConfig;
import com.xxy.rpc.config.ReferenceConfig;
import com.xxy.store.ApplicationModel;
import com.xxy.store.ConfigManager;

/**
 * @Author: XXY
 * @Date: 2020/7/17 21:07
 */
public class RpcBootStrap {
    private static RpcBootStrap instance;
    private final ConfigManager configManager;
    public static synchronized RpcBootStrap getInstance(){
        if(instance == null){
            instance = new RpcBootStrap();
        }
        return instance;
    }
    private RpcBootStrap(){
        configManager = ApplicationModel.getConfigManager();
    }

    public void init(){
        //开启配置中心
        startConfigCenter();
        //加载刷新远程配置
        loadRemoteConfig();

    }

    private void loadRemoteConfig() {
    }

    private void startConfigCenter() {
        ConfigCenterConfig configCenterConfig = configManager.getConfigCenter();

    }

    public void start(){
        init();

    }
    private void referServices(){
        configManager.getReferences().forEach(rc -> {
            ReferenceConfig referenceConfig = (ReferenceConfig) rc;

        });
    }

    public void stop() {
    }
}
