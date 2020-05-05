package com.rosyid.book.store.shipment.persistence;

import java.util.List;

public interface ShipmentServicePersistence<T, ID>
{

    List<T> findAll();
    T saveOrUpdate(T entity);
    T findById(ID id);
    T delete(T entity);
    T deleteById(ID id);
    Long countAll();

}
