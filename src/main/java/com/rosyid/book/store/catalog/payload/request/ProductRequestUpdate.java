package com.rosyid.book.store.catalog.payload.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestUpdate extends ProductRequest
{
    private Long id;
}
