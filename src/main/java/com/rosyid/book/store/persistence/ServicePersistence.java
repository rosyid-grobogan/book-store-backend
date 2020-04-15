package com.rosyid.book.store.persistence;

import java.util.List;

public interface ServicePersistence<T, ID> {

    T saveOrUpdate(T entity);
    T delete(T entity);
    T deleteById(ID id);
    T findById(ID id);
    List<T> findAll();
    Long countAll();
}
