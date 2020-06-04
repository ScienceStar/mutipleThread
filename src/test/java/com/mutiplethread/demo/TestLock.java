package com.mutiplethread.demo;

import com.mutiplethread.demo.bean.Person;
import com.mutiplethread.demo.service.MyService;
import com.mutiplethread.demo.thread.MyThread;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TestLock
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/23 15:00
 * @Version V1.0
 **/
public class TestLock {
    Lock lock = new ReentrantLock();

    Condition a = lock.newCondition();
    Condition b = lock.newCondition();

    public static void main(String[] args) {
        MyService service = new MyService();

        MyThread a1 = new MyThread(service);
        MyThread a2 = new MyThread(service);
        MyThread a3 = new MyThread(service);
        MyThread a4 = new MyThread(service);
        MyThread a5 = new MyThread(service);

        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();

        new Person("jack", 21).eat();
        new Person("tom", 33).eat("菠菜", 21);
    }

    @Test
    public void cacluateChar() {
        String str = "ladksksdfdswelslfds";

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }

        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<Character, Integer>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> next = iterator.next();
            System.out.println(next.getKey() + " " + next.getValue());
        }
    }

    @Test
    public void classLoadTest() throws ClassNotFoundException {
        ClassLoader classLoader = Person.class.getClassLoader();
        System.out.println(classLoader);
        classLoader.loadClass("com.mutiplethread.demo.bean.Person");
    }
}
