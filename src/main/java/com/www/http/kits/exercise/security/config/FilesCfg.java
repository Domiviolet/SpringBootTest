package com.www.http.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Author: WangHongYan
 * @Since: 2021/01/23 21:31:17
 */
@ConfigurationProperties("configfiles")
@Data
@Configuration
public class FilesCfg {

    private HashMap<String, String> jntaConfigFiles;

}
