package com.rosyid.book.store.catalog.service;

import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.catalog.persistence.CatalogServicePersistence;

public interface ProductService extends CatalogServicePersistence<ProductResponse, Long>
{

}
