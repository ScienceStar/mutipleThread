package com.mutiplethread.demo.thread;

/**
 * @ClassName Single_Thread
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/18 13:46
 * @Version V1.0
 **/
public class Single_Thread extends Thread{
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count =0;

        for (int i = 0; i < 5000000; i++) {
            Thread.yield();
            count= count+(i+1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime-beginTime)+"ms"+"           运行总数:"+count);
    }
}
