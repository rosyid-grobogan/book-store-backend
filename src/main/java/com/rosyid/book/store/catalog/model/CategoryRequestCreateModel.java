package com.rosyid.book.store.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryRequestCreateModel {

    private Integer id;
}
