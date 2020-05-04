package com.rosyid.book.store.transaction.persistence;

import java.util.List;

public interface TransactionServicePersistence<T, ID>
{

    List<T> findAll();
    T saveOrUpdate(T entity);
    T findById(ID id);
    T delete(T entity);
    T deleteById(ID id);
    Long countAll();

}
