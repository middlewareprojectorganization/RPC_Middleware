package com.xxy.config;

import com.xxy.URL;
import com.xxy.store.ApplicationModel;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * @Author: XXY
 * @Date: 2020/7/17 21:01
 */
public abstract class AbstractConfig {
    private static final String[] SUFFIXES = new String[]{"Config", "Bean", "ConfigBase"};
    private RegistryConfig registryConfig;

    public void setRegistryConfig(RegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }

    public RegistryConfig getRegistryConfig() {
        return registryConfig;
    }

    public void checkRegistry(){
        if(registryConfig == null){
            registryConfig = ApplicationModel.getConfigManager().getRegistry();
        }
    }
    public static String getTagName(Class<?> cls) {
        String tag = cls.getSimpleName();
        for (String suffix : SUFFIXES) {
            if (tag.endsWith(suffix)) {
                tag = tag.substring(0, tag.length() - suffix.length());
                break;
            }
        }
        return camelToSplitName(tag, "-");
    }

    public abstract URL toUrl();

    public static String camelToSplitName(String camelName, String split) {
        if (StringUtils.isEmpty(camelName)) {
            return camelName;
        }
        StringBuilder buf = null;
        for (int i = 0; i < camelName.length(); i++) {
            char ch = camelName.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                if (buf == null) {
                    buf = new StringBuilder();
                    if (i > 0) {
                        buf.append(camelName, 0, i);
                    }
                }
                if (i > 0) {
                    buf.append(split);
                }
                buf.append(Character.toLowerCase(ch));
            } else if (buf != null) {
                buf.append(ch);
            }
        }
        return buf == null ? camelName : buf.toString();
    }

    @PostConstruct
    public void addIntoConfigManager() {
        ApplicationModel.getConfigManager().addConfig(this);
    }
}
