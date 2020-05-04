package com.rosyid.book.store.transaction.service;

import com.rosyid.book.store.transaction.payload.request.CartRequest;
import com.rosyid.book.store.transaction.payload.response.CartResponse;
import com.rosyid.book.store.transaction.persistence.TransactionServicePersistence;

public interface CartService extends TransactionServicePersistence<CartResponse, Long>
{
    CartResponse saveOrUpdate(CartRequest request);

    CartResponse findByUserId(Long userId);

    CartResponse deleteByCartDetailId(Long detailId);
}
