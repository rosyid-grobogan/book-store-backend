package com.rosyid.book.store.catalog.controller;


import com.rosyid.book.store.catalog.payload.request.ProductRequest;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.catalog.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Api
@RestController
@RequestMapping("/api/v1/admin/products")
public class ProductRestController
{
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ProductResponse createProduct(@RequestBody @Valid ProductRequest request,
                                         BindingResult result,
                                         HttpServletResponse response
                                         ) throws IOException
    {
        ProductResponse productResponse = new ProductResponse();
        if (result.hasErrors())
        {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return productResponse;
        }else {
            BeanUtils.copyProperties(request, productResponse);
            return productService.saveOrUpdate(productResponse);
        }
    }

}
