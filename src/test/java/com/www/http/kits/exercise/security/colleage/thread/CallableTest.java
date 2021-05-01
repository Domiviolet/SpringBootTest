package com.www.http.kits.exercise.security.colleage.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 与Callable相比,Runnable功能更加强大
 *      1.它含有的Call()方法,相比run()方法,可以有返回值
 *      2.方法可以抛出异常,而run()方法不可以
 *      3.支持泛型的返回值
 *      4.需要借助FutureTask类.比如获取返回值
 *      FutureTask类是Future接口的唯一实现类,它可以对具体的Runnable,Callable任务的执行结果进行取消,查询是否完成,获取结果等
 *      FutureTask类同时实现了Runnable,Future接口,它即可以作为Runnable被线程执行,又可以作为Future得到Callable的返回值
 *
 *   具体步骤:
 *      第一步: 创建实现了Callable接口的实现类,实现Callable接口的call()方法,将子线程需要进行的操作,写在call()方法中
 *      第二步: 在另一个类中,创建Callable接口的实现类对象
 *      第三步: 将此Callable接口的实现类对象,传递到FutureTask()构造当中
 *      第四部: 将FutureTask对象传递到Thread()构造器中,创建Thread对象,并start()方法
 */
public class CallableTest  {
    public static void main(String[] args) {
        //第二步: 在另一个类中,创建Callable接口的实现类对象
        NmuThread nmuThread = new NmuThread();

        //第三步: 将此Callable接口的实现类对象,传递到FutureTask()构造当中
        FutureTask futureTask = new FutureTask(nmuThread);

      //  第四部: 将FutureTask对象传递到Thread()构造器中,创建Thread对象,并start()方法
        new Thread(futureTask).start(); //new Thread()中的参数是实现了Runnable接口的实现类的对象.而FutureTask是Java提供的Runnable接口的实现类.

        //注意这里的futureTask.get();方法抛出了异常.我们这里用到了try...catch方法
        try {
            //如果我们需要线程的返回值,就调用这个get()方法,否则不需要
            Object object = futureTask.get();
            System.out.println(object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
//第一步: 创建实现了Callable接口的实现类,实现Callable接口的call()方法,将子线程需要进行的操作,写在call()方法中
class NmuThread implements Callable<Object>{

    @Override
    public Object call() throws Exception {
        int sum = 0 ;
        for (int i = 0; i < 101; i++) {
            System.out.println(i);
           sum++ ;
        }
        return  sum ;
    }
}