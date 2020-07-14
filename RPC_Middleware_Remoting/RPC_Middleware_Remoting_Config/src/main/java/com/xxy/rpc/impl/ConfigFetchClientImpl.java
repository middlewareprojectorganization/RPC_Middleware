package com.xxy.rpc.impl;

import com.xxy.rpc.common.CommonResponse;
import com.xxy.rpc.common.ConfigFetchClient;
import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.logger.Logger;
import com.xxy.rpc.common.logger.LoggerFactory;
import com.xxy.rpc.common.utils.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @Author: XXY
 * @Date: 2020/7/14 9:06
 */
public class ConfigFetchClientImpl implements ConfigFetchClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFetchClientImpl.class);
    private final CloseableHttpClient client;

    private static final int OK_STATUS = 200;

    private final int timeoutMs = 3000;
    private final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(timeoutMs)
            .setConnectTimeout(timeoutMs)
            .setSocketTimeout(timeoutMs)
            .build();

    private final String consoleHost;
    private final int consolePort;

    public ConfigFetchClientImpl(URL url){
        if(url == null){
            LOGGER.error("the url can not be null");
            throw new RuntimeException("the url can not be null");
        }
        this.client = HttpClients.createDefault();
        consoleHost = url.getHost();
        consolePort = url.getPort();
    }

    @Override
    public CommonResponse getConfig() throws Exception{
        if(StringUtils.isEmpty(consoleHost)){
            LOGGER.info("the consoleHost is null");
            return null;
        }
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost(consoleHost).setPort(consolePort)
                .setPath("")
                .setParameter(" app", "app");
        HttpGet request = new HttpGet(uriBuilder.build());
        request.setConfig(requestConfig);
        // Send heartbeat request.
        CloseableHttpResponse response = client.execute(request);
        response.close();
        return null;
    }
}
