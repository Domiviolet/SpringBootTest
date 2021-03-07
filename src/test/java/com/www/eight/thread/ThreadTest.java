package com.www.eight.thread;



/**
 * 创建多线程的方式:
 *      1.继承Thread类
 *      2.实现Runnable接口
 *      3.通过callable和Future
 *      4.通过线程池
 */
//方式一: 继承Thread类
public class ThreadTest extends Thread {
    public static void main(String[] args) {
        ThreadTest thread1 = new ThreadTest();
        ThreadTest thread2 = new ThreadTest();
        thread1.setName("线程1");
        thread2.setName("线程2");
        thread1.start(); //让线程处于就绪状态
        thread2.start();

    }

    public void run() {//运行线程
        for (int i = 0; i<= 20; i++) {
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
