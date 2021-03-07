package com.www.eight.execise;


import com.www.eight.Java8.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest02 {
    @Test
    public void test01(){
//        Stream<Double> generate = Stream.generate(() -> Math.random() * 100);
//        generate.limit(10).forEach(System.out::println);

//        Stream<Integer> iterate = Stream.iterate(1, x -> x + 3);
//        iterate.limit(4).forEach(System.out::println);

        Stream<Integer> iterate1 = Stream.iterate(2, x -> x * 3);
//        Integer reduce = iterate1.limit(5).reduce(0, (x, y) -> x + y);
//        System.out.println(reduce);
       iterate1.limit(5).forEach(System.out::println);
    }

    @Test
    public void test02(){

        List<Employee> list = Arrays.asList(
                new Employee("张一", 15, 2000.0),
                new Employee("里二", 38, 2000.0),
                new Employee("张三", 50, 25.0),
                new Employee("吴三", 5, 25.0),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96.9));
        list.stream().sorted((x,y)->{
            int i = (int)(x.getSalary()-y.getSalary());
            if (x.getSalary().equals(y.getSalary())){
                return x.getAge()-y.getAge();
            }
            return i;
        }).forEach(System.out::println);
    }

    @Test
    public void test03(){

        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        Map<Employee.Status, Map<String, List<Employee>>> map = listEmployee.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(x -> {
            if (x.getAge() > 40) {
                return "Older";
            } else if (x.getAge() > 30) {
                return "Middle";
            } else {
                return "Youth";
            }
        })));
        for(Employee.Status key : map.keySet()){
            System.out.println(key);
            System.out.println(map.get(key));

        }
    }
}
