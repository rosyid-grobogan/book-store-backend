package com.rosyid.book.store.catalog.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.catalog.persistence.CatalogModelPersistence;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponse extends CatalogModelPersistence
{


    private String name;
    private String slug;
    private Integer parentId;
    private Boolean categoryStatus;


}
