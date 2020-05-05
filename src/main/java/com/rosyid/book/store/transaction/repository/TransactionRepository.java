package com.rosyid.book.store.transaction.repository;

import com.rosyid.book.store.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * DAO of Transaction Entity
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>
{
    List<Transaction> findByUserId(Long userId);

    // Paging
    Page<Transaction> findByUserFullNameContainsIgnoreCase(String fullName, Pageable pageable);

    Page<Transaction> findByInvoiceNumberContainsIgnoreCase(String invoiceNumber, Pageable pageable);

    Page<Transaction> findAll(Pageable pageable);
}
