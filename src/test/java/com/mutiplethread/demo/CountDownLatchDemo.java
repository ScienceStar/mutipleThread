package com.mutiplethread.demo;

import com.mutiplethread.demo.base.AbstractBaseDemo;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchDemo
 * @Description: TODO CountDownLatch实现异步转同步
 * @Author lxc
 * @Date 2020/6/21 15:29
 * @Version V1.0
 **/
public class CountDownLatchDemo extends AbstractBaseDemo {
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void callback(long response) {
        System.out.println(Thread.currentThread().getName()+"得到结果");
        System.out.println(response);
        System.out.println(Thread.currentThread().getName()+"调用结束");
        countDownLatch.countDown();
    }

    @Test
    public void testCountDownLatch(){
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();

        countDownLatchDemo.call();

        try {
            countDownLatchDemo.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"主线程内容");
    }
}
