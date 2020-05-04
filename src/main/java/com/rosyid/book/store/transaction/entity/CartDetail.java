package com.rosyid.book.store.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rosyid.book.store.catalog.entity.Product;
import com.rosyid.book.store.transaction.persistence.TransactionEntityPersistence;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_details")
@Where(clause = "status = 'ACTIVE'")
public class CartDetail extends TransactionEntityPersistence
{
    private static final long serialVersionUID = -5885681065699170246L;

    public enum CartDetailStatus {
        CARTED, TRANSACTED
    }

    @JoinColumn(name = "product_id")
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "cart_id")
    @ManyToOne(targetEntity = Cart.class, fetch = FetchType.LAZY)
    private Cart cart;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private CartDetailStatus cartDetailStatus;

}
