package com.www.http.kits.exercise.security.draft;

import com.www.http.kits.exercise.security.config.PostCfg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LambdaTest01 {

    @Autowired
    private PostCfg postCfg;



    @Test
    public void getMethod(){
        System.out.println(postCfg.getName());
    }

    @Test
    public void testChar(){

    }





    @Test
    public void test01(){
        Runnable runnable = () ->{
            System.out.println(1);
            System.out.println('w');
        };
        runnable.run();
    }

    @Test
    public void tes02(){
        Consumer<String> stringConsumer = string -> System.out.println(string);
        stringConsumer.accept("wanghognyan");
    }

    @Test
    public void test03(){
       Comparator<Integer> integerComparator = (x,y)->{
           System.out.println("函数式接口");
           int compare = Integer.compare(x, y);
           return compare;
       };
        System.out.println(integerComparator.compare(3, 1));
    }

    @Test
    public void test04(){
        buyCar("BMW",str-> System.out.println(str));
    }
    public void buyCar(String car ,Consumer<String> carConsumer ){
        carConsumer.accept(car);
    }

    @Test
    public void test05(){
        System.out.println(generateNum(190, () -> (int)( Math.random() * 100)));
    }
    public List<Integer> generateNum(Integer number , Supplier<Integer> supplier) {

        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i <number ; i++) {
            integerList.add(supplier.get());
        }
        return  integerList ;
    }

    @Test
    public void test06(){
        System.out.println(stringHandler("WangHongYan", str -> str.replace("hong", "lan")));
    }
    public String stringHandler(String object, Function<String,String> function){
       return function.apply(object);
    }

    @Test
    public void test07(){
        List<String> stringList = Arrays.asList("wang", "hong", "yan", "uu", "a", "sa");
        System.out.println(substring(stringList, str -> str.contains("g")));
    }
    public List<String> substring(List<String> list,Predicate<String> predicate){
        List<String> stringList = new ArrayList<>();
        for(String str : list){
            if(predicate.test(str)){
                stringList.add(str);
            }
        }
        return  stringList;
    }
}
