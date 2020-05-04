package com.rosyid.book.store.catalog.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.catalog.persistence.CatalogModelPersistence;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteResponse extends CatalogModelPersistence
{

    private UserResponse userResponse;

    private List<DetailModel> details;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DetailModel extends CatalogModelPersistence {

        private ProductResponse productResponse;

    }
}
