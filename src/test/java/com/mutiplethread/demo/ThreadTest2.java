package com.mutiplethread.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ThreadTest2
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 14:38
 * @Version V1.0
 **/
public class ThreadTest2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest2.class);

    public static void main(String[] args) throws InterruptedException {
        final Object object = new Object();

        Thread threadA = new Thread(() -> {
            synchronized (object) {
                try {
                    LOGGER.debug("进入同步代码块，准备wait(),threadName->{}", Thread.currentThread().getName());
                    Thread.sleep(1 * 1000);
                    object.wait();
                    LOGGER.debug("退出同步代码块，wait()结束,threadName->{}", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    LOGGER.error("InterruptedException error");
                }
            }
        }, "A");

        threadA.start();

        Thread.sleep(1);
        Thread threadB = new Thread(() -> {
            synchronized (object) {
                try {
                    LOGGER.debug("进入同步代码块，准备wait(),threadName->{}", Thread.currentThread().getName());
                    Thread.sleep(1 * 1000);
                    object.wait();
                    LOGGER.debug("退出同步代码块，wait()结束,threadName->{}", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    LOGGER.error("InterruptedException error");
                }
            }
        }, "B");
        threadB.start();

        Thread.sleep(1);
        Thread threadC = new Thread(() -> {
            synchronized (object) {
                try {
                    LOGGER.debug("进入同步代码块，准备notify(),threadName->{}", Thread.currentThread().getName());
                    Thread.sleep(1 * 1000);
                    object.notify();
                    LOGGER.debug("退出同步代码块，notify()结束,threadName->{}", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    LOGGER.error("InterruptedException error");
                }
            }
        }, "C");
        threadC.start();

        Thread.sleep(5 * 1000);
        LOGGER.debug("A线程状态->{}", threadA.getState());
        LOGGER.debug("B线程状态->{}", threadB.getState());
        LOGGER.debug("C线程状态->{}", threadC.getState());
    }
}
