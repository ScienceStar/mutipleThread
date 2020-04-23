package com.mutiplethread.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        ReentrantLock fairLock  = new ReentrantLock(true);

        Thread threadA = new FairLockThread(fairLock);
        Thread threadB = new FairLockThread(fairLock);
        Thread threadC = new FairLockThread(fairLock);
        Thread threadD = new FairLockThread(fairLock);
        Thread threadE = new FairLockThread(fairLock);
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadE.start();
    }

}
