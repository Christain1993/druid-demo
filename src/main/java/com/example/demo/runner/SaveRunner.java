package com.example.demo.runner;

import com.example.demo.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2020/3/18 11:48
 */
@Component
@Async
public class SaveRunner implements ApplicationRunner {

    public LinkedBlockingQueue<Person> queue ;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @PostConstruct
    public void init(){
        queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        while(!Thread.interrupted()){
            if(queue.size()<5000){
                TimeUnit.SECONDS.sleep(1);
            }
            List<Person> list = new ArrayList<>();
            queue.drainTo(list,5000);
            executorService.execute(new PersonExecutorRunner(list));
            list.clear();
        }
    }
}
