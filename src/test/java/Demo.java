import com.alibaba.fastjson.TypeReference;
import com.www.security.config.AnimalsCfg;
import com.www.security.config.FilesCfg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: WangHongYan
 * @Since: 2020/11/23 21:26:58
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Demo {

    private HashMap<String, AnimalsCfg> map;


    @Autowired
    private FilesCfg filesCfg;


    @Test
    public void test2(){

    }


    @Test
    public void test1(){
        HashMap<String, AnimalsCfg> map = new HashMap<>();

        TypeReference typeReference = new TypeReference<HashMap<String, AnimalsCfg>>(){} ;


        List<Object> objects = Arrays.asList();



    }

}
