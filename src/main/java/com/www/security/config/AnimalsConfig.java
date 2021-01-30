package com.www.security.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: WangHongYan
 * @Since: 2020/11/23 21:45:45
 */
@Component(value = "animals.config")
@Data
public class AnimalsConfig implements InitializingBean {


   private static final Charset charset = StandardCharsets.UTF_8;

   //指定需要转换的 JSON 对象的类型
    private static final TypeReference<HashMap<String, AnimalsCfg>> typeReference = new TypeReference<HashMap<String, AnimalsCfg>>() {
    };

    //把转换后的对象放入到 map 集合中
    private HashMap<String, AnimalsCfg> map = new HashMap<>();


    @Value("${animals.config}")
    private String jsonFileName;

    /**
     * 启动时加载文件
     * @throws IOException io 异常
     */
    private void checkFile() throws IOException {
        load(new File(jsonFileName));
    }

    /**
     * 把json 对象放到预设的 map 集合中
     * @param file 配置文件
     * @throws IOException io 异常
     */
    private void load(File file) throws IOException {
        Map<String, AnimalsCfg> mapper = doJason(file);
        for (Map.Entry<String, AnimalsCfg> entry : mapper.entrySet()) {
            String key = entry.getKey();
            AnimalsCfg value = entry.getValue();
            map.put(key, value);
        }
    }

    /**
     * 把配置文件的 json 格式转换为 json 对象
     * @param file 配置文件
     * @return  处理结果
     * @throws IOException io 异常
     */
    private Map<String, AnimalsCfg> doJason(File file) throws IOException {

        try(InputStream in = new FileInputStream(file)){
            return JSON.parseObject(IOUtils.toString(in, charset), typeReference);
        }catch (IOException e){
            throw new IOException("IO exception", e);
        }catch (JSONException e){
            throw new JSONException("Json format is wrong", e.getCause());


        }
    }



//项目启动时加载该配置文件
    @Override
    public void afterPropertiesSet() throws Exception {
        checkFile();
    }


}
