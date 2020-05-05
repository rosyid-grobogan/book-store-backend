package com.rosyid.book.store.transaction.entity;

import javax.persistence.*;

import com.rosyid.book.store.catalog.entity.Product;
import com.rosyid.book.store.transaction.persistence.TransactionEntityPersistence;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction_detail")
@Where(clause = "status = 'ACTIVE'")
public class TransactionDetail extends TransactionEntityPersistence
{
    private static final long serialVersionUID = 1L;

    @Column(columnDefinition = "double precision default '0'")
    private Double price;

    // FK product
    @JoinColumn(name = "product_id")
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    private Product product;

    // FK transaction
    @JoinColumn(name = "transaction_id")
    @ManyToOne(targetEntity = Transaction.class, fetch = FetchType.LAZY)
    private Transaction transaction;

    // FK cart_detail
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_detail_id", referencedColumnName = "id")
    private CartDetail cartDetail;
}
