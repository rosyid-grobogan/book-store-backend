package com.rosyid.book.store.catalog.repository;

import com.rosyid.book.store.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}
