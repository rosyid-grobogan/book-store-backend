package com.rosyid.book.store.catalog.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.catalog.entity.Category;
import com.rosyid.book.store.catalog.entity.EnumProductStatus;
import com.rosyid.book.store.catalog.entity.EnumVisibility;
import com.rosyid.book.store.catalog.entity.Product;
import com.rosyid.book.store.catalog.persistence.CatalogModelPersistence;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse extends CatalogModelPersistence
{
    private String name;
    private String slug;
//    private Long photoId;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private String description;
    private Long categoryId;
    private CategoryResponse ProductCategoryId;
    private EnumProductStatus productStatus;
    private EnumVisibility visibility;

}
