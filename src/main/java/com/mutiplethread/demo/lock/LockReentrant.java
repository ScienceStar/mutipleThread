package com.mutiplethread.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockReentrant implements Runnable{
    private final Lock lock = new ReentrantLock();

    /**
     * 方法1，调用方法2
     */
    public void method1() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " method1()");
            method2();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 方法2，打印前获取 obj 锁
     * 如果同一线程，锁不可重入的话，method2 需要等待 method1 释放 obj 锁
     */
    public void method2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " method2()");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        //线程启动 执行方法1
        method1();
    }

}
