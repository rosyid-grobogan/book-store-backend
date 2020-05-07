package com.rosyid.book.store.configglobal;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.MinioException;
import io.minio.errors.NoResponseException;

@Configuration
@ConditionalOnClass(MinioClient.class)
public class MinioConfig
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioConfig.class);

    @Value("${spring.minio.url}")
    private String url;

    @Value("${spring.minio.access-key}")
    private String accessKey;

    @Value("${spring.minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() throws InvalidEndpointException, InvalidPortException, IOException,
            InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException,
            NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException,
            InvalidResponseException, MinioException {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient(url, accessKey, secretKey, false);
        } catch (InvalidEndpointException | InvalidPortException e) {
            LOGGER.error("Error while connecting to Minio", e);
            throw e;
        }
        return minioClient;
    }

}