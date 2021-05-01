package com.www.http.kits.exercise.security.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: WangHongYan
 * @Since: 2021/04/07 20:55:32
 */
@Component
@Data
public abstract class PostCfg {

    @Value("${person.name}")
//    @Value("${fresh.length}")
    private String name;

    @Value("${hsm}")
//    @Value("${fresh.length}")
    private String hsm;

//    @Value("${}")
//    private String school;

    public abstract String path();






}
