package com.www.http.kits.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//方式4: 以线程池的方式创建多线程
public class ThreadPool {
    public static void main(String[] args) {
        //1.创建有10个线程的线程池.这里线程数越多速度越快
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        //2.执行指定的线程操作,需要提供实现Runnable接口或Callable接口的实现类对象
        executorService.execute(new NumberThread());  //使用于Runnable ,这里参数需要Runnable实现类对象.我们获得了这个实现类的对象,自然也得到它内部的run()方法了
        //我们需要需要其他线程池做别的事情,可以在再创建一个线程对象.同时在创建一个类也去实现Runnable接口,该类重写run()方法
//        executorService.submit(); //适合使用于Callable接口.参数是Callable接口的实现类对象
        executorService.shutdown();
    }
}
//下面是创建线程池究竟要做什么事?
class NumberThread implements Runnable{

    @Override
    public void run() {
        for(int i =0 ; i<=100 ; i++){
            if(i%2==0){
                System.out.println(i);
            }
        }
    }
}
