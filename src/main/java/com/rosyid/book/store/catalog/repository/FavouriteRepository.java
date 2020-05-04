package com.rosyid.book.store.catalog.repository;

import com.rosyid.book.store.catalog.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long>
{
//    List<Favourite> findByUserId(Long userId); // Previous modul
    Favourite findByUserId(Long userId);

    Favourite findByUserUsername(String username);
}
