package com.rosyid.book.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/*
* Request Form
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryRequestModel {

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
