package com.www.security;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.www.security.config.AnimalsCfg;
import com.www.security.config.FilesCfg;
import com.www.security.tools.ReadPathContent;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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


    @Autowired
    private ReadPathContent readPathContent;


    @Autowired
    private HashMap<Integer, Integer> hashMap_1;


    @Test
    public void testStream(){
        Stream<String> stream = Stream.of("1", "2", "3");
        Set<String> collect = stream.collect(Collectors.toSet());
        System.out.println(collect);

    }

    @Test
    public void test04(){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 3);
        map.put(2, 3);
        map.put(3, 3);
        map.put(4, 3);
        map.put(5, 3);

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);
        System.out.println(map.keySet().containsAll(arrayList1));


        ArrayList<Integer> arrayList2 = new ArrayList<>();


//        ArrayList<Integer> set1 = new ArrayList<>();
////        Set<Integer> set1 = new HashSet<>();
//        set1.add(1);
//        set1.add(2);
//        set1.add(3);
//        set1.add(4);
////        arrayList1.add(5);
//
////        ArrayList<Integer> arrayList2 = new ArrayList<>();
//        Set<Integer> set2 = new HashSet<>();
//        set2.add(3);
//        set2.add(4);
//
//        System.out.println(map.keySet().containsAll(set1));
//        System.out.println(map.keySet().containsAll(set2));

//        List<Integer> collect = map.keySet().stream().collect(Collectors.toList());
//        System.out.println(collect);



//        System.out.println(collect.containsAll(arrayList1));
//        System.out.println(arrayList1.containsAll(arrayList2));

//        Set<Integer> keySet = map.keySet();
//        System.out.println(keySet);
//        System.out.println(arrayList1);



//        boolean allMatch = map.entrySet().stream().allMatch(integer -> arrayList2.contains(integer) || arrayList1.contains(integer));
//        System.out.println(allMatch);

//        boolean allMatch = map.entrySet().stream().anyMatch(integer -> arrayList1.contains(integer));
//        System.out.println(allMatch);

//        boolean b = arrayList1.stream().anyMatch(integer -> integer.equals(1));
//        System.out.println(b);

    }

    @Test
    public void test06(){
        System.out.println("王红岩\r我");
    }



    @Test
    public void test05() throws IOException {
        String path = "/Users/dominatingm/IdeaProjects/SpringBootTest/src/main/resources/jsonArray.json";
        FileInputStream inputStream = new FileInputStream(path);
        String string = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//        System.out.println(string);

         TypeReference<HashMap<String, String>> typeReference = new TypeReference<HashMap<String, String>>() {
        };
//        JSONArray array= JSONArray.parseArray(string);
////        System.out.println(array);
//
//        for (Object o : array) {
//            Map map = (Map) o;
//            System.out.println(map.getClass());
//
//        }


        List<Map<String,String>> listMap = JSONArray.parseObject(string, List.class);
        System.out.println(listMap);

        //通过类型 a,b 把 appId 和 path 区分开来并放到 map 集合中
        HashMap<String, String> aMap = new HashMap<>();
        HashMap<String, String> bMap = new HashMap<>();

        for (Map<String, String> map : listMap) {
            String type = map.get("type");

            switch (type){
                case "A":
                    aMap.put(map.get("appId"), map.get("path"));
                    break;
                case "B":
                    bMap.put(map.get("appId"), map.get("path"));
                    break;
                default:
                    break;
            }

        }
        System.out.println(aMap);
        System.out.println(bMap);
    }


    @Test
    public void test03(){
        System.out.println(hashMap_1);
//        ApplicationContext


    }

    @Test
    public void test02() throws IOException {

        String path = "/Users/dominatingm/IdeaProjects/SpringBootTest/src/test/resources/iotest.tf";

        System.out.println(readPathContent.getFileContent(path));

    }



    @Test
    public void test01(){
//        HashMap<String, AnimalsCfg> map = new HashMap<>();
//
//        TypeReference typeReference = new TypeReference<HashMap<String, AnimalsCfg>>(){} ;
//
//
//        List<Object> objects = Arrays.asList();


        System.out.println("a");
    }





}
