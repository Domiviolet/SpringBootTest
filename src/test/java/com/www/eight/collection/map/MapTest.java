package com.www.eight.collection.map;


import com.www.eight.collection.list.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
    /**
     * Map集合中常用的方法
     * put（）添加元素k，v
     * get() 后加key，获取这个key对应的value值
     * size（）获取map集合中的key的数目
     * values() 获取所有的values值
     * keySet() 获取所有的key值，以数组的形式呈现
     * isEmpty() 是否为空,为空位true
     * entrySet()把map集合转换为set集合
     */
    @Test
    public void test01(){
        Map<String,String> map = new HashMap<>();

        map.put("1", "Monday");
        map.put("2", "Tuesday");
        map.put("3", "Wednesday");
        map.put("4", "Thursday");
        map.put("5", "Friday");
        map.put("6", "Saturday");
        map.put("7", "Sunday");
        map.put("8", "Sunday");
        System.out.println(map);
        System.out.println(map.size());
        System.out.println(map.keySet());

        System.out.println(map.get("1"));
        System.out.println(map.values());
        System.out.println(map.keySet());
        System.out.println(map.isEmpty());


        /**
         * 遍历map
         */
        for(String key : map.keySet()){
            System.out.println(key);
            System.out.println(map.get(key));
        }
        System.out.println("==========================");
        //转换为set集合
        /**
         * 以增强for的方式进行遍历
         */
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for(Map.Entry<String, String> entry : entries){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("************************");
        /**
         * 转换为迭代器进行遍历
         */
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
          
        }
        System.out.println("************************");

    }

    /**
     * 在Map集合中添加ArrayList集合
     */
    @Test
    public void test02(){
        HashMap<String, ArrayList<Student>> map = new HashMap<>();

        ArrayList<Student> arrayListStudent = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("NO1");
        student1.setAge(22);
        student1.setStudentId(12);
        arrayListStudent.add(student1);

        Student student2 = new Student();
        student2.setName("NO2");
        student2.setAge(23);
        student2.setStudentId(13);
        arrayListStudent.add(student2);

        Student student3 = new Student();
        student3.setName("NO3");
        student3.setAge(24);
        student3.setStudentId(14);
        arrayListStudent.add(student3);

        ArrayList<Student> arrayListStudent1 = new ArrayList<>();
        Student student4 = new Student();
        student4.setName("NO4");
        student4.setAge(22);
        student4.setStudentId(12);
        arrayListStudent1.add(student1);

        Student student5 = new Student();
        student5.setName("NO5");
        student5.setAge(12);
        student5.setStudentId(10000);



        map.put("One", arrayListStudent);
        map.put("Two", arrayListStudent1);

        for(String key : map.keySet()){
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    /**
     * 通过通配符?实现Map集合中ArrayList的不同类的整合
     */
    @Test
    public void test03(){
        HashMap<String, ArrayList<?>> map = new HashMap<>();

        ArrayList<Student> arrayListStudent = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("NO1");
        student1.setAge(22);


        student1.setStudentId(12);
        arrayListStudent.add(student1);

        Student student2 = new Student();
        student2.setName("NO2");


        student2.setAge(23);
        student2.setStudentId(13);
        arrayListStudent.add(student2);

        Student student3 = new Student();
        student3.setName("NO3");
        student3.setAge(24);
        student3.setStudentId(14);
        arrayListStudent.add(student3);

        ArrayList<Teacher> arrayListTeacher = new ArrayList<>();
        Teacher teacher1 =new Teacher();
        teacher1.setName("Jock");
        teacher1.setTelephoneNumber(1556542);
        arrayListTeacher.add(teacher1);

        map.put("One", arrayListStudent);
        map.put("Two", arrayListTeacher);

        for(String key : map.keySet()){
            System.out.println(key);
            System.out.println(map.get(key));

        }
    }

    @Test
    public void test04(){
        HashMap<String, ArrayList<?>> map = new HashMap<>();

        ArrayList<Student> studentArrayList = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("NO1");
        student1.setAge(12);
        student1.setStudentId(23);
        studentArrayList.add(student1);

        Student student2 = new Student();
        student2.setName("NO2");
        student2.setAge(15);
        student2.setStudentId(25);
        studentArrayList.add(student2);

        Student student3 = new Student();
        student3.setName("NO3");
        student3.setAge(10);
        student3.setStudentId(24);
        studentArrayList.add(student3);
        Collections.sort(studentArrayList, new Comparator<Student>() {
            @Override
            //按年龄从从大到小排序
            public int compare(Student o1, Student o2) {
                int i = o2.getAge()-o1.getAge();
                return i ;
            }
        });

        ArrayList<Teacher> teacherArrayList = new ArrayList<>();
        Teacher teacher1 =new Teacher();
        teacher1.setName("Jock");
        teacher1.setTelephoneNumber(1556542);
        teacherArrayList.add(teacher1);

        map.put("1", studentArrayList);
        map.put("2", teacherArrayList);
        //增强for遍历
       /* for(String key : map.keySet()){
            System.out.println(key);
            System.out.println(map.get(key));
        }*/

      //迭代器来遍历
       /* Iterator<Map.Entry<String, ArrayList<?>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, ArrayList<?>> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }*/

        //转换成set集合来遍历
        Set<Map.Entry<String, ArrayList<?>>> entries = map.entrySet();
//        System.out.println(entries); 输出集合
        for(Map.Entry<String, ArrayList<?>> entry : entries){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
