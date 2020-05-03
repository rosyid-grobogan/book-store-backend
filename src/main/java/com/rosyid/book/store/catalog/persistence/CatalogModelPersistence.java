package com.rosyid.book.store.catalog.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Parent for child
 *
 * id
 * createdBy
 * updatedAt
 * updateBy
 * updatedAt
 *
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatalogModelPersistence
{

//    @NotNull
//
    private Long id;
    private String createdBy;
    private String updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
    private Date updatedAt;

}
