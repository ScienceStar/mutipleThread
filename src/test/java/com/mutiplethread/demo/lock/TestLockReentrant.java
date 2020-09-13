package com.mutiplethread.demo.lock;

import org.junit.Test;

public class TestLockReentrant {

    @Test
    public void testReentrantLock(){
        Thread thread = new Thread(new LockReentrant());
        thread.start();
    }
}
