package com.rosyid.book.store.transaction.payload.request;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

}
