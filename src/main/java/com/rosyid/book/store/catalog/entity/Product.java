package com.rosyid.book.store.catalog.entity;

import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Setter
@Getter
@Entity
//@DynamicUpdate
//@NoArgsConstructor
@Table(name = "products")
@Where(clause = "status='ACTIVE' AND visibility = 'VISIBLE'")
public class Product extends CatalogEntityPersistence
{
    private static final long serialVersionUID = 1L;

    private String name;
    private String slug;
    private Long photoId;
    private Double price;
    private Integer quantity;
    @Column(columnDefinition = "text")
    private String description;

    @JoinColumn(name = "categories_id")
    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    private Category ProductCategoryId;

    @Enumerated(EnumType.STRING)
    private EnumProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    private EnumVisibility visibility;


}
