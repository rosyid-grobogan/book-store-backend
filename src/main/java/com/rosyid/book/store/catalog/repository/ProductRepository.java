package com.rosyid.book.store.catalog.repository;

import com.rosyid.book.store.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface DAO for Entity Product
 */
public interface ProductRepository extends JpaRepository<Product, Long>
{

}
