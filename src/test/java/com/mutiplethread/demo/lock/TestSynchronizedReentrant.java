package com.mutiplethread.demo.lock;

import org.junit.Test;

public class TestSynchronizedReentrant {

    @Test
    public void testSynchronized(){
        Thread thread = new Thread(new SynchronizedReentrant());
        thread.start();
    }
}
