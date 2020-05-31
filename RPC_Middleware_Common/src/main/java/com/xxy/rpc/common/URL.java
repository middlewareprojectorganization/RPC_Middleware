package com.xxy.rpc.common;

import com.xxy.rpc.common.utils.CollectionUtils;
import com.xxy.rpc.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.xxy.rpc.common.constants.CommonConstants.METHODS_KEY;

/**
 * @Author: XXY
 * @Date: 2020/5/31 18:01
 */
public class URL implements Serializable {
    private final String username;

    private final String password;

    // by default, host to registry
    private final String host;

    // by default, port to registry
    private final int port;

    private final String path;

    private final Map<String, String> parameters;

    private final Map<String, Map<String, String>> methodParameters;
    protected URL() {
        this.username = null;
        this.password = null;
        this.host = null;
        this.port = 0;
        this.path = null;
        this.parameters = null;
        this.methodParameters = null;
    }

    public URL(String protocol, String host, int port) {
        this(protocol, null, null, host, port, null, (Map<String, String>) null);
    }

    public URL(String protocol, String host, int port, String[] pairs) { // varargs ... conflict with the following path argument, use array instead.
        this(protocol, null, null, host, port, null, CollectionUtils.toStringMap(pairs));
    }

    public URL(String protocol, String host, int port, Map<String, String> parameters) {
        this(protocol, null, null, host, port, null, parameters);
    }

    public URL(String protocol, String host, int port, String path) {
        this(protocol, null, null, host, port, path, (Map<String, String>) null);
    }

    public URL(String protocol, String host, int port, String path, String... pairs) {
        this(protocol, null, null, host, port, path, CollectionUtils.toStringMap(pairs));
    }

    public URL(String protocol, String host, int port, String path, Map<String, String> parameters) {
        this(protocol, null, null, host, port, path, parameters);
    }

    public URL(String protocol, String username, String password, String host, int port, String path) {
        this(protocol, username, password, host, port, path, (Map<String, String>) null);
    }

    public URL(String protocol, String username, String password, String host, int port, String path, String... pairs) {
        this(protocol, username, password, host, port, path, CollectionUtils.toStringMap(pairs));
    }

    public URL(String protocol,
               String username,
               String password,
               String host,
               int port,
               String path,
               Map<String, String> parameters) {
        this (protocol, username, password, host, port, path, parameters, toMethodParameters(parameters));
    }

    public URL(String protocol,
               String username,
               String password,
               String host,
               int port,
               String path,
               Map<String, String> parameters,
               Map<String, Map<String, String>> methodParameters) {
        if (StringUtils.isEmpty(username)
                && StringUtils.isNotEmpty(password)) {
            throw new IllegalArgumentException("Invalid url, password without username!");
        }
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = (port < 0 ? 0 : port);
        // trim the beginning "/"
        while (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        this.path = path;
        if (parameters == null) {
            parameters = new HashMap<>();
        } else {
            parameters = new HashMap<>(parameters);
        }
        this.parameters = Collections.unmodifiableMap(parameters);
        this.methodParameters = Collections.unmodifiableMap(methodParameters);
    }
    public static Map<String, Map<String, String>> toMethodParameters(Map<String, String> parameters) {
        Map<String, Map<String, String>> methodParameters = new HashMap<>();
        if (parameters != null) {
            String methodsString = parameters.get(METHODS_KEY);
            if (StringUtils.isNotEmpty(methodsString)) {
                String[] methods = methodsString.split(",");
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    String key = entry.getKey();
                    for (String method : methods) {
                        String methodPrefix = method + ".";
                        if (key.startsWith(methodPrefix)) {
                            String realKey = key.substring(methodPrefix.length());
                            URL.putMethodParameter(method, realKey, entry.getValue(), methodParameters);
                        }
                    }
                }
            } else {
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    String key = entry.getKey();
                    int methodSeparator = key.indexOf(".");
                    if (methodSeparator > 0) {
                        String method = key.substring(0, methodSeparator);
                        String realKey = key.substring(methodSeparator + 1);
                        URL.putMethodParameter(method, realKey, entry.getValue(), methodParameters);
                    }
                }
            }
        }
        return methodParameters;
    }
    public static void putMethodParameter(String method, String key, String value, Map<String, Map<String, String>> methodParameters) {
        Map<String, String> subParameter = methodParameters.computeIfAbsent(method, k -> new HashMap<>());
        subParameter.put(key, value);
    }
}
