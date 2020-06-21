package com.mutiplethread.demo;

import com.mutiplethread.demo.base.AbstractBaseDemo;
import org.junit.Test;

/**
 * @ClassName WaitLockDemo
 * @Description: TODO
 * @Author lxc
 * @Date 2020/6/21 14:52
 * @Version V1.0
 **/
public class WaitLockDemo extends AbstractBaseDemo {
    private final Object lock = new Object();

    @Override
    public void callback(long response) {
        System.out.println(Thread.currentThread().getName() + "得到结果");
        System.out.println(response);
        System.out.println(Thread.currentThread().getName() + "调用结束");

        synchronized (lock) {
            lock.notifyAll();
        }
    }

    @Test
    public void testWaitLock() {
        WaitLockDemo waitLockDemo = new WaitLockDemo();
        waitLockDemo.call();

        synchronized (waitLockDemo.lock) {
            try {
                waitLockDemo.lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "主线程内容");
    }
}
