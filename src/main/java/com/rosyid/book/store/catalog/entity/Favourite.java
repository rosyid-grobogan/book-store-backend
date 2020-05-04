package com.rosyid.book.store.catalog.entity;

import com.rosyid.book.store.account.entity.User;
import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "favourites")
@Where(clause = "status = 'ACTIVE'")
public class Favourite extends CatalogEntityPersistence
{
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "favourite", fetch = FetchType.LAZY)
    private Set<FavouriteDetail> favouriteDetails;
}
