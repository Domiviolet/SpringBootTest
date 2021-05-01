package com.www.http.kits.exercise.security.draft;


import com.www.http.kits.exercise.security.colleage.Java8.Employee;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest01 {

    /**
     * generate stream
     */
    @Test
    public void test01() {
        Stream<Integer> stream = Stream.iterate(0, x -> x + 3);
        stream.limit(3).forEach(System.out::println);
    }

    @Test
    public void test02() {
        Stream<Integer> stream = Stream.generate(() -> (int) (Math.random() * 100));
        stream.limit(4).forEach(System.out::println);
    }

    @Test
    public void test03() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("张二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("三张", 116, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9));
        employeeList.stream().skip(2).forEach(System.out::println);
    }

    @Test
    public void test04() {
        List<String> list = Arrays.asList("aa2a", "bb", "cccdd", "eee");
        list.stream().map(x -> x.length()).forEach(System.out::println);
    }

    @Test
    public void test05() {

        List<Employee> employeeList = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("张二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("张三", 116, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9));

        employeeList.stream().map(Employee::getSalary).forEach(System.out::println);
        employeeList.stream().map(Employee::getName).forEach(System.out::println);
        employeeList.stream().map(Employee::getAge).forEach(System.out::println);
    }

    @Test
    public void test06() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eee", "rrtte");
        /*Stream<Stream<Character>> streamStream = list.stream().map(StreamTest01::filterCharacter);
        streamStream.forEach(x->{
            x.forEach(System.out::println);
        })*/
        ;
        list.stream().flatMap(StreamTest01::filterCharacter).forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String string) {
        List<Character> list = new ArrayList<>();
        for (Character character : string.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    @Test
    public void test07() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eee", "rrtte");
        list.stream().sorted().forEach(System.out::println);
    }

    @Test
    public void test08() {
        List<Employee> list = Arrays.asList(
                new Employee("张一", 15, 994.9),
                new Employee("里二", 38, 499.3),
                new Employee("张三", 50, 9456.9),
                new Employee("吴三", 50, 44444.9),
                new Employee("王二", 23, 96644.9),
                new Employee("赵四", 56, 13456.9),
                new Employee("小该", 34, 96645.9));

        list.stream().sorted((x, y) -> {
            int i = x.getAge() - y.getAge();
            if (x.getAge().equals(y.getAge())) {
                return (int) (x.getSalary() - y.getSalary());
            }
            return i;
        }).forEach(System.out::println);
    }

    @Test
    public void test09() {
        List<Employee> list = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));

//        list.stream().map(Employee::getStatus).forEach(System.out::println);
//        System.out.println(list.stream().anyMatch(x -> x.getAge().equals(15)));

//        System.out.println(list.stream().count());
        Optional<Employee> max = list.stream().max((x, y) -> {
            int i = x.getAge() - y.getAge();
            if (x.getAge().equals(y.getAge())) {
                return (int) (x.getSalary() - y.getSalary());
            }
            return i;
        });
        System.out.println(max);

    }

    @Test
    public void test10() {
//        List<Integer> integerList = Arrays.asList(23, 45, 56, 12, 8, 9, 90);
//        System.out.println(integerList.stream().reduce(0, (x, y) -> x + y));
//        System.out.println(integerList.stream().reduce((x, y) -> x + y));

        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));
        System.out.println(listEmployee.stream().map(Employee::getSalary).reduce((x, y) -> x + y));
        System.out.println(listEmployee.stream().map(Employee::getAge).reduce(Integer::sum));
    }

    @Test
    public void test11() {

        List<Employee> listEmployee = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 499.3, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("张三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));

        //先按状态分,状态相同再按年龄分
        Map<Employee.Status, Map<String, List<Employee>>> map = listEmployee.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(x -> {
            if (x.getAge() < 34) {
                return "青年";
            } else if (x.getAge() < 50) {
                return "中年";
            } else {
                return "老年";
            }
        })));
        for(Employee.Status key : map.keySet()){
            System.out.println(key );
            System.out.println(map.get(key));
        }

    }

    @Test
    public void tes12(){
        ClassPathResource classPathResource = new ClassPathResource("key/jsonArray.json");
        String filename = classPathResource.getFilename();
        System.out.println(filename);

    }

   @Test
   public void test13(){

   }

   @Test
    public void tes14(){

    }

   @Test
   public void test15(){

   }

   @Test
    public void tes16(){

    }

   @Test
   public void test17(){

   }

   @Test
    public void tes18(){

    }

   @Test
   public void test19(){

   }

   @Test
   public void test20(){

   }



}



