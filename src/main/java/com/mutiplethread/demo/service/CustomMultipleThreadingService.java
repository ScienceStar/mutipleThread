package com.mutiplethread.demo.service;

import com.mutiplethread.demo.config.CustomAsyncScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName CustomMultipleThreadingService
 * @Description: TODO
 * @Author lxc
 * @Date 2020/4/2 16:19
 * @Version V1.0
 **/
@Service
public class CustomMultipleThreadingService {
    private Logger logger = LoggerFactory.getLogger(CustomMultipleThreadingService.class);
    /**
     * @Description:通过@Async注解表明该方法是一个异步方法，
     * 如果注解在类级别上，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     * @Title: executeAysncTask1
     * @Date: 2018年9月21日 下午2:54:32
     * @Author: OnlyMate
     * @Throws
     * @param i
     */
    @Async
    public void executeAysncTask1(Integer i){
        logger.info("CustomMultiThreadingService ==> executeAysncTask1 method: 执行异步任务{} ", i);
    }

    /**
     * @Description:通过@Async注解表明该方法是一个异步方法，
     * 如果注解在类级别上，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     * @Title: executeAsyncTask2
     * @Date: 2018年9月21日 下午2:55:04
     * @Author: OnlyMate
     * @Throws
     * @param i
     */
    @Async
    public void executeAsyncTask2(Integer i){
        logger.info("CustomMultiThreadingService ==> executeAsyncTask2 method: 执行异步任务{} ", i);
    }

    /**
     * @Description: 异步线程调度管理器创建线程任务
     * @Title: executeAsyncTask3
     * @Date: 2018年9月21日 下午3:32:28
     * @Author: OnlyMate
     * @Throws
     * @param i
     */
    public void executeAsyncTask3(Integer i){
        CustomAsyncScheduler.getInstance().getChnlBackendQueryPool().execute(new Runnable() {
            @Override
            public void run() {
                logger.info("CustomMultiThreadingService ==> executeAsyncTask3 method: 执行异步任务{} ", i);
            }
        });

    }
}
