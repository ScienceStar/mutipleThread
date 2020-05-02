package com.mutiplethread.demo;

import com.mutiplethread.demo.thread.ExtrualThread;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadTest
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/30 17:18
 * @Version V1.0
 **/
public class ThreadTest {

    public static void main(String[] args) {
        ExtrualThread extrualThread = new ExtrualThread();
        extrualThread.run();
        System.out.println("main");
        String[] userInfos = {"tom", "jack", "mike"};
        List<String> userCollection = Arrays.asList(userInfos);
        userCollection.forEach(System.out::println);
    }

    @Test
    public void threadPoolTest() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));

        for (int i = 0; i < 120; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println("线程Id:" + Thread.currentThread().getId() + "is running.......");
                try {
                    for (int j = 0; j < 5000; j++) {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
