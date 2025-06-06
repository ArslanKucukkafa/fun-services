package com.arslankucukkafa.dev.fun.eureka.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FunEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunEurekaServerApplication.class, args);
    }

}
