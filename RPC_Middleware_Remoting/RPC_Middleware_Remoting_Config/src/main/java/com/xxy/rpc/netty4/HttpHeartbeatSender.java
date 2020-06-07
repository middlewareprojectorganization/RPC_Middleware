package com.xxy.rpc.netty4;

import com.xxy.rpc.api.tansport.HeartbeatSender;
import com.xxy.rpc.common.utils.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: XXY
 * @Date: 2020/6/3 22:52
 */
public class HttpHeartbeatSender implements HeartbeatSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatSender.class);
    private final CloseableHttpClient client;


    private static final int OK_STATUS = 200;

    private final int timeoutMs = 3000;
    private final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(timeoutMs)
            .setConnectTimeout(timeoutMs)
            .setSocketTimeout(timeoutMs)
            .build();

    public HttpHeartbeatSender() {
        this.client = HttpClients.createDefault();
    }
    @Override
    public boolean sendHeartbeat(String consoleHost) throws Exception {
        if (StringUtils.isEmpty(consoleHost)) {
            return false;
        }
        String[] split = StringUtils.split(consoleHost, ':');
        String ip = split[0];
        int port = Integer.valueOf(split[1]);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost(consoleHost).setPort(port)
                .setPath("/machine/get");
        HttpGet request = new HttpGet(uriBuilder.build());
        request.setConfig(requestConfig);
        // Send heartbeat request.
        CloseableHttpResponse response = client.execute(request);
        response.close();
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == OK_STATUS) {
            return true;
        } else if (clientErrorCode(statusCode) || serverErrorCode(statusCode)) {
            LOGGER.warn("[HttpHeartbeatSender] Failed to send heartbeat to "
                    + consoleHost + ":" + port + ", http status code: " + statusCode);
        }

        return false;
    }

    @Override
    public long intervalMs() {
        return 5000;
    }

    private boolean clientErrorCode(int code) {
        return code > 399 && code < 500;
    }

    private boolean serverErrorCode(int code) {
        return code > 499 && code < 600;
    }
}
