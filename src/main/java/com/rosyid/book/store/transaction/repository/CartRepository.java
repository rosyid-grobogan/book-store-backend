package com.rosyid.book.store.transaction.repository;

import com.rosyid.book.store.transaction.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CartRepository extends JpaRepository<Cart, Long>
{

    Cart findByUserId(Long userId);
}

