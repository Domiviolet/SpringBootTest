package com.www.http.kits.exercise.security.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.List;

/**
 * @Author: WangHongYan
 * @Since: 2021/04/25 02:16:57
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

   @Override
   public PropertySource<?> createPropertySource(String s, EncodedResource encodedResource) throws IOException {
      YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
      List<PropertySource<?>> load = loader.load(s, encodedResource.getResource());
      if (load!=null && !load.isEmpty()) {
         return load.get(0);
      }
      return null;
   }
}
