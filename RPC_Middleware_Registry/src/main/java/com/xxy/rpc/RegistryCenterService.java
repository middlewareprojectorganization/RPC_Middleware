package com.xxy.rpc;

import com.xxy.URL;
import com.xxy.rpc.registry.Registry;

/**
 * @Author: XXY
 * @Date: 2020/7/31 1:13
 */
public interface RegistryCenterService {
    void registry(URL url);

    Registry getReferServiceInfo();
}
