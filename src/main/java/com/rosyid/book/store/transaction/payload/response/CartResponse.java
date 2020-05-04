package com.rosyid.book.store.transaction.payload.response;

import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.transaction.entity.CartDetail;
import com.rosyid.book.store.transaction.persistence.TransactionModelPersistence;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartResponse extends TransactionModelPersistence
{

    private UserResponse userResponse;

    private List<DetailModel> details;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DetailModel extends TransactionModelPersistence
    {

        private ProductResponse bookModel;

        private CartDetail.CartDetailStatus cartDetailStatus;

    }
}

