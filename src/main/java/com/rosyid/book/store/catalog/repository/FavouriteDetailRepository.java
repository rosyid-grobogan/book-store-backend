package com.rosyid.book.store.catalog.repository;

import com.rosyid.book.store.catalog.entity.FavouriteDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FavouriteDetailRepository extends JpaRepository<FavouriteDetail, Long>
{
    @Query("FROM FavouriteDetail detail WHERE detail.favourite.user.id = ?1 AND detail.product.id = ?2")
    List<FavouriteDetail> findByUserIdAndProductId(Long userId, Long productId);
}
