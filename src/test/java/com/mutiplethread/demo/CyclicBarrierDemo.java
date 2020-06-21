package com.mutiplethread.demo;

import com.mutiplethread.demo.base.AbstractBaseDemo;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierDemo
 * @Description: TODO
 * @Author lxc
 * @Date 2020/6/21 15:35
 * @Version V1.0
 **/
public class CyclicBarrierDemo extends AbstractBaseDemo {

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    @Override
    public void callback(long response) {
        System.out.println(Thread.currentThread().getName()+"得到结果");
        System.out.println(response);
        System.out.println(Thread.currentThread().getName()+"调用结果");
    }

    @Test
    public void testCyclicBarrier(){
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.call();

        try {
            cyclicBarrierDemo.cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"主线程内容");
    }
}
