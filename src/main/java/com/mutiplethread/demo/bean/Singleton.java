package com.mutiplethread.demo.bean;

/**
 * @ClassName Singleton
 * @Description: TODO
 * @Author lxc 单例模式
 * @Date 2020/5/6 11:24
 * @Version V1.0
 **/
public class Singleton {
    private volatile static Singleton instance = null;

    private Singleton(){};

    public static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
