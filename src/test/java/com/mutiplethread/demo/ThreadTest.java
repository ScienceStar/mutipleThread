package com.mutiplethread.demo;

import com.mutiplethread.demo.bean.Person;
import com.mutiplethread.demo.thread.ExtrualThread;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        String[] userInfos = {"tom", "jack", "mike"};
        List<String> userCollection = Arrays.asList(userInfos);
        userCollection.forEach(System.out::println);
    }

    @Test
    public void threadPoolTest() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));

        for (int i = 0; i < 120; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println("线程Id:" + Thread.currentThread().getId() + "is running.......");
                try {
                    for (int j = 0; j < 5000; j++) {
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test
    public void hashSetTest(){
        Set<Person> personSet = new HashSet<Person>();

        Person person1 = new Person("jack",21);
        Person person2 = new Person("tom",22);

        List<Person> personList = new ArrayList<Person>();
        personList.add(person1);
        personList.add(person2);

        personSet.addAll(personList);

        for(Person person : personSet){
            System.out.println(person.getPersonName()+"   "+person.getPersonAge());
        }
    }

    @Test
    public void hashMapTest(){
        Map<String,Person> map = new HashMap<String, Person>();

        Person person1 = new Person("jack",21);
        Person person2 = new Person("tom",22);

        map.put("jack",person1);
        map.put("tom",person2);

        for(String key:map.keySet()){
            Person p = map.get(key);
            System.out.println(p.getPersonName()+"  "+p.getPersonAge());
        }
    }

    @Test
    public void hashTableTest(){
        Map<String, Person> map = new Hashtable<String, Person>();
        Person person1 = new Person("jack",21);
        Person person2 = new Person("tom",22);

        map.put("jack",person1);
        map.put("tom",person2);

        for(String key:map.keySet()){
            Person p = map.get(key);
            System.out.println(p.getPersonName()+"  "+p.getPersonAge());
        }
    }

    @Test
    public void currentHashMapTest(){
        Map<String,Person> map = new ConcurrentHashMap<String, Person>();

        Person person1 = new Person("jack",21);
        Person person2 = new Person("tom",22);

        map.put("jack",person1);
        map.put("tom",person2);

        for(String key:map.keySet()){
            Person p = map.get(key);
            System.out.println(p.getPersonName()+"  "+p.getPersonAge());
        }
    }
}
