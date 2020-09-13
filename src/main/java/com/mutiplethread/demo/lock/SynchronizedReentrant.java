package com.mutiplethread.demo.lock;

public class SynchronizedReentrant implements Runnable{
    private final Object obj = new Object();

    /**
     * 方法1，调用方法2
     */
    public void method1() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " method1()");
            method2();
        }
    }

    /**
     * 方法2，打印前获取 obj 锁
     * 如果同一线程，锁不可重入的话，method2 需要等待 method1 释放 obj 锁
     */
    public void method2() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " method2()");
        }
    }

    @Override
    public void run() {
        //线程启动 执行方法1
        method1();
    }
}
