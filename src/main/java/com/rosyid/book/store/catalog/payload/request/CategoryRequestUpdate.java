package com.rosyid.book.store.catalog.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryRequestUpdate extends CategoryRequest
{
    private Long id;

}
