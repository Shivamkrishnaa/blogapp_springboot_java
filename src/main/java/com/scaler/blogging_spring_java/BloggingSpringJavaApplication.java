package com.scaler.blogging_spring_java;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BloggingSpringJavaApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    } // user controller
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public static void main(String[] args) {
        SpringApplication.run(BloggingSpringJavaApplication.class, args);
    }

}
