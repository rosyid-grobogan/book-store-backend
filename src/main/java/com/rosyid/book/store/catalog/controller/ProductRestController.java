package com.rosyid.book.store.catalog.controller;


import com.rosyid.book.store.catalog.payload.request.ProductRequest;
import com.rosyid.book.store.catalog.payload.request.ProductRequestUpdate;
import com.rosyid.book.store.catalog.payload.response.ProductResponse;
import com.rosyid.book.store.catalog.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/v1/admin/products")
public class ProductRestController
{
    @Autowired
    private ProductService productService;



    @GetMapping()
    public List<ProductResponse> getAllProducts()
    {
        return productService.findAll();
    }

    /**
     * Create new Product
     * @param request
     * @param result
     * @param response
     * @return
     * @throws IOException
     */
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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


    /**
     * Get a Product
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") final Long id)
    {
        return productService.findById(id);
    }


    /**
     * Update a Product
     * @param requestUpdate
     * @param result
     * @param response
     * @return
     * @throws IOException
     */
    @PutMapping(value = "/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id,
            @RequestBody @Valid ProductRequestUpdate requestUpdate,
                                         BindingResult result,
                                         HttpServletResponse response) throws IOException
    {
        ProductResponse productResponse = new ProductResponse();

        if (result.hasErrors())
        {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return productResponse;
        }else {
            BeanUtils.copyProperties(requestUpdate, productResponse);
            return productService.saveOrUpdate(productResponse);
        }
    }


    /**
     * Delete a Product
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ProductResponse deleteProduct(@PathVariable("id") final Long id)
    {
        return productService.deleteById(id);
    }

}
