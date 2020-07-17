package com.mutiplethread.demo.thread;

/**
 * @ClassName VolatileCountThread
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/17 16:47
 * @Version V1.0
 **/
public class VolatileCountThread extends Thread {
    volatile private static int count = 0;

    public VolatileCountThread() {
    }

    public VolatileCountThread(Thread thread) {
        super(thread);
    }

    public static void addNumber() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("Thread:" + Thread.currentThread().getName() + "Count End. " + count);
    }

    public void run() {
        addNumber();
    }
}
