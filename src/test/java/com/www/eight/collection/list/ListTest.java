package com.www.eight.collection.list;

import org.junit.Test;

import java.util.*;

/**
 * List集合中的方法测试
 */
public class ListTest {

    //add（）方法
    @Test
    public void test01(){
        List<String> list = new ArrayList<>();
        list.add("Monday");
        list.add("Tuesday");
        list.add("wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");
        System.out.println(list);//[Monday, Tuesday, wednesday, Thursday, Friday, Saturday, Sunday]
    }

    /**
     * size()方法
     */
    @Test
    public void test02(){
        List<String> list = new ArrayList<>();
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");
        list.set(0, "mm");
        System.out.println(list);
        System.out.println(list.size());
    }

    /**
     * contains()方法 ，搜索list集合中是否含有该元素
     * isEmpty()方法
     * get()方法/后跟索引值
     * indexOf()方法，获取元素的索引
     * remove()方法 ，删除元素
     * add()方法，带索引的
     * clear()方法
     *  set()方法 ，改写指定索引处的元素，返回被改写的元素
     *  add()方法 整体添加
     *  addAll()方法,拆分添加
     *  集合中的add()方法和 addAll()方法区别
     *      如果传过来的是一个集合,用add()方法,是把整个集合添加到现有集合中,这里整个集合是作为一个整体的
     *      而addAll()方法则是,把这个集合给拆分成每个元素,然后再把所有的元素添加到现有集合中
     */

    /**
     *  集合中的add()方法和 addAll()方法区别
     */
    @Test
    public void test00(){
        List<String> list1 = new ArrayList<>();
        list1.add("Monday");
        list1.add("Tuesday");
        list1.add("Wednesday");
        list1.add("Thursday");
        list1.add("Friday");
        list1.add("Saturday");
        list1.add("Sunday");
        System.out.println(list1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(11);
        list2.add(2222);
        list2.add(345);
        System.out.println(list2);

        List<List<?>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        System.out.println(list);
        System.out.println("=======");
        boolean b = list.addAll(Collections.singleton(list1));
        System.out.println(b);
        list.addAll(Collections.singleton(list2));
    }


    @Test
    public void test03(){
        List<String> list = new ArrayList<>();
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");
        list.add(3, "--");
        System.out.println(list.set(0,"one"));
        System.out.println(list);
        System.out.println(list.contains("Monday"));
        System.out.println(list.isEmpty());
        System.out.println(list.get(2));
        System.out.println(list.indexOf("Friday"));
        System.out.println(list.remove("Saturday"));

    }

    /**
     *  List集合的遍历
     */
    @Test
    public void test04(){
        List<String> list =new ArrayList<>();
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Sunday");

        /**
         *  方式一： 增强for遍历
         */

       /* for(String str : list){
            System.out.println(str);
        }*/

        /**
         * 方式二： 转换为数组遍历 ,用到toArray（）和 size（）方法
         */

       /* String[] stringArray = new String[list.size()];
        list.toArray(stringArray);
        for (int i = 0; i < stringArray.length; i++) {
            System.out.println(stringArray[i]);*//*
        }*/

        /**
         * 方式三： 采用迭代器遍历
         */
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * List集合去重
     */
    @Test
    public void test05(){
        List<String> list = new ArrayList<>();
        list.add("Monday");
        list.add("Monday");
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        list.add("Thursday");
        list.add("Thursday");
        list.add("Thursday");
        list.add("Thursday");
        list.add("Friday");
        list.add("Friday");
        list.add("Saturday");
        list.add("Saturday");
        list.add("Saturday");
        list.add("Sunday");
        list.add("Sunday");

        /**
         * 方式一： 转换成Set集合
         */
        Set<String> stringSet = new HashSet<>();
        for(String str : list){
            stringSet.add(str);
        }
        System.out.println(stringSet);

        /**
         * 方式二： 构建过渡集合，通过list集合中的contains（）方法
         */
        List<String> listTransition = new ArrayList<>();
        for(int i =0 ; i<list.size() ;i++){
            if(!listTransition.contains(list.get(i))){
                listTransition.add(list.get(i));
            }
        }
        System.out.println(listTransition);


        /**
         * 方式三： 通过for循环
         */
        for(int i=0 ; i<list.size()-1;i++){
            for(int j=list.size()-1 ;j>i ; j--){
                if(list.get(i).equals(list.get(j))){
                    list.remove(i);
                }
            }
        }
        System.out.println(list);
    }

    /**
     * list集合排序 //实现comparable接口
     */
    @Test
    public void test06(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(32, 24, "Tom"));
        list.add(new Student(14, 32, "Jack"));
        list.add(new Student(23, 24, "Ross"));
        list.add(new Student(13, 12, "Timor"));
        Collections.sort(list);
        for(Student student : list){
            System.out.println(student);
        }

    }

    /**list集合
     * 通过比较器实现排序，实现comparator接口
     * Collections.sort()中只能跟list型的
     */
    @Test
    public void test07(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(32, 24, "Tom"));
        list.add(new Student(14, 32, "Jack"));
        list.add(new Student(23, 24, "Ross"));
        list.add(new Student(23, 24, "Louse"));
        list.add(new Student(13, 12, "Timor"));
        list.add(new Student(12, 13, "vin"));
//        //按年龄和学号总和从高到低排序
//        Collections.sort(list, (o1,o2) -> {
//                int i = (o2.getAge()+o2.getStudentId()) - (o1.getAge()+o1.getStudentId());
//                return i ;
//        });
//        Iterator<Student> iterator = list.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        /*list.stream().map(student -> null).collect(groupBying() );
        System.out.println(result);*/
    }

    /**
     * set集合
     * 通过比较器实现排序，实现comparator接口
     *
     */
    @Test
    public void test08(){
        TreeSet<Student> set = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int i = (o2.getAge()+o2.getStudentId()) - (o1.getAge()+o1.getStudentId());
                int j = i == 0 ? (o2.getStudentId()-o1.getStudentId()) : i;
                return j ;
            }

        });
        set.add(new Student(32, 24, "Tom"));
        set.add(new Student(14, 32, "Jack"));
        set.add(new Student(23, 24, "Ross"));
        set.add(new Student(23, 24, "Louse"));
        set.add(new Student(13, 12, "Timor"));
        set.add(new Student(12, 13, "vin"));

        Student[] studentArrays = new Student[set.size()];
        set.toArray(studentArrays);
        for (int i = 0; i < studentArrays.length; i++) {
            System.out.println(studentArrays[i]);
        }

    }
}
