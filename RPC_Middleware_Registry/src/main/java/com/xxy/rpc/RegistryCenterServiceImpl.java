package com.xxy.rpc;

import com.alibaba.fastjson.JSONObject;
import com.xxy.URL;
import com.xxy.rpc.registry.Registry;
import com.xxy.util.HttpAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XXY
 * @Date: 2020/7/31 1:12
 */
public class RegistryCenterServiceImpl implements RegistryCenterService{
    private final static Logger LOGGER = LoggerFactory.getLogger(RegistryCenterServiceImpl.class);
    private HttpAgent httpAgent;
    private HttpAgent.HttpResult result = null;
    public RegistryCenterServiceImpl(){
        httpAgent = HttpAgent.getInstance();
    }
    @Override
    public void registry(URL url) {
        try {
           result =  httpAgent.httpPost(url);
        }catch (Exception e){
            LOGGER.error("registry failed, caused by ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public URL getReferServiceInfo(String host, Integer port, String className) {
        URL url = new URL(host, port);
        Map<String, Object> param = new HashMap<>();
        param.put("path", "/service/queryReferService");
        param.put("className", className);
        url.setParam(param);
        try {
            HttpAgent.HttpResult httpResult = httpAgent.httpGet(url);
            return transHttpResultToURL(httpResult);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private URL transHttpResultToURL(HttpAgent.HttpResult httpResult) {
        URL url = (URL) JSONObject.parse(httpResult.content);
        return url;
    }
}
