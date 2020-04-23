package com.mutiplethread.demo;

import com.mutiplethread.demo.thread.ConditionNotifyThread;
import com.mutiplethread.demo.thread.ConditionWaitThread;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionDemo
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/23 14:43
 * @Version V1.0
 **/
public class ConditionDemo {

    @Test
    public void testCondition() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread waitThread = new ConditionWaitThread(lock, condition);
        Thread notiyThread = new ConditionNotifyThread(lock, condition);

        waitThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notiyThread.start();
    }

    @Test
    public void testCondition2() {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();

        Thread waitThreadA = new ConditionWaitThread(lock, conditionA);
        Thread waitThreadB = new ConditionWaitThread(lock, conditionB);

        Thread notiyThread = new ConditionNotifyThread(lock, conditionA);

        waitThreadA.start();
        waitThreadB.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notiyThread.start();
    }
}
