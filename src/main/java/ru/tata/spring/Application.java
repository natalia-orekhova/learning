package ru.tata.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@ComponentScan(basePackages = {"ru.tata.spring"})
@EntityScan(basePackages = {"ru.tata.spring.model"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
