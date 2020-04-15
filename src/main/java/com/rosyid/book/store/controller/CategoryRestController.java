package com.rosyid.book.store.controller;

import com.rosyid.book.store.model.CategoryModel;
import com.rosyid.book.store.model.CategoryRequestCreateModel;
import com.rosyid.book.store.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/rest/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public CategoryModel save(
            @RequestBody
            @Valid CategoryRequestCreateModel request,
            BindingResult result,
            HttpServletResponse response) throws IOException {

        CategoryModel categoryModel = new CategoryModel();

        if ( result.hasErrors() ) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString() );

            return categoryModel;

        }else {
            BeanUtils.copyProperties( request, categoryModel );

            return categoryService.saveOrUpdate( categoryModel );
        }

    }

    @PostMapping("/update")
    public CategoryModel update(
            @RequestBody
            @Valid
            CategoryRequestCreateModel request,
            BindingResult result,
            HttpServletResponse response
            ) throws IOException {

        CategoryModel categoryModel = new CategoryModel();

        if ( result.hasErrors() ) {

            response.sendError( HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString() );

            return categoryModel;
        }else {
            BeanUtils.copyProperties( request, categoryModel);

            return categoryService.saveOrUpdate( categoryModel );
        }

    }

    @DeleteMapping("/deleteById/{id}")
    public CategoryModel deleteById(
            @PathVariable("id") final Integer id
            ) {

        return categoryService.deleteById( id );
    }

    @GetMapping("/findAll")
    public List<CategoryModel> findAll() {

        return categoryService.findAll();
    }

    @GetMapping("/findById/{id}")
    public CategoryModel findById(
            @PathVariable("id") final Integer id
            ) {

        return categoryService.findById( id );
    }
}
