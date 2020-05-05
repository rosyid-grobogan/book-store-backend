package com.rosyid.book.store.transaction.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest
{

    @NotNull
    private String invoiceNumber;

    @NotNull
    private String receiptImageUrl;

    @NotNull
    private String paymentMethod;

    @NotNull
    private Double price;
}
