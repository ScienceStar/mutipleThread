package com.mutiplethread.demo.base;

import com.mutiplethread.demo.config.AsyncCall;

/**
 * @ClassName AbstractBaseDemo
 * @Description: TODO
 * @Author lxc
 * @Date 2020/6/21 14:48
 * @Version V1.0
 **/
public abstract class AbstractBaseDemo {
    protected AsyncCall asyncCall = new AsyncCall();

    public abstract void callback(long response);

    public void call() {
        System.out.println(Thread.currentThread().getName() + "发起调用");
        asyncCall.call(this);
        System.out.println(Thread.currentThread().getName() + "调用返回");
    }
}
