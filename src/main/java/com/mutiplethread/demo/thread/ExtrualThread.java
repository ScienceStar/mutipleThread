package com.mutiplethread.demo.thread;

/**
 * @ClassName ExtrualThread
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/30 17:16
 * @Version V1.0
 **/
public class ExtrualThread extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Thread");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
