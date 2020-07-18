package com.mutiplethread.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName YieldTest
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 13:51
 * @Version V1.0
 **/
public class Communication {

    public static void main(String[] args) {
        /*Single_Thread single_thread = new Single_Thread();
        single_thread.start();*/
    }

    @Test
    public void sleepTest() {
        long beginTime = System.currentTimeMillis();
        Thread single_thread = new Thread(() -> {
            try {
                System.out.println("run threadName=" + Thread.currentThread().getName() + " begin");
                Thread.sleep(2000);
                System.out.println("run threadName=" + Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("thread run done......");
            }
        });
        single_thread.start();
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime - beginTime));
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(Communication.class);

    private static volatile List list = new ArrayList<>();// 加volatile关键字是为了每次从主存中读取数据，否则两个线程读取到的不一致
    @Test
    public void threadTest1(){
        Thread thread1 = new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    LOGGER.debug("add ele ->{},threadName->{}", i, Thread.currentThread().getName());
                    list.add(String.valueOf(i));
                    Thread.sleep(1 * 1000);
                }
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException", e);
            }
        },"A");
        thread1.start();

        Thread thread2 = new Thread(()->{
            try {
                while (true) {
                    if (list.size() == 5) {
                        LOGGER.debug("list大小为5 了，线程B要退出了");
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.error("抛出异常，线程退出", e);
            }
        },"B");
        thread2.start();
    }
}
