package com.mutiplethread.demo;

import com.mutiplethread.demo.base.AbstractBaseDemo;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockDemo
 * @Description: TODO 使用条件锁🔒
 * @Author lxc
 * @Date 2020/6/21 15:11
 * @Version V1.0
 **/
public class ReentrantLockDemo extends AbstractBaseDemo {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    @Override
    public void callback(long response) {
        System.out.println(Thread.currentThread().getName() + "得到结果");
        System.out.println(response);
        System.out.println(Thread.currentThread().getName() + "调用结束");

        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testReentrantLoock() {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        demo.call();

        demo.lock.lock();

        try {
            demo.condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            demo.lock.unlock();
        }

        System.out.println(Thread.currentThread().getName() + "主线程内容");
    }
}
