package com.rosyid.book.store.catalog.entity;

import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "favourite_details")
@Where(clause = "status = 'ACTIVE'")
public class FavouriteDetail extends CatalogEntityPersistence
{

    @JoinColumn(name = "product_id")
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "favourite_id")
    @ManyToOne(targetEntity = Favourite.class, fetch = FetchType.LAZY)
    private Favourite favourite;
}
