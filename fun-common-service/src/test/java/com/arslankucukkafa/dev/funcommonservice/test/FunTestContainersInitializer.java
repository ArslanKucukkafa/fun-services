package com.arslankucukkafa.dev.funcommonservice.test;

import org.springframework.boot.test.util.TestPropertyValues;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;


@Testcontainers
public class FunTestContainersInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Container
    private static final LocalStackContainer LOCAL_STACK = new LocalStackContainer(
            DockerImageName.parse("localstack/localstack:3.0"))
            .withServices(LocalStackContainer.Service.S3, LocalStackContainer.Service.SQS, LocalStackContainer.Service.SNS);

    @Container
    private static final MongoDBContainer MONGO_DB = new MongoDBContainer("mongo:4.0.10");

    @Container
    private static final KafkaContainer KAFKA = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    static {
        LOCAL_STACK.start();
        MONGO_DB.start();
        KAFKA.start();

        try {
            LOCAL_STACK.execInContainer("awslocal", "s3", "mb", "s3://bucket-name");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to initialize S3 bucket", e);
        }
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                // AWS Configuration
                "aws.region=" + LOCAL_STACK.getRegion(),
                "aws.credentials.access-key-id=" + LOCAL_STACK.getAccessKey(),
                "aws.credentials.secret-access-key=" + LOCAL_STACK.getSecretKey(),
                "aws.services.s3.endpoint=" + LOCAL_STACK.getEndpointOverride(LocalStackContainer.Service.S3),
                "aws.services.sqs.endpoint=" + LOCAL_STACK.getEndpointOverride(LocalStackContainer.Service.SQS),
                "aws.services.sns.endpoint=" + LOCAL_STACK.getEndpointOverride(LocalStackContainer.Service.SNS),

                // MongoDB Configuration
                "spring.data.mongodb.uri=" + MONGO_DB.getReplicaSetUrl(),

                // Kafka Configuration
                "spring.kafka.bootstrap-servers=" + KAFKA.getBootstrapServers()
        ).applyTo(applicationContext.getEnvironment());
    }
}
