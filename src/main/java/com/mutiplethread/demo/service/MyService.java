package com.mutiplethread.demo.service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName MyService
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/23 15:17
 * @Version V1.0
 **/
public class MyService {
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void testMethod() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("await时间为:"+System.currentTimeMillis());
                condition.await();
                System.out.println("ThreadName=" + Thread.currentThread().getName() + (" " + (i + 1)));
                System.out.println("This is the world after condition.await,run it after condition.signal method");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
