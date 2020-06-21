package com.mutiplethread.demo;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.mutiplethread.demo.bean.Person;
import com.mutiplethread.demo.thread.ExtrualThread;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

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
    public void hashSetTest() {

        Person person1 = new Person("jack", 21);
        Person person2 = new Person("tom", 22);

        List<Person> personList = new ArrayList<Person>();
        personList.add(person1);
        personList.add(person2);

        Set<Person> personSet = new HashSet<Person>(personList);

        for (Person person : personSet) {
            System.out.println(person.getPersonName() + "   " + person.getPersonAge());
        }
    }

    @Test
    public void hashMapTest() {
        Map<String, Person> map = new HashMap<String, Person>();

        Person person1 = new Person("jack", 21);
        Person person2 = new Person("tom", 22);

        map.put("jack", person1);
        map.put("tom", person2);

        for (String key : map.keySet()) {
            Person p = map.get(key);
            System.out.println(p.getPersonName() + "  " + p.getPersonAge());
        }
    }

    @Test
    public void hashTableTest() {
        Map<String, Person> map = new Hashtable<String, Person>();
        Person person1 = new Person("jack", 21);
        Person person2 = new Person("tom", 22);

        map.put("jack", person1);
        map.put("tom", person2);

        for (String key : map.keySet()) {
            Person p = map.get(key);
            System.out.println(p.getPersonName() + "  " + p.getPersonAge());
        }
    }

    @Test
    public void currentHashMapTest() {
        Map<String, Person> map = new ConcurrentHashMap<String, Person>();

        Person person1 = new Person("jack", 21);
        Person person2 = new Person("tom", 22);

        map.put("jack", person1);
        map.put("tom", person2);
        for (String key : map.keySet()) {
            Person p = map.get(key);
            System.out.println(p.getPersonName() + "  " + p.getPersonAge());
        }
    }

    private static int size = 1000000;
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.01);

    /**
     * bloom过滤器测试
     */
    @Test
    public void bloomFilterTest() {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<Integer>(1000);
        //故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }

    @Test
    public void runnableThread() {
        MyRunnabale runnabale = new MyRunnabale();
        Thread t1 = new Thread(runnabale, "线程1");
        Thread t2 = new Thread(runnabale, "线程2");
        Thread t3 = new Thread(runnabale, "线程3");

        t1.start();
        t2.start();
        t3.start();
    }

    @Test
    public void cacheThreadTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            executorService.submit(new MyRunnabale());
        }
        executorService.shutdown();
    }
}

class MyRunnabale implements Runnable {
    private int ticket = 10;

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 20; i++) {
                if (this.ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "买票:ticket" + this.ticket--);
                }
            }
        }
    }
}
