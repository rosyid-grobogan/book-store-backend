package com.rosyid.book.store.catalog.service;

import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.catalog.persistence.CatalogServicePersistence;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService extends CatalogServicePersistence<ProductResponse, Long>
{
    ProductResponse uploadImage(Long id, MultipartFile file);
}
