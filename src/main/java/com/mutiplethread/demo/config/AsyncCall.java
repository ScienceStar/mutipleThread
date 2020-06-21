package com.mutiplethread.demo.config;

import com.mutiplethread.demo.base.AbstractBaseDemo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName AsyncCall
 * @Description: TODO 异步调用类
 * @Author lxc
 * @Date 2020/6/21 14:46
 * @Version V1.0
 **/
public class AsyncCall {

    private Random random = new Random(System.currentTimeMillis());

    private ExecutorService tp = Executors.newSingleThreadExecutor();

    public void call(AbstractBaseDemo demo) {
        new Thread(() -> {
            long res = random.nextInt(10);
            try {
                Thread.sleep(res * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            demo.callback(res);
        }).start();
    }

    public Future<Long> futureCall() {
        return tp.submit(() -> {
            long res = random.nextInt(10);

            Thread.sleep(res * 1000);
            return res;
        });
    }

    public void shutdown() {
        tp.shutdown();
    }
}
