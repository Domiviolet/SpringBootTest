package com.www.http.kits.execise.draft;


import com.www.http.kits.Java8.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ReferenceTest01 {

    @Test
    public void test01(){
      Consumer<String> consumer = System.out::println;
      consumer.accept("wang");
    }

    @Test
    public void test02(){
        BiPredicate<String,String> biPredicate = String::equalsIgnoreCase;
        System.out.println(biPredicate.test("wang", "WANG"));
    }

    @Test
    public void test03(){
       Comparator<Integer> comparator = Integer::sum;
        System.out.println(comparator.compare(1, 3));
    }

    @Test
    public void test04(){
        Employee employee = new Employee();
        employee.setName("Ross");
        Supplier<String> supplier =employee::getName ;
        System.out.println(supplier.get());

        Supplier<Integer> supplier1 = employee::getAge ;
        System.out.println(supplier1.get());
    }

    @Test
    public void test05(){
        Supplier<Employee> employeeSupplier = Employee::new ;
        System.out.println(employeeSupplier.get());
    }

    @Test
    public void test06(){
        Function<String,Employee> function = Employee::new;
        Employee apply = function.apply("王");
        System.out.println(apply);
    }
    @Test
    public void test07(){
        Function<Integer,String[]> function = String[]::new ;
        System.out.println(function.apply(10).length);
    }



}
