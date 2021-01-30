package com.www.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WangHongYan
 * @Since: 2021/01/24 00:06:09
 */
@Configuration
public class Config {


    @Autowired
    private FilesCfg filesCfg;

    @Bean("aa")
    public HashMap<String, HsmSigner> getMap() throws Exception {
        HashMap<String, HsmSigner> getMap = new HashMap<>();
        for (Map.Entry<String, String> entry : filesCfg.getJntaConfigFiles().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            HsmSigner hsmSigner = new HsmSigner(key, value);
            getMap.put(key, hsmSigner);
        }
        return getMap;

    }
}
