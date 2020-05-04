package com.rosyid.book.store.catalog.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.catalog.entity.Category;
import com.rosyid.book.store.catalog.entity.EnumVisibility;
import com.rosyid.book.store.catalog.persistence.CatalogModelPersistence;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponse extends CatalogModelPersistence
{


    private String name;
    private String slug;
    private Integer parentId;
    private EnumVisibility visibility;


}
