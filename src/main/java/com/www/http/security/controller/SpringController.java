package com.www.http.security.controller;

import com.www.http.security.config.AnimalsCfg;
import com.www.http.security.config.AnimalsConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
public class SpringController {

    @Resource
    private AnimalsConfig animalsConfig;

//    @Resource
//    private HashMap<String, HsmSigner> aa;


    @GetMapping("/1")
    List<String> getList(){
        List<String> list = Arrays.asList("a", "b", "c");
        Collections.sort(list);
        return list;

    }

//    @GetMapping("/2")
//    HashMap<String, AnimalsCfg> getStr(){
//        HashMap<String, AnimalsCfg> map = animalsConfig.getMap();
//        return  map;
//    }

    @GetMapping("/3")
    AnimalsCfg getStr1(){
        HashMap<String, AnimalsCfg> map = animalsConfig.getMap();
        AnimalsCfg dog = map.get("dog");
        return dog;
    }

}
