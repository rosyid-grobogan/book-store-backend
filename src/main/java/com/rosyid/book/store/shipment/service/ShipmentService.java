package com.rosyid.book.store.shipment.service;

import com.rosyid.book.store.shipment.payload.request.ShipmentRequest;
import com.rosyid.book.store.shipment.payload.request.ShipmentRequestUpdate;
import com.rosyid.book.store.shipment.payload.response.ShipmentResponse;
import com.rosyid.book.store.shipment.persistence.ShipmentServicePersistence;

import java.util.List;

public interface ShipmentService extends ShipmentServicePersistence<ShipmentResponse, Long>
{
    ShipmentResponse create(ShipmentRequest request);

    ShipmentResponse update(ShipmentRequestUpdate request);

    List<ShipmentResponse> findByUserId(Long userId);
}
