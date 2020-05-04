package com.rosyid.book.store.transaction.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

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
public class TransactionModelPersistence
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
