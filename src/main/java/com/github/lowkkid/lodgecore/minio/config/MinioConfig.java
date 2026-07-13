package com.github.lowkkid.lodgecore.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    private final String publicEndpoint;
    private final String internalEndpoint;
    private final String accessKey;
    private final String secretKey;

    public  MinioConfig(@Value("${minio.endpoint}") String publicEndpoint,
                        @Value("${minio.internal-endpoint}") String internalEndpoint,
                        @Value("${minio.access-key}") String accessKey,
                        @Value("${minio.secret-key}") String secretKey) {
        this.publicEndpoint = publicEndpoint;
        this.internalEndpoint = internalEndpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                        .endpoint(internalEndpoint)
                        .credentials(accessKey, secretKey)
                        .build();
    }

    @Bean
    public MinioClient minioPresignClient() {
        return MinioClient.builder()
                        .endpoint(publicEndpoint)
                        .credentials(accessKey, secretKey)
                        .build();
    }
}
