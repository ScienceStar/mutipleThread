package com.mutiplethread.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ThreadTest4
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 14:54
 * @Version V1.0
 **/
public class ThreadTest4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest4.class);

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
                        LOGGER.error("InterruptedException error", e);
                    }
                }
            }
        }, "A");
        threadA.start();

        // 睡眠两秒钟发出中断信号
        Thread.sleep(2 * 1000);
        threadA.interrupt();
    }
}
