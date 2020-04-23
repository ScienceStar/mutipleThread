package com.mutiplethread.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionNotifyThread
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/23 14:42
 * @Version V1.0
 **/
public class ConditionNotifyThread extends Thread{
    ReentrantLock lock;
    Condition condition;
    public ConditionNotifyThread(ReentrantLock lock,Condition condition){
        this.lock = lock;
        this.condition = condition;
    }
    // 等待线程
    @Override
    public void run(){
        try{
            lock.lock();
            System.out.println("Thread: "+Thread.currentThread().getName()+System.currentTimeMillis()+" Notify begin.");
            condition.signal();
            System.out.println("Thread: "+Thread.currentThread().getName()+System.currentTimeMillis()+" Notify end.");
        }finally{
            lock.unlock();
        }
    }
}
