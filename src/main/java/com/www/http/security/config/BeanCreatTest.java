package com.www.http.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * SpringBoot 中 Bean 的测试
 * @Author: WangHongYan
 * @Since: 2021/01/31 14:36:32
 */
@Configuration
public class BeanCreatTest {

    @Bean("hashMap_1")
    public HashMap<Integer, Integer> getHashMap(){
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 2);
        hashMap.put(3, 4);
        hashMap.put(5, 6);
        return hashMap;
    }

}
