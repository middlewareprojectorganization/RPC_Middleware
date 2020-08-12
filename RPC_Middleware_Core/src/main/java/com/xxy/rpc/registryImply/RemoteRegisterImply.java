package com.xxy.rpc.registryImply;

import com.xxy.URL;
import com.xxy.rpc.RemoteRegister;

import java.util.*;

public class RemoteRegisterImply implements RemoteRegister {
    private Map<String, List<URL>> registerMap=new HashMap<>(1024);

    @Override
    public void register(String interfaceName, URL host) {
        if (registerMap.containsKey(interfaceName)) {
            List<URL> urls = registerMap.get(interfaceName);
             urls.add(host);
        }
        else {
            LinkedList<URL> urls = new LinkedList<>();
            urls.add(host);
            registerMap.put(interfaceName,urls);
        }
        }


    @Override
    public URL getURL(String interfaceName) {
        List<URL> list = registerMap.get(interfaceName);
        Random random = new Random();
        int i = random.nextInt(list.size());
        return list.get(i);
    }
}
