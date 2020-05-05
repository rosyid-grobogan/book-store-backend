package com.rosyid.book.store.transaction.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.transaction.entity.EnumPaymentMethod;
import com.rosyid.book.store.transaction.entity.EnumTransactionStatus;
import com.rosyid.book.store.transaction.persistence.TransactionModelPersistence;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponse extends TransactionModelPersistence
{
    private UserResponse userResponse;

    private String invoiceNumber;
    private String receiptImageUrl;

    private EnumTransactionStatus transactionStatus;

    private EnumPaymentMethod paymentMethod;

    private Date paymentAt;

    private List<DetailModel> details;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DetailModel extends TransactionModelPersistence
    {
        private ProductResponse productResponse;

        private Double price;
    }
}
