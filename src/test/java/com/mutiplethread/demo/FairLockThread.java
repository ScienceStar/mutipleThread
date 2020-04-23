package com.mutiplethread.demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName FairLockThread
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/23 14:08
 * @Version V1.0
 **/
public class FairLockThread extends Thread{
    private ReentrantLock lock;
    public FairLockThread(ReentrantLock lock){
        this.lock = lock;
    }

    public void run(){
        try{
            lock.lock();
            System.out.println("Thread: "+Thread.currentThread().getName()+System.currentTimeMillis());
        }finally{
            lock.unlock();
        }
    }
}
