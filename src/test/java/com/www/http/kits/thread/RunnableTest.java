package com.www.http.kits.thread;



/**
 * 创建多线程的方式:
 *      1.继承Thread类
 *      2.实现Runnable接口
 *      3.通过collable和Future
 *      4.通过线程池
 */
//方式2: 实现Runnable接口
/*
解决线程不安全的方法: 1.synchronized( 同步监视器 ){ 同步代码块 }  //同步代码块,就是操作共享数据的代码
                        1.同步监视器,就是锁,任何一个类的对象都可以充当锁.多个线程必须共用同一把锁
                        2.共享数据: 多个线程共同操作的变量
                        3.操作共享数据的代码,就是需要被同步的代码
                        4.同步代码块的实际上只让一个线程参与,其他线程等待,相当于一个单线程的过程.效率低
                   2.同步,如果操作共享数据的代码都在某个方法中,我们可以写成public synchronized void run(){ }

* */
public class RunnableTest  {
    public static void main(String[] args) {
        //1.创建Runnable接口的实现类对象
        Window1 window1 = new Window1();

        //把实现类对象传入到Thread类的构造器中,创建线程对象
        Thread thread1 = new Thread(window1);
        Thread thread2 = new Thread(window1);
        Thread thread3 = new Thread(window1);

        //设置分线程的名称
        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        //调用线程中的start()方法,执行实现Runnable接口中的实现类中重写的run()方法
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
class Window1 implements Runnable{

    private int ticket = 1000;
    Object object= new Object();
    @Override
    public void run() {
//        Object object= new Object();注意这里的object对象的位置,放在这里就不对
        while (true){
            synchronized (object){ //这里也可以写Window1.class .因为类也是对象.如果依然发现有线程不安全的问题,那就要考虑是否各个线程用的锁是同一把锁

                if(ticket>0){
                    try {
                        Thread.sleep(10); //sleep()方法会抛异常,我们用try...catch处理     sleep()方法提高了出现0和-1的概率
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 卖票,票号为: "+ ticket);
                    ticket--;
                }else {
                    break ;
                }
            }
        }
    }
}