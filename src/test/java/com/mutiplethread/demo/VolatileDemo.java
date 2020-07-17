package com.mutiplethread.demo;

import com.mutiplethread.demo.bean.MyData;
import org.junit.Test;

/**
 * @ClassName VolatileDemo
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/17 17:35
 * @Version V1.0
 **/
public class VolatileDemo {

    @Test
    public void volatileTest() {
        MyData myData = new MyData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程加入
                System.out.println(Thread.currentThread().getName() + "update value Thread join!");
                try {
                    //线程睡眠3秒钟
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myData.setValue();
                System.out.println(Thread.currentThread().getName() + "update value success!");
            }
        }, "update Thread").start();

        while (myData.value == 0) {
            //空等待
        }

        System.out.println(Thread.currentThread().getName() + " game over!");
    }

    @Test
    public void unDisplayTest() {
        MyData myData = new MyData();
        //开启20个线程 i++操作
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                }
            }).start();
        }
        //如果当前线程大于2 说明除了主线程和垃圾回收线程还有别的线程在运行
        //因此mian线程等待其他线程执行完毕之后 在执行main线程的代码
        while (Thread.activeCount() > 2) {
            Thread.yield();//主线程让步
        }
        System.out.println(Thread.currentThread().getName() + "\t" + myData.value);
    }
}
