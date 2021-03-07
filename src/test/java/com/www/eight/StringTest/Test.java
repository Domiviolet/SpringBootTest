package com.www.eight.StringTest;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    @org.junit.Test
    public void test1(){
        char[] cars = new char[]{'1', 'r', 'i'};
        String string = new String(cars);
        System.out.println(string);
    }

    @org.junit.Test
    public void test2(){
        String string = "acd";
        String string1 = "acb";
        System.out.println(string.equals(string1));
    }
    @org.junit.Test
    public void test3() throws UnsupportedEncodingException {
        byte[] gbks = "中".getBytes("gbk");
        String string = new String(gbks, "utf-8");
        System.out.println(string);
    }
    /*将此字符串中的字符复制到目标字符数组中
	getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)  无返回值
	例如：
*/
    @org.junit.Test
    public void test4(){
        String string = "abc";
        System.out.println(string.indexOf('a'));

    }
    /*
    * indexOf(String str, int fromIndex)
返回指定子串的第一次出现的字符串中的索引，从指定的索引开始
* */
    @org.junit.Test
    public void test5(){
        String string = "defadeffff";
        int abc = string.lastIndexOf("de",7);
        System.out.println(abc);
    }
    @org.junit.Test
    public void test6(){
        String string = " ";
        System.out.println(string.isEmpty());
    }
    @org.junit.Test
    public void test7(){
        boolean b = true ;
        String s = String.valueOf(b);
        System.out.println(s);
    }

    @org.junit.Test
    public void test8(){
        String join = String.join("1","a","b");
        System.out.println(join);
    }

    @org.junit.Test
    public void test9(){
        char[] chars = new char[]{'a', 'b', 'c'};
        char[] chars1 = Arrays.copyOf(chars, 9);


    }

    @org.junit.Test
    public void test10(){
        int[] arr = {1,2,3,4} ;
        int[] arr1 = {1,2,3,4,5} ;
        boolean equals = Arrays.equals(arr, arr1);
        System.out.println(equals);
    }
    /*
    * public static void fill(byte[] a,
                        int fromIndex,
                        int toIndex,
                        byte val)
    * */
    @org.junit.Test
    public void test11(){
        int[] arr = {1, 2, 3, 4};
        Arrays.fill(arr, 1, 3, 5);
        for(int in : arr){
            System.out.println(in);
        }
    }
    @org.junit.Test
    public void test12(){
        String[] arr = {"a", "v", "e","b"};
        Arrays.parallelSort(arr,1,3);

        for(String  in : arr){
            System.out.println(in );
        }
    }
    /*public static <T> boolean addAll(Collection<? super T> c,
                                              T... elements)
    将所有指定的元素添加到指定的集合。 要添加的元素可以单独指定或作为数组指定。 这种方便方法的行为与c.addAll(Arrays.asList(elements)) 相同 ，但是在大多数实现中，这种方法可能会显着加快。
    单独指定元素时，此方法为现有集合添加一些元素提供了一种便捷的方法：

     Collections.addAll(flavors, "Peaches 'n Plutonium", "Rocky Racoon");
    参数类型
        T - 要添加和收集的元素的类
    参数
        c - 要插入 elements的集合
        elements - 要插入到 c的元素
   结果
        true如果集合由于调用而更改

     */
    @org.junit.Test
    public void test13(){
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        Collections.addAll(integerList, 1, 3, 4, 6, 7);
        System.out.println(integerList);
    }

    @org.junit.Test
    public void test14(){
        List<Object> objects = Collections.emptyList();
    }




}
