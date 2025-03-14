package com.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.utility.DockerImageName
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import jakarta.annotation.PostConstruct
import org.testcontainers.containers.localstack.LocalStackContainer.Service

@TestConfiguration
class TestS3Config {
    
    companion object {
        private val localstack: LocalStackContainer = LocalStackContainer(DockerImageName.parse("localstack/localstack:3.2.0"))
            .withServices(Service.S3)
    }
    
    @PostConstruct
    fun setUp() {
        localstack.start()
    }
    
    @Bean
    @Primary
    fun s3Client(): S3Client {
        return S3Client.builder()
            .endpointOverride(localstack.getEndpointOverride(Service.S3))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(
                        localstack.accessKey,
                        localstack.secretKey
                    )
                )
            )
            .region(Region.of(localstack.region))
            .build()
    }
} 