package com.www.http.kits.exercise.security.draft;


import com.www.http.kits.exercise.security.colleage.Java8.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambdaTest02 {

    @Test
    public void test01() {
        Runnable runnable = () -> System.out.println("MyLambda");
        runnable.run();
    }

    @Test
    public void test02() {
        Consumer consumer = x -> System.out.println(x);
        consumer.accept(12879);
    }

    @Test
    public void test03() {
        Consumer consumer = x -> {
            for (int i = 0; i < (int) x; i++) {
                System.out.println(i);
            }
            System.out.println(x);
        };
        consumer.accept(10);
    }

    @Test
    public void test04() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("Who are you");
    }

    @Test
    public void test05() {
        Comparator<Integer> comparator = (x, y) -> x.compareTo(y);
        System.out.println(comparator.compare(1, 3));
    }

    @Test
    public void test06() {
        consumerCar("法拉利", x -> System.out.println("买了一辆" + x));
    }

    public String consumerCar(String carName, Consumer<String> consumer) {
        consumer.accept(carName);
        return carName;
    }

    @Test
    public void test07() {
        List<Integer> integerList = generateInteger(10, () -> (int) (Math.random() * 1000));
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
    }

    public List<Integer> generateInteger(Integer number, Supplier<Integer> supplier) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Integer integer = supplier.get();
            integerList.add(integer);
        }
        return integerList;
    }

    @Test
    public void test08() {
        String str1 = "wang";
        String str2 = "hong";
        System.out.println(supply(() -> str1 + str2));
    }

    public String supply(Supplier<String> supplier) {
        return supplier.get();
    }

    @Test
    public void test09() {
        String handler = strHandler("abcdefg", x -> x.concat("abc"));
        System.out.println(handler);
    }

    public String strHandler(String string, Function<String, String> function) {
        String apply = function.apply(string);
        return apply;
    }

    @Test
    public void test10() {
        List<String> list = Arrays.asList("ab", "cdb", "ef", "bgh");
        List<String> stringList = fitString(list, x -> x.contains("b"));
        System.out.println(stringList);
    }

    public List<String> fitString(List<String> stringList, Predicate<String> predicate) {
        List<String> strings = new ArrayList<>();
        for (String string : stringList) {
            if (predicate.test(string)) {
                strings.add(string);
            }
        }
        return strings;
    }

    @Test
    public void test11() {
        List<String> stringList = Arrays.asList("abc", "wang", "am", "are", "bing", "bye");
        List<String> list = getString(stringList, x -> x.contains("a"));
        System.out.println(list);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public List<String> getString(List<String> string, Predicate<String> predicate) {
        List<String> list = new ArrayList<>();
        for (String str : string) {
            if (predicate.test(str)) {
                list.add(str);
            }
        }
        return list;
    }

    @Test
    public void test12() {

        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("张一", 34, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        //先按状态分组,状态相同在按照年龄分组
        for (Map.Entry<Employee.Status, Map<String, List<Employee>>> statusMapEntry : listEmployee.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() > 50) {
                        return "old";
                    } else if (e.getAge() > 30) {
                        return "middle";
                    } else {
                        return "young";
                    }
                })))
                .entrySet()) {
            Employee.Status key = statusMapEntry.getKey();
            System.out.println(key);
            Map<String, List<Employee>> value = statusMapEntry.getValue();
            System.out.println(value);
        }
    }

    @Test
    public void test13(){

    }

}
