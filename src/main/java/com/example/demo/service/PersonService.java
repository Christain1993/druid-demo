package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.runner.SaveRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2020/3/18 10:07
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional
    public void save() {
        StopWatch stopWatch = new StopWatch();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Person person = new Person();
            person.setId(Long.valueOf(i));
            person.setName(String.valueOf(i));
            person.setAge(18);
            list.add(person);
        }
        stopWatch.start();
        repository.saveAll(list);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SaveRunner saveRunner;

    @Transactional
    public void saveAll() {

        StopWatch stopWatch = new StopWatch();
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            Person person = new Person();
//            person.setId(Long.valueOf(i));
            person.setName(String.valueOf(i));
            person.setAge(18);
            saveRunner.queue.offer(person);
        }
    }


}
