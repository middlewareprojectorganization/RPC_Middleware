package com.xxy.util;

import com.xxy.URL;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Author: XXY
 * @Date: 2020/7/18 12:38
 */
public class HttpAgent {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpAgent.class);
    private static HttpAgent agent;
    public static synchronized HttpAgent getInstance(){
        if(agent == null){
            agent = new HttpAgent();
        }
        return agent;
    }
    private static final int OK_STATUS = 200;

    private final int timeoutMs = 3000;
    private final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(timeoutMs)
            .setConnectTimeout(timeoutMs)
            .setSocketTimeout(timeoutMs)
            .build();

    private final CloseableHttpClient client;
    private HttpAgent(){
        client = HttpClients.createDefault();
    }
    public HttpResult httpGet(URL url) throws Exception{
        if(!checkUrl(url)){
            return null;
        }
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost(url.getHost()).setPort(url.getPort())
                .setPath(url.getParam().get("path").toString());
        url.getParam().forEach((k,v) -> {
            uriBuilder.setParameter(k, v.toString());
        });
        HttpGet request = new HttpGet(uriBuilder.build());
        request.setConfig(requestConfig);
        CloseableHttpResponse response = client.execute(request);
        response.close();
        int statusCode = response.getStatusLine().getStatusCode();

        if(statusCode == OK_STATUS){
            return  handlerResponse(response);
        }else if (clientErrorCode(statusCode) || serverErrorCode(statusCode)){
            LOGGER.error("http get failed to{}:{},http code:{}", statusCode);
            throw new RuntimeException("http get error");
        }
        return new HttpResult(statusCode, null);
    }
    public HttpResult httpPost(URL url) throws Exception {
        if(!checkUrl(url)){
            return null;
        }
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost(url.getHost()).setPort(url.getPort())
                .setPath(url.getParam().get("path").toString());
        HttpPost request = new HttpPost(uriBuilder.build());
        // 3. 创建参数队列（键值对列表）
        List<NameValuePair> paramPairs = new ArrayList<>();
        Map<String, Object> map = url.getParam();
        Set<String> keySet = url.getParam().keySet();
        for (String key : keySet) {
            Object val = map.get(key);
            paramPairs.add(new BasicNameValuePair(key, val.toString()));
        }
        // 4. 将参数设置到entity对象中
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramPairs, "UTF-8");
        // 5. 将entity对象设置到httppost对象中
        request.setEntity(entity);
        request.setConfig(requestConfig);
        // 6. 发送请求并回去响应
        CloseableHttpResponse resp = client.execute(request);
        resp.close();
        int statusCode = resp.getStatusLine().getStatusCode();
        if(statusCode == OK_STATUS){
            return handlerResponse(resp);
        }
        return new HttpResult(statusCode, null);
    }

    private HttpResult handlerResponse(CloseableHttpResponse response) throws Exception{
        HttpEntity entity = response.getEntity();
        return new HttpResult(OK_STATUS, response.getAllHeaders(), EntityUtils.toString(entity));
    }

    private boolean checkUrl(URL url){
        if(url == null || StringUtils.isEmpty(url.getHost())){
            return false;
        }
        return true;

    }

    private boolean clientErrorCode(int code) {
        return code > 399 && code < 500;
    }

    private boolean serverErrorCode(int code) {
        return code > 499 && code < 600;
    }

    public static class HttpResult {
        public final int code;
        public final  Header[] headers;
        public final String content;


        public HttpResult(int code, String content) {
            this.code = code;
            this.headers = null;
            this.content = content;
        }

        public HttpResult(int code, Header[] headers, String content) {
            this.code = code;
            this.headers = headers;
            this.content = content;
        }
    }
}
