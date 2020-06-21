package com.mutiplethread.demo;

import com.mutiplethread.demo.config.AsyncCall;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName FutureDemo
 * @Description: TODO 使用future实现异步转同步
 * @Author lxc
 * @Date 2020/6/21 15:18
 * @Version V1.0
 **/
public class FutureDemo {

    private AsyncCall asyncCall = new AsyncCall();

    public Future<Long> call(){
        Future<Long> future = asyncCall.futureCall();
        asyncCall.shutdown();
        return future;
    }

    @Test
    public void testFuture(){
        FutureDemo futureDemo = new FutureDemo();

        System.out.println(Thread.currentThread().getName()+"发起调用");
        Future<Long> future = futureDemo.call();
        System.out.println(Thread.currentThread().getName()+"返回结果");

        while (!future.isDone() && !future.isCancelled());

        try{
            System.out.println(future.get());
        }catch (InterruptedException| ExecutionException exception){
            exception.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"主线程内容");
    }
}
