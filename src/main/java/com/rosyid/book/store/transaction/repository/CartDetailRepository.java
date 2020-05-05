package com.rosyid.book.store.transaction.repository;

import java.util.List;
import java.util.Set;

import com.rosyid.book.store.transaction.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * DAO of CartDetail entity
 */
public interface CartDetailRepository extends JpaRepository<CartDetail, Long>
{

    @Query("FROM CartDetail detail WHERE detail.cart.user.id = ?1 AND detail.product.id = ?2 AND detail.cartDetailStatus = ?3")
    List<CartDetail> findByUserIdAndProductIdAndDetailStatus(Long userId, Long productId, CartDetail.CartDetailStatus status);

    @Query("FROM CartDetail detail WHERE detail.id IN ?1")
    List<CartDetail> findByIds(Set<Long> ids);
}
