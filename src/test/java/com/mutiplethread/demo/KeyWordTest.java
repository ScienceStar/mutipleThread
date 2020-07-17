package com.mutiplethread.demo;

import com.mutiplethread.demo.thread.SyncUpdateVolatileThread;
import com.mutiplethread.demo.thread.VolatileCountThread;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName VolatileTest
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/17 16:48
 * @Version V1.0
 **/
public class KeyWordTest {

    @Test
    public void volatileCountAdd() {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            VolatileCountThread thread = new VolatileCountThread();
            list.add(thread);
        }

        for (Thread thread : list) {
            thread.start();
        }
    }

    @Test
    public void syncUpdateVolatile() {
        SyncUpdateVolatileThread thread = new SyncUpdateVolatileThread();
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.flag = false;
    }
}
