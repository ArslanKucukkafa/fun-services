package com.arslankucukkafa.dev.funcommonservice;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.sns.SnsClient;

@SpringBootTest
public class AwsSnsServiceTest {

    @Autowired
    private SnsClient snsClient;


    @Test
    @DisplayName("Connection Test For SNS")
    public void testSnsClient() {
        int statusCode = snsClient.listSubscriptions().sdkHttpResponse().statusCode();
        Assert.assertEquals(200, statusCode);
    }
}
