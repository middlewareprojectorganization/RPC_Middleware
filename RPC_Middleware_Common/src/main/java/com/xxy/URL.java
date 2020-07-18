package com.xxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: XXY
 * @Date: 2020/7/18 9:39
 */
public class URL {
    private String host;
    private int port;

    private Map<String, Object> param = new ConcurrentHashMap<>();

    public URL(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public URL(String host, int port, Map<String, Object> param) {
        this.host = host;
        this.port = port;
        this.param = param;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}
