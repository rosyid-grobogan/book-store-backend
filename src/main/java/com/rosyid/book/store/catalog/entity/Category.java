package com.rosyid.book.store.catalog.entity;

import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
@Where(clause = "status = 'ACTIVE' ")
public class Category extends CatalogEntityPersistence
{
    private static final long serialVersionUID = 1L;

    private String name;
    private String slug;

    @Column(nullable = true)
    private Integer parentId;

    @Enumerated(EnumType.STRING)
    private CategoryStatus categoryStatus;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ProductCategoryId", fetch = FetchType.LAZY)
    private Set<Product> products;

    public enum CategoryStatus
    {
        SHOWED, HIDDEN
    }
}
