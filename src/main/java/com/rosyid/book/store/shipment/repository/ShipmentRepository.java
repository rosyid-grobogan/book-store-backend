package com.rosyid.book.store.shipment.repository;

import com.rosyid.book.store.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long>
{
    List<Shipment> findByUserId(Long userId);
}
