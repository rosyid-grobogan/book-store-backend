package com.rosyid.book.store.shipment.payload.request;

import com.rosyid.book.store.shipment.entity.EnumShipmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentRequestUpdate
{
    @NotNull
    private Long shipmentId;

    @NotNull
    private EnumShipmentStatus shipmentStatus;

    private String trackingNumber;
}
