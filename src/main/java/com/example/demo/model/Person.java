package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description:
 * @Author: zhaoyu
 * @CreateDate: 2020/3/18 9:48
 */
@Entity
@Table
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
}
