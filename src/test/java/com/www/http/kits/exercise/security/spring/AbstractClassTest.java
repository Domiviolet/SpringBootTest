package com.www.http.kits.exercise.security.spring;

import com.www.http.kits.exercise.security.config.PropertiesResourceAnnotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: WangHongYan
 * @Since: 2021/04/24 23:28:23
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractClassTest {

//   @Autowired
//   private PostConfig postConfig;

   @Autowired
   private PropertiesResourceAnnotation propertiesResourceAnnotation;

   @Test
   public void superClass(){
//      System.out.println(postConfig.getHsm());
//      System.out.println(postConfig.getInteger());
//      System.out.println(propertiesResourceAnnotation.getSansec());
//      System.out.println(propertiesResourceAnnotation.getSansecName());

      System.out.println(propertiesResourceAnnotation.getSansecName());

   }




   @Test
   public void test02(){
//      postConfig.setSchool("FuDan");
//      System.out.println(postConfig.getSchool());
//
//      System.out.println(postConfig.path());
   }







}
