package com.www.eight.sigleton.lazy_hungry;

/**
 * 懒汉式
 */
public class Order {

    //1.私有化类的构造器
    private Order(){

    }

    //2.声明当前类的对象,没有初始化
    //4.此对象也声明为static的
    private static Order instance = null ; //懒汉式和饿汉式的区别,主要看最初对象是否造好了,如果造好了,就是饿汉式,没造好就是懒汉式

    //3.声明public ,static的返回当前类对象的方法
    public static Order getInstance(){
        if(instance==null){
            instance = new Order();
        }
        return instance ;
    }

}

