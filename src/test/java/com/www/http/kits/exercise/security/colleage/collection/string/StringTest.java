package com.www.http.kits.exercise.security.colleage.collection.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringTest {

    /**
     * String中的方法
     *  replace()   修改String中的字符，返回被修改后的String. 原来的String不变
     *  concat()    拼接字符串，返回被拼接后的完整字符串
     *  length()    返回字符串的长度
     *  indexOf()   返回某个字符串第一次出现的索引，未找到会返回-1
     *  substring() 截取字符串
     *  charAt()    后跟索引，返回该索引的字符
     *  trim()      忽略字符串前面和后面的空格，返回新的首尾没有空格的字符串
     *  equalsIgnoreCase()  忽略大小写来比较内容是否相等
     */
    @Test
    public void test01(){
        String name = "abcdeg";
        String hobby = "ABcdeG";
        System.out.println(name.replace('a', 'c'));

        String eee = name.concat("eee");
        System.out.println(eee);

        System.out.println(name.length());
        System.out.println(name.indexOf('i'));

        System.out.println(name.substring(1,3));
        System.out.println(name.substring(1));
        System.out.println(name.charAt(2));

        System.out.println(name.trim());
        System.out.println(name.equalsIgnoreCase(hobby));

        System.out.println(name.indexOf("cd",1));
        System.out.println(name.substring(2,5));

        String str = "12he3is42my0toy";
        System.out.println(str.replaceAll("\\d"," "));

    }
    /**
     * String 和基本包装类的转换
     */
    @Test
    public void test02(){
        String stringNumber = "123456";
        /*int i = Integer.parseInt(stringNumber);
        System.out.println(i);
        String s = String.valueOf(i);
        System.out.println(s);*/

        char[] chars = stringNumber.toCharArray();
        System.out.println(chars);

    }
    /**
     * 可变的字符序列StringBuffer和StringBuilder
     * append() 添加数据
     * delete() 删除指定索引处的字符串
     * replace()  替换
     *
     * 增 append（）
     * 删 delete（）
     * 改 setCharAt（）/replace（）
     * 查 charAt（）
     * 长度 length（）
     * 遍历 for + charAt（）
     */
    @Test
    public void test03(){
        StringBuffer stringBuffer = new StringBuffer("abcde");
        System.out.println(stringBuffer.capacity());
        stringBuffer.append(1);
        stringBuffer.append("1");
        System.out.println(stringBuffer);

        stringBuffer.delete(1, 3);
        System.out.println(stringBuffer);

        StringBuffer stringBuffer1 = new StringBuffer("wanghongyan");
        System.out.println(stringBuffer1);
        System.out.println(stringBuffer1.replace(1,5,"yum"));

        System.out.println(stringBuffer1.append(1).append(2).append(3).append(4));


        //对象StringBuffer进行遍历
        for (int i = 0; i < stringBuffer1.length(); i++) {
            System.out.println(stringBuffer1.charAt(i));
        }
        System.out.println(stringBuffer1.charAt(3));
    }

    @Test
    public void test04(){
        StringBuffer stringBuffer = new StringBuffer("123456789");
        for (int i = 0; i < stringBuffer.length(); i++) {
            System.out.println(stringBuffer.charAt(i));
        }
        char c = stringBuffer.charAt(0);
        System.out.println(c);
    }

    @Test
    public void test05(){
        List<Integer> list = new ArrayList<>();

    }
}
