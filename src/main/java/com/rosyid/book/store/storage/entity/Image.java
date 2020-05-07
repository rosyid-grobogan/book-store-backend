package com.rosyid.book.store.storage.entity;

import com.rosyid.book.store.storage.persistence.StorageEntityPersistence;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;
import javax.persistence.Table;


@Setter
@Getter
@Entity
@Table(name = "images")
@Where(clause = "status = 'ACTIVE'")
public class Image extends StorageEntityPersistence
{
    private String name;
}
