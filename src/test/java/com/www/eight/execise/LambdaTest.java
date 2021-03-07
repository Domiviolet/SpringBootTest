package com.www.eight.execise;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class LambdaTest {

    @Test
    public void test01(){
        Comparator<Integer> comparator = (x,y)->Integer.compare(x,y);
        System.out.println(comparator.compare(10, 100));
    }

    @Test
    public void test02(){
        Comparator<Integer> comparator =(x,y)->{
            System.out.println("wang");
            return Integer.compare(x,y);
        };
        System.out.println(comparator.compare(1, 2));
    }

    /**
     * Consumer接口,只有一个accept()方法,无返回值
     */

    @Test
    public void test03() {
        buyHouse("楼房",house-> System.out.println(house));
    }
    public void buyHouse(String house,Consumer<String> consumer){
        consumer.accept(house);
    }

    @Test
    public void test04(){
        List<Integer> integerList = getNumList(12,()->(int)(Math.random()*100));
        for(Integer integer : integerList){
            System.out.println(integer);
        }
    }
    public List<Integer> getNumList(Integer num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        //依次把supplier获得的值提供给list集合,这里的for循环,表示进行了多少次
        for (int i = 0 ; i < num; i++) {
            Integer integer1 = supplier.get();
            list.add(integer1);
        }
        return list ;
    }

    @Test
    public void test05(){
        String string = stringHandler(" wanghongyan ",str -> str.trim());
        System.out.println(string);
    }
    public String stringHandler(String string, Function<String,String> function) {
        return function.apply(string);
    }

    @Test
    public void test06(){
        List<String> stringList1 = Arrays.asList("wanggg", "nn", "rrrrr","d","toy");
        List<String> list = filterString(stringList1, s -> s.length() > 3);
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    public List<String> filterString(List<String> list, Predicate<String> predicate){
        List<String> stringList = new ArrayList<>();
        for(String string: list){
            if(predicate.test(string)){
                stringList.add(string);
            }
        }
        return  stringList ;
    }

}
