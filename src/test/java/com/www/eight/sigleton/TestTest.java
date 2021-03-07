package com.www.eight.sigleton;

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
