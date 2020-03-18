package com.example.demo.runner;

import com.example.demo.DemoApplication;
import com.example.demo.model.Person;
import com.example.demo.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2020/3/18 13:37
 */
public class PersonExecutorRunner implements Runnable {
    private List<Person> list;

    public PersonExecutorRunner(List list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public void run() {
        saveWithJdbc(list);
    }

    private void saveWithJdbc(List<Person> list) {
        System.out.println(Thread.currentThread().getName() + "   " + list.size());
        if(list.size()==0){
            return;
        }
        String sql = "insert into person(name,age) values(?,?)";
        JdbcTemplate jdbcTemplate = SpringUtil.getBean(JdbcTemplate.class);
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setLong(1,list.get(i).getId());
                ps.setString(1, list.get(i).getName());
                ps.setInt(2, list.get(i).getAge());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });

    }
}
