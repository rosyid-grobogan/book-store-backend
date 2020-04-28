package com.rosyid.book.store.entity;

import com.rosyid.book.store.persistence.EntityPersistence;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter

@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends EntityPersistence {

    private EnumRole name;

    public Role(EnumRole name) {
        this.name = name;
    }
}
