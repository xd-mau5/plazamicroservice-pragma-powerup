package com.pragma.powerup.plazamicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.pragma.powerup.plazamicroservice.adapters.driven.mongo.repository")
public class PlazaMicroserviceApplication{
public static void main(String[] args){
    SpringApplication.run(PlazaMicroserviceApplication.class, args);
}
}
