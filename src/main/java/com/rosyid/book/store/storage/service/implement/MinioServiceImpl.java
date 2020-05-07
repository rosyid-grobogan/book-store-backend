package com.rosyid.book.store.storage.service.implement;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.rosyid.book.store.storage.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.NoResponseException;

@Service
public class MinioServiceImpl implements MinioService
{

    @Autowired
    private MinioClient minioClient;

    @Value("${spring.minio.bucket}")
    private String bucket;

    @Override
    public String uploadImage(String source, InputStream file, String contentType) throws Exception
    {
        try {
            minioClient.putObject(bucket, source, file, (long) file.available(), null, null, contentType);
            return minioClient.presignedGetObject(bucket, source);
        } catch (XmlPullParserException | InvalidBucketNameException | NoSuchAlgorithmException
                | InsufficientDataException | IOException | InvalidKeyException | NoResponseException
                | ErrorResponseException | InternalException | InvalidArgumentException | InvalidResponseException e)
        {
            throw e;
        }
    }

}
