package com.arslankucukkafa.dev.gatewayservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedList;
import java.util.Queue;

public class ServiceConfigurations {

    Queue<String> serviceUrls = new LinkedList<>();

    public Queue<String> getServiceUrls() {

        serviceUrls.remove("");

        return serviceUrls;
    }

}
