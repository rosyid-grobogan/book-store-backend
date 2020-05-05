package com.rosyid.book.store.shipment.entity;

import com.rosyid.book.store.account.entity.User;
import com.rosyid.book.store.shipment.persistence.ShipmentEntityPersistence;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.rosyid.book.store.transaction.entity.Transaction;
import org.hibernate.annotations.Where;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "shipment")
@Where(clause = "status = 'ACTIVE'")
public class Shipment extends ShipmentEntityPersistence
{
    private static final long serialVersionUID = 1L;



    @Column
    private String trackingNumber;

    @NotNull
    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @Column(columnDefinition = "text")
    private String address;

    @NotNull
    @JoinColumn(name = "transaction_id")
    @ManyToOne(targetEntity = Transaction.class, fetch = FetchType.LAZY)
    private Transaction transaction;

    @NotNull
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private EnumShipmentStatus shipmentStatus;

    @NotNull
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private EnumCourier courier;
}
