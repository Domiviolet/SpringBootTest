package com.www.http.kits.exercise.security;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建多线程的方法
 */
public class ThreadCopy {
    //实现Runnable接口
    @Test
    public void test1(){
        new Thread(new NumberThread()).start();
    }
    class NumberThread1 implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        }
    }

    //继续Thread类(略过)


    //继承Callable接口
    @Test
    public void test3(){
        NumThread numThread = new NumThread();
        FutureTask<Object> objectFutureTask = new FutureTask<>(numThread);
        new Thread(objectFutureTask).start();
        try {
            Object o = objectFutureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    class NumThread implements Callable<Object>{

        @Override
        public Object call() throws Exception {
            int sum = 0 ;
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                sum++;
            }
            return sum;
        }
    }

}
