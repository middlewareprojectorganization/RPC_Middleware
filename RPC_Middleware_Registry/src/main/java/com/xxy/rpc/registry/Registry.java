package com.xxy.rpc.registry;

import java.util.Map;

/**
 * @Author: XXY
 * @Date: 2020/7/31 1:09
 */
public class Registry {
    private String host;
    private Integer port;
    private Map<String, Object> params;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
