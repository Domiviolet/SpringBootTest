package com.www.http.security.exception;

/**
 * @Author: WangHongYan
 * @Since: 2020/12/08 07:05:01
 */
public class AnimalsException extends Exception{

    private String errMsg;

    //无参构造
    public AnimalsException(){}

    //有参构造
    public AnimalsException(String errMsg){
        super(errMsg);
    }





}
