package com.rosyid.book.store.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelPersistence {

    @NotNull
    private Integer id;
    private String createdBy;
    private String updatedBy;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7"
    )
    private Date createdTime;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+7"
    )
    private Date updatedTime;


}
