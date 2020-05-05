package com.rosyid.book.store.transaction.service;

import com.rosyid.book.store.transaction.payload.request.TransactionRequest;
import com.rosyid.book.store.transaction.payload.request.TransactionRequestUpdate;
import com.rosyid.book.store.transaction.payload.response.TransactionResponse;
import com.rosyid.book.store.transaction.persistence.TransactionServicePersistence;
import org.springframework.data.domain.Page;

import java.util.List;


public interface TransactionService extends TransactionServicePersistence<TransactionResponse, Long>
{
    TransactionResponse save(TransactionRequest request);

    TransactionResponse update(TransactionRequestUpdate request);

    List<TransactionResponse> findByUserId(Long userId);

    // Paging
    Page<TransactionResponse> findByUserOrInvoice(String fullName, String invoiceNumber, Integer page, Integer perPage);
}
