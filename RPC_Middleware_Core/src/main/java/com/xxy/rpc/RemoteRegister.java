package com.xxy.rpc;

import com.xxy.URL;

public interface RemoteRegister {
    /**
     * @param interfaceName 接口名
     * @param host
     */
    void register(String interfaceName, URL host);

    URL getURL(String interfaceName);
}
