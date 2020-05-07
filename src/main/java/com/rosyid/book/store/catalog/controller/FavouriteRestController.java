package com.rosyid.book.store.catalog.controller;


import com.rosyid.book.store.catalog.payload.request.FavouriteRequest;
import com.rosyid.book.store.catalog.payload.response.FavouriteResponse;
import com.rosyid.book.store.catalog.service.FavouriteService;
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
@RestController
@RequestMapping("/api/v1/user/favourites")
public class FavouriteRestController
{
    @Autowired
    private FavouriteService favouriteService;


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping()
    public List<FavouriteResponse> getAll() {
        return favouriteService.findAll();
    }

    /**
     * Save or Update
     * @param request
     * @param result
     * @param response
     * @return
     * @throws IOException
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping()
    public FavouriteResponse createNew(
            @RequestBody @Valid FavouriteRequest request,
            BindingResult result,
            HttpServletResponse response) throws IOException {
        FavouriteResponse favouriteBookModel = new FavouriteResponse();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return favouriteBookModel;
        } else
            BeanUtils.copyProperties(request, favouriteBookModel);
            return favouriteService.create(favouriteBookModel);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/{id}")
    public FavouriteResponse updateData(
            @PathVariable("id") Long id,
            @RequestBody @Valid FavouriteRequest request,
            BindingResult result,
            HttpServletResponse response) throws IOException {
        FavouriteResponse favouriteModel = new FavouriteResponse();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return favouriteModel;
        } else
            BeanUtils.copyProperties(request, favouriteModel);
            return favouriteService.update(favouriteModel);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{detailId}")
    public FavouriteResponse deleteData(@PathVariable("detailId") final Long detailId) {
        return favouriteService.deleteByFavouriteDetailId(detailId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public FavouriteResponse getSingle(@PathVariable("id") final Long id) {
        return favouriteService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/findByUserId/{userId}")
    public FavouriteResponse findByUserId(@PathVariable("userId") final Long userId) {
        return favouriteService.findByUserId(userId);
    }
}