package com.mutiplethread.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName VolatileTest
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 15:29
 * @Version V1.0
 **/
public class VolatileTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileTest.class);

    public volatile static boolean isOddThread = true; // 标记是不是奇数线程

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        //10个奇数线程打印☆
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (lock) {
                            while (!isOddThread) {
                                lock.wait();
                            }
                            //LOGGER.info("ThreadName->{}, print ->{}", Thread.currentThread().getName(), "☆");
                            System.out.print("☆");
                            isOddThread = false;
                            lock.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "odd" + i).start();
        }

        //10个偶数线程打印★
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (lock) {
                            while (isOddThread) {
                                lock.wait();
                            }
                            //LOGGER.info("ThreadName->{}, print ->{}", Thread.currentThread().getName(), "★");
                            System.out.print("★");
                            isOddThread = true;
                            lock.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "even" + i).start();
        }

    }
}
