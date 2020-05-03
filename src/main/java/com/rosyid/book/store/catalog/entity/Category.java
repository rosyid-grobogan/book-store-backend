package com.rosyid.book.store.catalog.entity;

import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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
    private Integer parentId;
    private Boolean categoryStatus;

//    public enum EnumCategoryStatus
//    {
//        ACTIVED, INACTIVED
//    }
}
