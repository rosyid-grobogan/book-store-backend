package com.rosyid.book.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryModel {

    private Integer id;
    private String name;
    private String code;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}
