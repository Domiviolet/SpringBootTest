package com.www.http.kits.exercise.security.AnnotaitonTest;

import com.www.http.kits.exercise.security.config.PostCfg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *  @Author: WangHongYan
 * @Since: 2021/04/07 20:45:56
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PostConstructTest {

    @Autowired
    private PostCfg postCfg;




    @Test
    public void test01() {

        System.out.println(postCfg.getName());
    }

    @Test
    public void test02(){
        System.out.println(postCfg.getHsm());

    }
}
