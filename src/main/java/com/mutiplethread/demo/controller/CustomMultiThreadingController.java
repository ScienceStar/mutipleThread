package com.mutiplethread.demo.controller;

import com.mutiplethread.demo.service.CustomMultiThreadingService;
import com.mutiplethread.demo.service.CustomMultipleThreadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName CustomMultiThreadingController
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/2 16:01
 * @Version V1.0
 **/
@Controller
@RequestMapping(value="/mutipleThread")
public class CustomMultiThreadingController {
    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;

    @Autowired
    private CustomMultipleThreadingService customMultipleThreadingService;

    @ResponseBody
    @RequestMapping(value="/dotask")
    public String doTask() {
        for (int i=0;i<10;i++){
            customMultiThreadingService.executeAysncTask1(i);
            customMultiThreadingService.executeAsyncTask2(i);
            customMultiThreadingService.executeAsyncTask2(i+1);
        }

        return "success";
    }

    @ResponseBody
    @RequestMapping(value="/dojob")
    public String doJob() {
        for (int i=0;i<10;i++){
            customMultipleThreadingService.executeAysncTask1(i);
            customMultipleThreadingService.executeAsyncTask2(i);
            customMultipleThreadingService.executeAsyncTask3(i);
        }
        return "success";
    }
}
