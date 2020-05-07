package com.rosyid.book.store.storage.service;

import com.rosyid.book.store.storage.payload.request.ImageRequest;
import com.rosyid.book.store.storage.payload.response.ImageResponse;
import com.rosyid.book.store.storage.persistence.StorageServicePersistence;
import org.springframework.web.multipart.MultipartFile;



public interface ImageService extends StorageServicePersistence
{
    ImageResponse uploadImage(ImageRequest request, MultipartFile file);
}
