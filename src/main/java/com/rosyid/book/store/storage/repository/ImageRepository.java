package com.rosyid.book.store.storage.repository;

import com.rosyid.book.store.storage.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>
{

}
