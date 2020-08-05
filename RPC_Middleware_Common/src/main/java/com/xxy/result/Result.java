package com.xxy.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XXY
 * @Date: 2020/8/2 22:37
 */
public class Result {
    private Object result;
    private Map<String, String> attachments = new HashMap();

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }
}
