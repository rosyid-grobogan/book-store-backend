package com.rosyid.book.store.storage.service;

import java.io.InputStream;

public interface MinioService
{
    String uploadImage(String source, InputStream file, String contentType) throws Exception;
}
