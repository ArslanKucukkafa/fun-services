package com.arslankucukkafa.dev.funcommonservice;

import io.awspring.cloud.autoconfigure.sns.SnsClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AwsSnsConfig {

    @Bean
    SnsClientCustomizer customizer() {
        return builder -> {
            builder.overrideConfiguration(builder.overrideConfiguration().copy(c -> {
                c.apiCallTimeout(Duration.ofMillis(1500));
            }));
        };
    }



}
