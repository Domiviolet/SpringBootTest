package com.www.http.security.tools;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 读取指定路径下的文档内容
 * @Author: WangHongYan
 * @Since: 2021/01/30 23:57:24
 */
@Component
public class ReadPathContent {


    public String getFileContent(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
    }


}
