package com.www.eight.Java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceTest {
    /**
     * 方法引用:
     * 若Lamda体中的内容已经实现了,我们可以使用方法引用
     * 主要有三种语法格式:
     * 1.对象::实例方法名
     * 2.类::静态方法名
     * 3.类::实例方法名
     * 4.类::new ,调用的是构造器,这里的构造器,可能是空参的也可能不是
     */
    @Test
    public void test00() {
        //要求Consumer接口中的抽象方法的参数列表,返回值类型和System.out方法中的参数列表,返回值类型都相同
        Consumer<String> consumer = System.out::println;
        consumer.accept("wang");
    }

    /**
     * 对象::实例方法名
     */
    @Test
    public void test01() {
        Employee employee = new Employee();
        employee.setName("Jock");
//        Supplier<String> supplier = () -> employee.getName();
        Supplier<String> supplier = employee::getName; //这里的getName()的类型要和前面泛型的类型一致,都是String
        String s = supplier.get();
        System.out.println(s);
    }

    /**
     * 类::静态方法
     */
    @Test
    public void test02(){
        Comparator<Integer> integerComparator =Integer::compareTo ;
        int compare = integerComparator.compare(1,3);
        System.out.println(compare);
    }

    /**
     * 类::实例方法名  实例方法就是非静态方法
     * BiPredicate(T,U)传递两个参数,看这两个参数是否符合某个条件表达式
     */
    @Test
    public void test03(){
//        BiPredicate<String, String> stringBiPredicate = (x, y) -> x.equals(y);
        BiPredicate<String ,String> biPredicate = String::equals;
        System.out.println(biPredicate.test("string", "stringtb"));
    }

    @Test
    public void test04(){
        //调用的是Employee中的构造器
        Supplier<Employee> supplier = Employee::new;
        Employee employee = supplier.get();
        System.out.println(employee);
    }

    @Test
    public void test05(){
        Function<Integer,Employee> function = Employee::new; /*表示调用Employee中的参数类型是Integer的构造器*/
        Employee apply = function.apply(101);
        System.out.println(apply);
    }

    /**
     * 数组引用
     */
    @Test
    public void test06(){
        Function<Integer ,String[]> function = String[]::new ;
        String[] apply = function.apply(23); //等同于: String[] apply = new String[23]
        System.out.println(apply.length);
    }

    @Test
    public void test07(){
        Consumer<String> consumer=System.out::println;
        consumer.accept("wang");
    }

    @Test
    public void test08(){
        Employee employee = new Employee();
        employee.setName("Jock");
//        Supplier<String> supplier=()->employee.getName();
//        System.out.println(supplier.get());
        Supplier<String> supplier=employee::getName;
        System.out.println(supplier.get());

    }

    @Test
    public void test09(){

    }
}

