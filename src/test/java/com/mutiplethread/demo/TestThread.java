package com.mutiplethread.demo;

/**
 * @ClassName TestThread
 * @Description: TODO 多线程测试
 * @Author lxc
 * @Date 2020/5/6 11:51
 * @Version V1.0
 **/
public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        int count =0;
        Aobing aobing = new Aobing();
        aobing.start();
        for(;;){
            synchronized (aobing){
                if(aobing.isFlag()){
                    System.out.println("Count 计数:"+count++);
                    Thread.sleep(50000);
                }
            }
        }
    }
}
class Aobing extends Thread{
    private volatile boolean flag = true;

    public boolean isFlag(){
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag="+flag);
    }
}
