package com.rosyid.book.store.catalog.controller;

import com.rosyid.book.store.catalog.payload.request.CategoryRequest;
import com.rosyid.book.store.catalog.payload.request.CategoryRequestUpdate;
import com.rosyid.book.store.catalog.payload.response.CategoryGetByNameResponse;
import com.rosyid.book.store.catalog.payload.response.CategoryResponse;
import com.rosyid.book.store.catalog.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/admin/categories")
public class CategoryRestController
{
    @Autowired
    private CategoryService categoryService;



    /**
     * Get All Resources
     *
     * @return
     */
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List<CategoryResponse> getAll()
    {
        return categoryService.findAll();
    }


    /**
     * Create a Resource
     *
     * @param request
     * @param result
     * @param response
     * @return
     * @throws IOException
     */
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public CategoryResponse create(
            @Valid @RequestBody CategoryRequest request,
            BindingResult result,
            HttpServletResponse response) throws IOException
    {
        CategoryResponse categoryModel = new CategoryResponse();

        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return categoryModel;
        } else {
            BeanUtils.copyProperties(request, categoryModel);
            return categoryService.saveOrUpdate(categoryModel);
        }

    }


    /**
     * Get a Resource
     *
     * @param id
     * @return
     */
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable("id") final Long id)
    {
        return categoryService.findById(id);
    }

    /**
     * Find By Name
     * @param name
     * @return
     */
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/byName/{name}")
    public CategoryGetByNameResponse getByName(@PathVariable("name") final String name)
    {
        CategoryGetByNameResponse nameResponse = new CategoryGetByNameResponse();

        return categoryService.findByName(name);
    }


    /**
     * Update a Resource
     * @param request
     * @param result
     * @param response
     * @return
     * @throws IOException
     */
//        @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public CategoryResponse update(

           @RequestBody @Valid CategoryRequestUpdate request,
            BindingResult result,
            HttpServletResponse response) throws IOException
    {
        CategoryResponse categoryModel = new CategoryResponse();

        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return categoryModel;
        } else {
            BeanUtils.copyProperties(request, categoryModel);
            return categoryService.saveOrUpdate(categoryModel);
        }

    }


    /**
     * Delete a Resource
     * @param id
     * @return
     */
//        @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public CategoryResponse deleteById(@PathVariable("id") final Long id) {

        return categoryService.deleteById(id);
    }


}