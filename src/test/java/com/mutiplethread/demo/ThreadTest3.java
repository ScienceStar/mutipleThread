package com.mutiplethread.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ThreadTest3
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 14:51
 * @Version V1.0
 **/
public class ThreadTest3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest3.class);

    public static void main(String[] args) throws InterruptedException {
        final List<String> list = new ArrayList<String>();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    try {
                        for (int i = 0; i < 10; i++) {
                            list.add(i + "");
                            LOGGER.debug("add ele -> {}", i);
                            if (list.size() == 5) {
                                LOGGER.debug("wait---------------");
                                list.wait();
                            }
                        }
                    } catch (InterruptedException e) {
                        LOGGER.error("InterruptedException error");
                    }
                }
            }
        }, "A");
        threadA.start();

        // 开启一个线程占用锁之后唤醒一个线程
        Thread.sleep(1);
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    LOGGER.debug("run,threadName->{}", Thread.currentThread().getName());
                    list.notify();
                    LOGGER.debug("threadName->{}, notify", Thread.currentThread().getName());
                }
            }
        }, "B");
        threadB.start();
    }
}
