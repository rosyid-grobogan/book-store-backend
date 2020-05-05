package com.rosyid.book.store.shipment.payload.request;

import com.rosyid.book.store.shipment.entity.EnumCourier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentRequest
{
    @NotNull
    private Long userId;

    @NotNull
    private Long transactionId;

    @NotNull
    private EnumCourier courier;
}
