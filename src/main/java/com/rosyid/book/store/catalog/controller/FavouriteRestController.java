package com.rosyid.book.store.catalog.controller;


import com.rosyid.book.store.catalog.payload.request.FavouriteRequest;
import com.rosyid.book.store.catalog.payload.response.FavouriteResponse;
import com.rosyid.book.store.catalog.service.FavouriteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    /**
     * Save or Update
     * @param request
     * @param result
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/saveOrUpdate")
    public FavouriteResponse saveOrUpdate(@RequestBody @Valid FavouriteRequest request, BindingResult result,
                                          HttpServletResponse response) throws IOException {
        FavouriteResponse favouriteBookModel = new FavouriteResponse();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return favouriteBookModel;
        } else
            return favouriteService.saveOrUpdate(request);
    }

    @DeleteMapping("/deleteByFavouriteBookDetailId/{detailId}")
    public FavouriteResponse delete(@PathVariable("detailId") final Long detailId) {
        return favouriteService.deleteByFavouriteDetailId(detailId);
    }

    @GetMapping("/findAll")
    public List<FavouriteResponse> findAll() {
        return favouriteService.findAll();
    }

    @GetMapping("/findById/{id}")
    public FavouriteResponse findById(@PathVariable("id") final Long id) {
        return favouriteService.findById(id);
    }

    @GetMapping("/findByUserId/{userId}")
    public FavouriteResponse findByUserId(@PathVariable("userId") final Long userId) {
        return favouriteService.findByUserId(userId);
    }
}
