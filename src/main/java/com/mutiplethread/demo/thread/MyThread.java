package com.mutiplethread.demo.thread;

import com.mutiplethread.demo.service.MyService;

/**
 * @ClassName MyThread
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/23 15:20
 * @Version V1.0
 **/
public class MyThread extends Thread{
    private MyService service;

    public MyThread(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }

    static public class cycleThread extends Thread{

    }
}
