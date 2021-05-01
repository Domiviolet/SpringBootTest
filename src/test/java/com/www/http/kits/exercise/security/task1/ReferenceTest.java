package com.www.http.kits.exercise.security.task1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author: WangHongYan
 * @Since: 2021/03/10 19:28:55
 */
public class ReferenceTest {

    @Test
    public void test01(){
        Comparator<Integer> comparator = Integer::compareTo;
        int compare = comparator.compare(1, 3);
        System.out.println(compare);
    }

    @Test
    public void test02(){
//        Comparator<String> comparator = String::indexOf;
//        int compare = comparator.compare("abc", "bed");
//        System.out.println(compare);

        String str = "abc";
        int i = str.indexOf('b');
        System.out.println(i);
    }

    @Test
    public void testList(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        arrayList.stream().forEach(System.out::println);

    }


}
