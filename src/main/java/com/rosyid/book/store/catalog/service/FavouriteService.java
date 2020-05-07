package com.rosyid.book.store.catalog.service;

import com.rosyid.book.store.catalog.payload.request.FavouriteRequest;
import com.rosyid.book.store.catalog.payload.response.FavouriteResponse;
import com.rosyid.book.store.catalog.persistence.CatalogServicePersistence;


public interface FavouriteService extends CatalogServicePersistence<FavouriteResponse, Long>
{
//    FavouriteResponse saveOrUpdate(FavouriteRequest request);
    FavouriteResponse create(FavouriteRequest request);
    FavouriteResponse update(FavouriteRequest request);

    FavouriteResponse findByUserId(Long userId);

    FavouriteResponse findByUsername(String username);

    FavouriteResponse deleteByFavouriteDetailId(Long detailId);
}
