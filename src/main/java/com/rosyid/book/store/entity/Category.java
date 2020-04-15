package com.rosyid.book.store.entity;

import com.rosyid.book.store.persistence.EntityPersistence;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @Entity
@Table(name = "categories")
@Where(clause = "status = 'ACTIVE' ")
public class Category extends EntityPersistence {

    @Column(length = 100)
    private String name;

    @Column(length = 50)
    private String code;

}
