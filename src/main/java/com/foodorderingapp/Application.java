package com.foodorderingapp;

import com.foodorderingapp.config.YAMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
//@EnableConfigurationProperties(YAMLConfig.class)
public class Application implements CommandLineRunner{
    public static void main(String[] args) {

        SpringApplication.run(Application.class,args);
    }

    @Autowired
    private YAMLConfig myConfig;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void run(String... args) throws Exception {
        System.out.println("driver : " + myConfig.getDriver());
        System.out.println("url: " + myConfig.getUrl());
        System.out.println("username: " + myConfig.getUsername());
        System.out.println("password: " + myConfig.getPassword());
        System.out.println("dialect: " + myConfig.getDialect());
    }
}
