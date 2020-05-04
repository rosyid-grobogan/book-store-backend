package com.rosyid.book.store.transaction.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.rosyid.book.store.account.entity.User;
import com.rosyid.book.store.transaction.persistence.TransactionEntityPersistence;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carts")
@Where(clause = "status = 'ACTIVE'")
public class Cart extends TransactionEntityPersistence
{
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", fetch = FetchType.LAZY)
    private Set<CartDetail> cartDetails;

}
