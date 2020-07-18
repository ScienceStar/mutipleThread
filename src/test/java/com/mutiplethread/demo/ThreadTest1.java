package com.mutiplethread.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ThreadTest1
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 14:32
 * @Version V1.0
 **/
public class ThreadTest1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Communication.class);

    private static volatile List list = new ArrayList<>();// 加volatile关键字是为了每次从主存中读取数据，否则两个线程读取到的不一致

    public static void main(String[] args) {
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
