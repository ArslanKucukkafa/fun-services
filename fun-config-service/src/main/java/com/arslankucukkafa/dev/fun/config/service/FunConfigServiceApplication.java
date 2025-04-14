package com.arslankucukkafa.dev.fun.config.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FunConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunConfigServiceApplication.class, args);
    }

}
