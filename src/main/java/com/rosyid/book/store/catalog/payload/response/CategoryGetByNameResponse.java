package com.rosyid.book.store.catalog.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.catalog.persistence.CatalogModelPersistence;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryGetByNameResponse extends CatalogModelPersistence
{
    private String name;
}
