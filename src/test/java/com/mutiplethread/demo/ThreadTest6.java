package com.mutiplethread.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ThreadTest6
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 15:11
 * @Version V1.0
 **/
public class ThreadTest6 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest6.class);

    public static void main(String[] args) throws InterruptedException {
        final Object object = new Object();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        Thread.sleep(5 * 1000);
                        LOGGER.info("wait start---------------,threadName->{}", Thread.currentThread().getName());
                        // 等待10秒钟自动唤醒
                        object.wait(5 * 1000);
                        LOGGER.info("wait end---------------,threadName->{}", Thread.currentThread().getName());
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        LOGGER.error("InterruptedException error", e);
                    }
                }
            }
        }, "A");
        threadA.start();

        Thread.sleep(2 * 1000);
        LOGGER.info("threadA state->{}", threadA.getState());
        Thread.sleep(5 * 1000);
        LOGGER.info("threadA state->{}", threadA.getState());
        Thread.sleep(5 * 1000);
        LOGGER.info("threadA state->{}", threadA.getState());
        Thread.sleep(5 * 1000);
        LOGGER.info("threadA state->{}", threadA.getState());
    }
}
