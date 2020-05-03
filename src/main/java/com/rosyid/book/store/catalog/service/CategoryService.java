package com.rosyid.book.store.catalog.service;

import com.rosyid.book.store.catalog.payload.response.CategoryGetByNameResponse;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.persistence.CatalogServicePersistence;

public interface CategoryService extends CatalogServicePersistence<CategoryResponse, Long>
{
    CategoryGetByNameResponse findByName(String name);
}
