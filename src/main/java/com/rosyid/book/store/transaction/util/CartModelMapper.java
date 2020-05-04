package com.rosyid.book.store.transaction.util;

import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.transaction.entity.Cart;
import com.rosyid.book.store.transaction.payload.request.CartRequest;
import com.rosyid.book.store.transaction.payload.response.CartResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartModelMapper
{
    public static CartResponse constructModel(Cart cart) {
        UserResponse userModel = new UserResponse();
        BeanUtils.copyProperties(cart.getUser(), userModel);

        CartResponse model = new CartResponse();
        model.setUserResponse(userModel);

        List<CartResponse.DetailModel> details = new ArrayList<>();
        cart.getCartDetails().forEach(data -> {
            CartResponse.DetailModel detail = new CartResponse.DetailModel();
            ProductResponse productResponse = new ProductResponse();
            CategoryResponse categoryResponse = new CategoryResponse();

            BeanUtils.copyProperties(data.getProduct().getProductCategoryId(), categoryResponse);
            productResponse.setProductCategoryId(categoryResponse);

            BeanUtils.copyProperties(data.getProduct(), productResponse);
            detail.setBookModel(productResponse);

            BeanUtils.copyProperties(data, detail);
            details.add(detail);
        });
        model.setDetails(details);

        BeanUtils.copyProperties(cart, model);
        return model;
    }

    public static List<CartResponse> constructModel(List<Cart> carts) {
        List<CartResponse> models = new ArrayList<>();
        carts.forEach(cart -> {
            CartResponse model = constructModel(cart);
            models.add(model);
        });
        return models;
    }

    public static CartResponse construcModelForRequest(CartRequest request)
    {
        CartResponse cartModel = new CartResponse();
        CartResponse.DetailModel detail = getCartDetailModel(request.getProductId());
        cartModel.setUserResponse( getUserModel(request.getUserId()));
        cartModel.setDetails(Arrays.asList(detail));
        return cartModel;
    }

    private static UserResponse getUserModel(Long userId)
    {
        UserResponse userModel = new UserResponse();
        userModel.setId(userId);
        return userModel;
    }

    private static CartResponse.DetailModel getCartDetailModel(Long productId)
    {
        CartResponse.DetailModel detail = new CartResponse.DetailModel();
        ProductResponse bookModel = new ProductResponse();
        bookModel.setId(productId);
        detail.setBookModel(bookModel);
        return detail;
    }
}
