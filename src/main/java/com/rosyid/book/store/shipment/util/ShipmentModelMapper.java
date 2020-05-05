package com.rosyid.book.store.shipment.util;

import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.shipment.entity.Shipment;
import com.rosyid.book.store.shipment.payload.response.ShipmentResponse;
import com.rosyid.book.store.transaction.util.TransactionModelMapper;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Workflow
 */
public class ShipmentModelMapper
{
    public static ShipmentResponse constructModel(Shipment shipment)
    {
        UserResponse userModel = new UserResponse();
        BeanUtils.copyProperties(shipment.getUser(), userModel);

        ShipmentResponse model = new ShipmentResponse();
        model.setUserResponse(userModel);
        model.setTransactionResponse(TransactionModelMapper.constructModel(shipment.getTransaction()));

        BeanUtils.copyProperties(shipment, model);
        return model;
    }

    public static List<ShipmentResponse> constructModel(List<Shipment> shipments)
    {
        List<ShipmentResponse> models = new ArrayList<>();
        shipments.forEach(shipment -> {
            ShipmentResponse model = constructModel(shipment);
            models.add(model);
        });
        return models;
    }
}
