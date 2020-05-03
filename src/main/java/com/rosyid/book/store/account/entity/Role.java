package com.rosyid.book.store.account.entity;

import com.rosyid.book.store.account.persistence.AccountEntityPersistence;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter

@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AccountEntityPersistence {

    private EnumRole name;

    public Role(EnumRole name) {
        this.name = name;
    }
}
