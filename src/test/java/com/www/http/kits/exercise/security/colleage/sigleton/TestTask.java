package com.www.http.kits.exercise.security.colleage.sigleton;

/**
 * 饿汉式
 */
public class TestTask {

    private TestTask(){

    }

    private static TestTask instance = new TestTask();

    public static TestTask getInstance(){
        return instance ;
    }
}
