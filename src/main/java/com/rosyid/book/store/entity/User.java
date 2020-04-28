package com.rosyid.book.store.entity;

import com.rosyid.book.store.persistence.EntityPersistence;
import lombok.*;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Data
@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint( columnNames = "username"),
        @UniqueConstraint( columnNames = "email")
        })
@Where(clause = "status = 'ACTIVE'")
public class User extends EntityPersistence {

    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id") )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
