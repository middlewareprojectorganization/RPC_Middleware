package com.xxy.configcenter;

import com.xxy.URL;
import com.xxy.configcenter.server.ConfigListenerServer;
import com.xxy.util.HttpAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: XXY
 * @Date: 2020/7/18 10:14
 */
public class HttpConfigClient implements ConfigClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConfigClient.class);
    public final static Map<String, Listener> listeners = new ConcurrentHashMap<>();
    private final ConfigListenerServer server;
    private final HttpAgent agent;
    private final URL url;

    public HttpConfigClient(URL url){
        this.url = url;
        this.agent = HttpAgent.getInstance();
        server = new ConfigListenerServer(url);
        try {
            server.start();
        }catch (Exception e){
            LOGGER.error("server start fail,caused by", e);
            throw new RuntimeException(e);
        }

    }
    @Override
    public String getConfig(String... var) {
        try {
            return agent.httpGet(url).content;
        }catch (Exception e){
            LOGGER.error("get config error, caused by ", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addListener(Listener listener) {
        listeners.put(listener.getClass().getSimpleName(), listener);
    }





}
