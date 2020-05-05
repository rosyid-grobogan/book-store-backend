package com.rosyid.book.store.transaction.util;

import com.rosyid.book.store.account.payload.response.UserResponse;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.transaction.entity.Transaction;
import com.rosyid.book.store.transaction.payload.response.TransactionResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TransactionModelMapper
{
    public static TransactionResponse constructModel(Transaction transaction)
    {
        UserResponse userModel = new UserResponse();
        BeanUtils.copyProperties(transaction.getUser(), userModel);

        TransactionResponse model = new TransactionResponse();
        model.setUserResponse(userModel);

        List<TransactionResponse.DetailModel> details = new ArrayList<>();
        transaction.getTransactionDetails().forEach(data -> {
            TransactionResponse.DetailModel detail = new TransactionResponse.DetailModel();
            ProductResponse productResponse = new ProductResponse();
            CategoryResponse categoryResponse = new CategoryResponse();

            BeanUtils.copyProperties(data.getProduct().getProductCategoryId(), categoryResponse);
            productResponse.setProductCategoryId(categoryResponse);

            BeanUtils.copyProperties(data.getProduct(), productResponse);
            detail.setProductResponse(productResponse);

            BeanUtils.copyProperties(data, detail);
            details.add(detail);
        });
        model.setDetails(details);

        BeanUtils.copyProperties(transaction, model);
        return model;
    }

    public static List<TransactionResponse> constructModel(List<Transaction> transactions)
    {
        List<TransactionResponse> models = new ArrayList<>();
        transactions.forEach(transaction -> {
            TransactionResponse model = constructModel(transaction);
            models.add(model);
        });
        return models;
    }
}
