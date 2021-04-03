package com.www.http.kits.Java8;

import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LambdaTest {

    /**
     * Lambda表达式的: 左边是参数列表,右边是所需要的执行功能
     *              Lambda表达式中的参数列表的数据类型可以省略不写,因为jvm可以推断出
     *              它需要函数式接口的支持(接口中只有一个抽象方法的接口)注意抽象方法没有方法体
     *      常用的函数式接口: Comparator,
     *                      Runnable,
     *                      Consumer<T>     消费型接口  含有: void accept()方法
     *                      Supplier<T>     供给型接口  含有: T get()方法
     *                      Function<T ,R>  T代表参数,R代表返回值  含有的方法: R apply(T t)
     *                      Predicate<T>    断言型接口, 含有: boolean test(T t)
     *
     */
    /**
     * 方式一: 无参无返回值得 () -> System.out.println()
     */
    @Test
    public void test00(){
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("myBike");
            }
        };*/
        //改写后:
        Runnable runnable = () -> System.out.println("myBike");
        runnable.run();
    }

    /**
     * 方式二: 含有一个参数的,无返回值
     *      注意这里都是以匿名内部类的形式实现的
     */
    @Test
    public void test01(){
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("youaremybaby");
    }
    /**
     * 方式三: 有两个以上的参数,有返回值,并且Lambda体中有多条语句(要用大括号）
     */
    @Test
    public void test02(){
        Comparator<Integer> comparator = (x,y) ->{
            System.out.println("函数式接口");
            //后面的数y和前面的数x哪个大,如果前者大,返回正数,如果后者大,返回-1,其他返回0
            return Integer.compare(y, x);
        };
        int compare = comparator.compare(12, 34);
        System.out.println(compare);
    }

    /**
     * 方式四: 如果以上,只有一条执行语句,则大括号和return可省略
     */
    @Test
     public void test03(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        int compare = comparator.compare(10, 12);
        System.out.println(compare);
    }
    /**
     *  Consumer<T>  消费型接口  含有: void accept()方法
     *  应用:
     */
    @Test
    public void test04(){
        buyCar("法拉利",car-> System.out.println("买了一辆"+car));
    }
    public void buyCar(String car, Consumer<String> consumer){
        consumer.accept(car);
    }

    /**
     * Supplier<T>  供给型接口 含有: T get()方法
     */
    @Test
    public void test05(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for(Integer integer : numList){
            System.out.println(integer);
        }
    }
    //需求,产生指定个数的整数,并放入集合中
    public List<Integer> getNumList(Integer num , Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list ;
    }

    /**
     * Function<T ,R>  T代表参数,R代表返回值  含有的方法: R apply(T t)
     */
    @Test
    public void test06(){
        String strHandler = strHandler(" Iamgoodman ", (str) -> str.trim());
        System.out.println(strHandler);
    }
    //需求: 用于处理字符串
    public String strHandler(String str , Function<String,String> function){
       return function.apply(str);
    }

    /**
     * Predicate<T>   断言型接口, 含有: boolean test(T t)
     */
    @Test
    public void test07(){
        List<String> list = Arrays.asList("wandddg", "xffu", "yanfg", "ue");
        //需求,对长度大于3的接口操作
        List<String> stringList = filterString(list, s -> s.length()>3);
        for(String string : stringList){
            System.out.println(string);
        }
    }
    //需求: 将满足条件的字符串,放入集合中
    public List<String> filterString(List<String> list, Predicate<String> predicate){
        List<String> stringList1 = new ArrayList<>();
        for(String str : list){
            if (predicate.test(str)){
                stringList1.add(str);
            }
        }
        return stringList1;
    }



    /**
     * 集合结合stream()方法
     */

    @Test
    public void test0111(){
      /*  Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        };*/
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(5);
        treeSet.add(9);
        treeSet.add(10);
        treeSet.add(2);
        System.out.println(treeSet);
    }

    @Test
    public void test0222(){
        List<Employee> employeeList = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("张二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("张三", 116, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9)
        );

   /*     //需求,获取当前公司中员工的年龄大于35的员工信息,并且进行遍历
//        employeeList.stream().filter(employee -> employee.getSalary()>994).forEach(System.out::println);

        //需求,获取当前公司员工的年龄大于35的员工,只取这些中的前2个,并且进行遍历.
        employeeList.stream().filter(employee -> employee.getSalary()>994).limit(2).forEach(System.out::println);

        //获取所有的名字,并打印输出
        employeeList.stream().map(Employee::getName).forEach(System.out::println);

        //获取所有的年龄,并打印输出
        employeeList.stream().map(Employee::getAge).forEach(System.out::println);

        //获取所有的工资,并打印输出
        employeeList.stream().map(Employee::getSalary).forEach(System.out::println);*/

        employeeList.stream().forEach(System.out::println);
    }

    @Test
    public void test0333() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("张二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("张三", 116, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9)
        );
    }

    @Test
    public void test002(){
        Runnable runnable = () -> System.out.println("my");
        runnable.run();
    }
    @Test
    public void test004(){
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("wanghongyan");

    }

    @Test
    public void test005() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        int compare = comparator.compare(1, 3);
        System.out.println(compare);
    }

    @Test
    public void test006(){
        List<Employee> employeeList = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("张二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("张三", 116, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9)
        );
    }

    /**
     * binary(二元的)
     * BinaryOperator继承了 BiFunction 接口
     * 其中有一个 apply(T,T)方法,注意这里的 apply()方法
     * 中传入的是两个参数
     */
    @Test
    public void test007(){
//        BinaryOperator<Long> binary = (x,y) -> x+y;
//        Long apply = binary.apply(2L, 4L);
//        System.out.println(apply);
        BinaryOperator<String> binaryOperator = (x,y) -> x+y+"岩" ;
        String apply = binaryOperator.apply("wang", "hong");
        System.out.println(apply);
    }

    /**
     *unary(一元的)
     * UnaryOperator继承了BiFunction 接口
     * 其中有一个 apply(T)方法,注意这里的 apply()方法
     * 中传入的是一个参数
     *   注意和上面的进行区分!!
     */
    @Test
    public void test008(){
        UnaryOperator<String> unaryOperator = x -> x + "Watchmen";
        String apply = unaryOperator.apply("A");
        System.out.println(apply);
    }

    /**
     * Consumer 接口用来拆分数组中的元素,同时添加一些个性化定制内容
     */
    @Test
    public void test009(){
        String[] strings = {"A,Monday,1", "B,Tuesday,2", "C,Wednesday,3", "D,Thursday,4", "E,Friday,5", "D,Saturday,6", "E,Sunday,7"};
        printInfo(strings,x ->{
            System.out.print("序号: " + x.split(",")[0]+" ;");
        },y ->{
            System.out.print("星期: "+y.split(",")[1] + " ;");
        },z ->{
            System.out.println("ID: "+ z.split(",")[2]);
        });
    }
    public static void printInfo(String[] str,Consumer<String> con1,Consumer<String> con2,Consumer<String> con3){
        for (int i = 0; i < str.length; i++) {
            con1.andThen(con2).andThen(con3).accept(str[i]);
        }
    }


}