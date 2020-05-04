package com.rosyid.book.store.catalog.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.catalog.entity.EnumProductStatus;
import com.rosyid.book.store.catalog.entity.EnumVisibility;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.catalog.persistence.CatalogModelPersistence;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequest
{
    private String name;
    private String slug;
    private Long photoId;
    private Double price;
    private Integer quantity;
    private String description;
    private Long categoryId;
    private CategoryResponse ProductCategoryId;
    private EnumProductStatus productStatus;
    private EnumVisibility visibility;
}
