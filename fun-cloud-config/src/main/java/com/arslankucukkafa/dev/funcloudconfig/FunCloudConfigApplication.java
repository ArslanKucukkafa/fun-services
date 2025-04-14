package com.arslankucukkafa.dev.funcloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FunCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunCloudConfigApplication.class, args);
    }

}
