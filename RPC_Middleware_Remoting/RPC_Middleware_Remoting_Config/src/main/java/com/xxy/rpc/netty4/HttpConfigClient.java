package com.xxy.rpc.netty4;

import com.xxy.rpc.api.response.CommandResponse;
import com.xxy.rpc.api.tansport.ConfigClient;
import com.xxy.rpc.api.tansport.config.ConfigClientConfig;
import com.xxy.rpc.common.URL;
import com.xxy.rpc.common.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: XXY
 * @Date: 2020/6/3 22:52
 */
public class HttpConfigClient implements ConfigClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigClient.class);
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
    private final URL url;

    public HttpConfigClient(URL url) {
        this.consoleHost = url.getHost();
        this.consolePort = url.getPort();
        this.url = url;
        this.client = HttpClients.createDefault();
    }
    @Override
    public CommandResponse getConfig() throws Exception {
        if (StringUtils.isEmpty(consoleHost)) {
            return null;
        }
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost(consoleHost).setPort(consolePort)
                .setPath(ConfigClientConfig.CONFIG_FETCH_PAHT)
                .setParameter("app", url.getParameter("app"));
        HttpGet request = new HttpGet(uriBuilder.build());
        request.setConfig(requestConfig);
        // Send heartbeat request.
        CloseableHttpResponse response = client.execute(request);
        response.close();
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == OK_STATUS) {
            return CommandResponse.ofSuccess(EntityUtils.toString(response.getEntity()));
        } else if (clientErrorCode(statusCode) || serverErrorCode(statusCode)) {
            LOGGER.warn("[HttpHeartbeatSender] Failed to send heartbeat to "
                    + consoleHost + ":" + consolePort + ", http status code: " + statusCode);
        }

        return CommandResponse.ofFailure(new RuntimeException());
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
