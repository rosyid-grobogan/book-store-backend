package com.rosyid.book.store.transaction.repository;

import com.rosyid.book.store.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    List<Transaction> findByUserId(Long userId);
}
