package com.mutiplethread.demo.lock;

import org.junit.Test;

public class TestLockNonReentrant {

    @Test
    public void testNonLock(){
        Thread thread = new Thread(new LockNonReentrant());
        thread.start();
    }
}
