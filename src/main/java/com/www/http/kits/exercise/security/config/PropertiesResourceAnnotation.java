package com.www.http.kits.exercise.security.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: WangHongYan
 * @Since: 2021/04/25 01:01:48
 */
@PropertySource(value = {"classpath:hsm/sansec.yml"},factory = YamlPropertySourceFactory.class)
@Data
@SpringBootConfiguration
@ConfigurationProperties(prefix = "city")
public class PropertiesResourceAnnotation {

//   @Value("${city.sansecName}")
//   @Value("${sansecName}")
   private String sansecName;


   //   @Value("${sansec}")
   private String sansec;

   }





