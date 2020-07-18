package com.mutiplethread.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ThreadTest7
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 15:13
 * @Version V1.0
 **/
public class ThreadTest7 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest7.class);

    public static void main(String[] args) throws InterruptedException {
        final Object object = new Object();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    LOGGER.info("notify start---------------,threadName->{}", Thread.currentThread().getName());
                    // 等待10秒钟自动唤醒
                    object.notify();
                    LOGGER.info("notify end---------------,threadName->{}", Thread.currentThread().getName());
                }
            }
        }, "A");
        threadA.start();

        Thread.sleep(1 * 1000);
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        LOGGER.info("wait start---------------,threadName->{}", Thread.currentThread().getName());
                        // 等待10秒钟自动唤醒
                        object.wait();
                        LOGGER.info("wait end---------------,threadName->{}", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        LOGGER.error("InterruptedException error", e);
                    }
                }
            }
        }, "B");
        threadB.start();
    }
}
