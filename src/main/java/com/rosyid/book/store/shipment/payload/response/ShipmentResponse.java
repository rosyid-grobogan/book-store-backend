package com.rosyid.book.store.shipment.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.shipment.entity.EnumCourier;
import com.rosyid.book.store.shipment.entity.EnumShipmentStatus;
import com.rosyid.book.store.shipment.persistence.ShipmentModelPersistence;
import com.rosyid.book.store.transaction.payload.response.TransactionResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentResponse extends ShipmentModelPersistence
{

    private String trackingNumber;
    private String address;

    private UserResponse userResponse;
    private TransactionResponse transactionResponse;

    private EnumShipmentStatus shipmentStatus;
    private EnumCourier courier;
}
