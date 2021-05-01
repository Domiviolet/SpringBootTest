package com.www.http.kits.exercise.security.colleage.sigleton.lazy_hungry;

/**
 * 饿汉式
 */
public class Singleton {

    //1.私有化构造器
    private Singleton(){

    }
    //2.内部创建类的对象
    //4.要求此对象也必须声明为静态的
    private static Singleton instance = new Singleton();

    //3.提供公共的静态的方法,返回类的对象
    public static Singleton getInstance(){
        return instance ;
    }


}



