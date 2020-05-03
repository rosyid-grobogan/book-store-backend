package com.rosyid.book.store.catalog.repository;

import com.rosyid.book.store.catalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>
{
    @Query(value = "SELECT * FROM categories c WHERE c.name=?1", nativeQuery = true)
    Category findByName(String name);


}
