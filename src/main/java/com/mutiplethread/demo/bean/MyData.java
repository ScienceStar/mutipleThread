package com.mutiplethread.demo.bean;

/**
 * @ClassName MyData
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/17 17:32
 * @Version V1.0
 **/
public class MyData {
    volatile public int value =0;
    //每个线程都有一个自己主内存中值的一份拷贝，默认使用都是自己内存的值。
    //描述的场景是:在main线程中创建一个线程，通过启动调用修改mydata.value值 然后看主内存线程是否可以读取到
    //updateThread线程的值，如果不可以 说明线程之间的值是不可见的。
    //加上volatile关键字 就可以看到volatile保证了线程之间的可见性。

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addPlus(){
        value++;
    }

    public void setValue() {
        value=100;
    }
}
