package com.mutiplethread.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionWaitThread
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/23 14:40
 * @Version V1.0
 **/
public class ConditionWaitThread extends Thread{
    ReentrantLock lock;
    Condition condition;
    public ConditionWaitThread(ReentrantLock lock,Condition condition){
        this.lock = lock;
        this.condition = condition;
    }
    // 等待线程
    @Override
    public void run(){
        try{
            lock.lock();
            System.out.println("Thread: "+Thread.currentThread().getName()+System.currentTimeMillis()+" Await begin.");
            condition.await();
            System.out.println("Thread: "+Thread.currentThread().getName()+System.currentTimeMillis()+" Await end.");
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
