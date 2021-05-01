package com.www.http.kits.exercise.security.colleage.sigleton;

/**
 * 懒汉式
 */
public class TestTest {

    private TestTest(){

    }

    private static TestTest instance = null ;

    public static TestTest getInstance(){
        if(instance==null){
            instance =new TestTest();
        }
        return instance ;
    }
}
