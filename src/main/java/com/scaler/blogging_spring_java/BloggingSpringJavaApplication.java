package com.scaler.blogging_spring_java;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingSpringJavaApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    } // user controller
    public static void main(String[] args) {
        SpringApplication.run(BloggingSpringJavaApplication.class, args);
    }

}
