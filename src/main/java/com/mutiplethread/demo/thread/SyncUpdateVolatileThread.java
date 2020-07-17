package com.mutiplethread.demo.thread;

/**
 * @ClassName SyncUpdateVolatileThread
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/17 16:52
 * @Version V1.0
 **/
public class SyncUpdateVolatileThread extends Thread{

    volatile public boolean flag = true;
    @Override
    public void run() {
        while (flag){
            System.out.println("i am running.........");
        }
    }
}
