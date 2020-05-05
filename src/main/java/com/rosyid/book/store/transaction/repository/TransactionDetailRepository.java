package com.rosyid.book.store.transaction.repository;

import com.rosyid.book.store.transaction.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long>
{

}
