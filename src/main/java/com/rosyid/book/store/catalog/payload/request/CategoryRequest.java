package com.rosyid.book.store.catalog.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.persistence.CatalogModelPersistence;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/*
* Request Form
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryRequest extends CategoryResponse
{

}
