package com.www.http.kits.Java8;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    /**
     * Optional<T>类是一个容器,代表一个值存在或不存在,原来用null表示一个值不存在,
     * 现在Optional可以更好的表达这个概念,并且可以避免空指针异常
     *  常用方法:
     *      1.Optional.of(T t) : 创建一个Optional实例
     *      2.Optional.empty() : 创建一个空的Optional实例
     *      3.Optional.ofNullable(T t) : 若t不为null,创建Optional实例,否则创建空实例
     *      4.isPresent() : 判断是否包含值
     *      5.orElse(T t) : 如果调用对象包含值,返回该值,否则返回t
     *      6.orElseGet(Supplier s) : 如果调用对象包含值,返回该值,否则返回s 获取的值
     *      7.map(Function f) : 如果有值对其处理,并返回处理后的Optional,否则返回Optional.empty()
     *      8.flatMap(Function mapper) : 与map类似,要求返回值必须是Optional
     */
    @Test
    public void test01() {
        /**
         * Optional.of(T t) 方法  创建T类型的容器Optional
         * isPresent() 判断是否包含值
         */
        Optional<Employee> optionalEmployee = Optional.of(new Employee());
        System.out.println(optionalEmployee);

        //判断容器中是否有值,有值就获取,否则不获取,避免了空指针异常
        if (optionalEmployee.isPresent()) {
            System.out.println(optionalEmployee.get());
        }
    }

    /**
     * orElse(T t)
     */

    @Test
     public void test02() {
        //调用容器,如果容器中有值就返回该值,否则返回t 这里的t就是new Employee("zhang",13,444.5, Employee.Status.VOCATION)
        Optional<Employee> optionalEmployee1 = Optional.empty();
        System.out.println(optionalEmployee1);
        Employee employee = optionalEmployee1.orElse(new Employee("zhang", 13, 444.5, Employee.Status.VOCATION));
        System.out.println(employee);
    }
    /**
     * map(Function f) : 如果有值对其处理,并返回处理后的Optional,否则返回Optional.empty()
     */
    @Test
    public void test03(){
        Optional<Employee> optionalEmployee = Optional.ofNullable(new Employee("zhang", 13, 444.5, Employee.Status.VOCATION));

        Optional<String> s = optionalEmployee.map(employee -> employee.getName());
        System.out.println(s);
    }
}
