package com.rosyid.book.store.catalog.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteRequest
{

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;
}
