package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2020/3/18 9:49
 */
public interface PersonRepository extends JpaRepository<Person, Id> {
}
