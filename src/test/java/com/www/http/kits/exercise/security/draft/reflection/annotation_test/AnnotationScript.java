package com.www.http.kits.exercise.security.draft.reflection.annotation_test;

import com.www.http.kits.exercise.security.draft.reflection.auxiliary.StuCharacteristicAnnotation;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @Author: WangHongYan
 * @Since: 2021/04/18 19:57:09
 */
public class AnnotationScript {

    @StuCharacteristicAnnotation(name = "Jack")
    String name;



    @Test
    public void test00() throws ClassNotFoundException {
        stuAnnotationDemo();
        System.out.println(name);//这样就可以直接打印 name 了,这里的 name 先被定义为全局变量,再通过第一个方法执行过程中去赋值,从而 name 值不再为 null

    }

    //通过反射获取注解中的属性
    public void stuAnnotationDemo() throws ClassNotFoundException {
        //注意这里的forName()中的类名要是全路径即: com.www.http.kits.execise.draft.reflection.annotation_test.AnnotationScript
        // 直接写成这样: AnnotationScript不对
        Class<?> aClass = Class.forName("com.www.http.kits.exercise.security.draft.reflection.annotation_test.AnnotationScript");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(StuCharacteristicAnnotation.class)) {
                StuCharacteristicAnnotation annotation = field.getAnnotation(StuCharacteristicAnnotation.class);
               name = annotation.name();
                int age = annotation.age();
                System.out.println(age);
                System.out.println(name);

            } else {
                System.out.println("Annotation is not exist");
            }
        }

    }

    @Test
    public void test01(){
        //ofNullable()方法是,如果参数不为 null,结果就是这个参数,否则是 orElse()中的参数
        String s = (String) Optional.ofNullable(null).orElse("b");
        System.out.println(s);

    }

    @Test
    public void test02(){

    }

    @Test
    public void test03(){

    }

    @Test
    public void test04(){

    }




}
