package com.bg.phrobe;

import com.bg.phrobe.entities.User;
import com.bg.phrobe.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);


        UserRepository repository = context.getBean(UserRepository.class);

        // save a couple of customers
        repository.save(new User("user", "pass"));
    }
}