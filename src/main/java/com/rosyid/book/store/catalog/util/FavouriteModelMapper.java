package com.rosyid.book.store.catalog.util;

import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.catalog.entity.Favourite;
import com.rosyid.book.store.catalog.payload.request.FavouriteRequest;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.payload.response.FavouriteResponse;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavouriteModelMapper
{
    public static FavouriteResponse constructModel(Favourite favourite)
    {
        UserResponse userModel = new UserResponse();
        BeanUtils.copyProperties(favourite.getUser(), userModel);

        FavouriteResponse model = new FavouriteResponse();
        model.setUserResponse(userModel);

        List<FavouriteResponse.DetailModel> details = new ArrayList<>();
        favourite.getFavouriteDetails().forEach(data -> {
            FavouriteResponse.DetailModel detail = new FavouriteResponse.DetailModel();
            ProductResponse productResponse = new ProductResponse();
            CategoryResponse bookCategoryModel = new CategoryResponse();

            BeanUtils.copyProperties(data.getProduct().getProductCategoryId(), bookCategoryModel);
            productResponse.setProductCategoryId(bookCategoryModel);

            BeanUtils.copyProperties(data.getProduct(), productResponse);
            detail.setProductResponse(productResponse);

            BeanUtils.copyProperties(data, detail);
            details.add(detail);
        });
        model.setDetails(details);

        BeanUtils.copyProperties(favourite, model);
        return model;
    }

    public static List<FavouriteResponse> constructModel(List<Favourite> favouriteBooks) {
        List<FavouriteResponse> models = new ArrayList<>();
        favouriteBooks.forEach(favouriteBook -> {
            FavouriteResponse model = constructModel(favouriteBook);
            models.add(model);
        });
        return models;
    }

    public static FavouriteResponse construcModelForRequest(FavouriteRequest request) {
        FavouriteResponse favouriteBookModel = new FavouriteResponse();
        FavouriteResponse.DetailModel detail = getFavouriteDetailModel(request.getProductId());
        favouriteBookModel.setUserResponse( getUserModel(request.getUserId()));
        favouriteBookModel.setDetails(Arrays.asList(detail));
        return favouriteBookModel;
    }

    private static UserResponse getUserModel(Long userId) {
        UserResponse userModel = new UserResponse();
        userModel.setId(userId);
        return userModel;
    }

    private static FavouriteResponse.DetailModel getFavouriteDetailModel(Long bookId) {
        FavouriteResponse.DetailModel detail = new FavouriteResponse.DetailModel();
        ProductResponse bookModel = new ProductResponse();
        bookModel.setId(bookId);
        detail.setProductResponse(bookModel);
        return detail;
    }
}
