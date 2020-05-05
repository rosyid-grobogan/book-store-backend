package com.rosyid.book.store.shipment.payload.response;

import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.shipment.entity.EnumCourier;
import com.rosyid.book.store.shipment.entity.EnumShipmentStatus;
import com.rosyid.book.store.shipment.persistence.ShipmentModelPersistence;
import com.rosyid.book.store.transaction.payload.response.TransactionResponse;


public class ShipmentResponse extends ShipmentModelPersistence
{

    private String trackingNumber;
    private String address;

    private UserResponse userResponse;
    private TransactionResponse transactionResponse;

    private EnumShipmentStatus shipmentStatus;
    private EnumCourier courier;
}
