package com.example.demo;

import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

    public static ApplicationContext context ;

    public static void main(String[] args) {
        context = SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService personService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        personService.saveAll();
    }
}
