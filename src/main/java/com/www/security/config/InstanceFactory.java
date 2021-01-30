package com.www.security.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WangHongYan
 * @Since: 2021/01/23 21:41:53
 */
public class InstanceFactory implements InitializingBean {


    HashMap<String, HsmSigner> getMap = new HashMap<>();
    @Autowired
    private FilesCfg filesCfg;


    void initInstance() throws Exception {
        for (Map.Entry<String, String> entry : filesCfg.getJntaConfigFiles().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            HsmSigner hsmSigner = new HsmSigner(key, value);
            getMap.put(key,hsmSigner);

        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            initInstance();
        } catch (Exception e) {
            throw new Exception("s");
        }
    }
}
