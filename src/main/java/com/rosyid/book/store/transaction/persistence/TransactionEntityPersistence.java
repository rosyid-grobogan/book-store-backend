package com.rosyid.book.store.transaction.persistence;


import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class TransactionEntityPersistence {

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(length = 50)
    @CreatedBy
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(length = 50)
    @LastModifiedBy
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private TransactionEntityPersistence.Status status;

    @PrePersist
    public void prePersist() {
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
        setStatus(TransactionEntityPersistence.Status.ACTIVE);
        setCreatedBy("system");
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedBy("system");
        setUpdatedAt(new Date());
    }
}