package com.mutiplethread.demo;

import com.mutiplethread.demo.thread.ExtrualThread;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ThreadTest
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/30 17:18
 * @Version V1.0
 **/
public class ThreadTest {

    public static void main(String[] args) {
        ExtrualThread extrualThread = new ExtrualThread();
        extrualThread.run();
        System.out.println("main");
        String[] userInfos = {"tom","jack","mike"};
        List<String> userCollection = Arrays.asList(userInfos);
        userCollection.forEach(System.out::println);
    }
}
