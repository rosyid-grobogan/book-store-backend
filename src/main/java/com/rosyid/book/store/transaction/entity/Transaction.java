package com.rosyid.book.store.transaction.entity;

import com.rosyid.book.store.account.entity.User;
import com.rosyid.book.store.transaction.persistence.TransactionEntityPersistence;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "transactions")
@Where(clause = "status = 'ACTIVE'")
public class Transaction extends TransactionEntityPersistence
{
    private static final long serialVersionUID = 1L;



    @Column(unique = true)
    private String invoiceNumber;

    @Column(length = 255)
    private String receiptImageUrl;

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private EnumTransactionStatus transactionStatus;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private EnumPaymentMethod paymentMethod;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date paymentAt;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction", fetch = FetchType.LAZY)
    private Set<TransactionDetail> transactionDetails;
}
