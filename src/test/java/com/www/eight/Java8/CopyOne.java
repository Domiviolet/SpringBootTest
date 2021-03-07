package com.www.eight.Java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class CopyOne {
    @Test
    public void test00(){
        List<Employee> list = Arrays.asList(
                new Employee("张一", 15, 994.9, Employee.Status.BUSY),
                new Employee("里二", 38, 994.9, Employee.Status.BUSY),
                new Employee("张三", 50, 9456.9, Employee.Status.VOCATION),
                new Employee("吴三", 50, 44444.9, Employee.Status.FREE),
                new Employee("王二", 23, 96644.9, Employee.Status.BUSY),
                new Employee("赵四", 56, 13456.9, Employee.Status.FREE),
                new Employee("小该", 34, 96645.9, Employee.Status.VOCATION));

    }

    @Test
    public void test01(){
        BinaryOperator<Long> addLongs = (x, y) -> x + y;
        Long apply = addLongs.apply(3L, 4L);
        System.out.println(apply);
    }
}
