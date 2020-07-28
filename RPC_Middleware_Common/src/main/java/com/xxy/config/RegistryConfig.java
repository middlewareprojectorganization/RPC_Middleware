package com.xxy.config;

import com.xxy.URL;

/**
 * @Author: XXY
 * @Date: 2020/7/18 21:51
 */
public class RegistryConfig extends AbstractConfig {
    private String address;

    private Integer port;

    private String path;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public URL toUrl() {
        return null;
    }
}
