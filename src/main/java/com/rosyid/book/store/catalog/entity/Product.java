package com.rosyid.book.store.catalog.entity;

import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "products")
@Where(clause = "status='ACTIVE'")
public class Product extends CatalogEntityPersistence
{
    private static final long serialVersionUID = 1L;

    private String name;
    private String slug;
    private Long photoId;
    private Double price;
    private Integer quantity;
    private String description;

    @JoinColumn(name = "categories_id")
    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    private Category ProductCategoryId;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;


    public enum ProductStatus {
        FOR_SELL, OUT_OF_STOCK, HIDDEN

    }
}
