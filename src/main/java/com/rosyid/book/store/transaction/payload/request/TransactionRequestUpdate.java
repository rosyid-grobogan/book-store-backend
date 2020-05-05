package com.rosyid.book.store.transaction.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.transaction.entity.EnumTransactionStatus;
import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequestUpdate
{
    @NotNull
    private Long transactionId;

    @NotNull
    private EnumTransactionStatus transactionStatus;

    private String receiptImageUrl;
}
