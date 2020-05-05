package com.rosyid.book.store.transaction.controller;

import com.rosyid.book.store.transaction.payload.request.CartRequest;
import com.rosyid.book.store.transaction.payload.response.CartResponse;
import com.rosyid.book.store.transaction.service.CartService;
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
@RequestMapping("/api/v1/user/carts")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
public class CartRestController
{
    @Autowired
    private CartService cartService;

    @PostMapping("/saveOrUpdate")
    public CartResponse saveOrUpdate(@RequestBody @Valid CartRequest request,
                                     BindingResult result,
                                     HttpServletResponse response) throws IOException
    {
        CartResponse CartModel = new CartResponse();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return CartModel;
        } else
            return cartService.saveOrUpdate(request);
    }

    @DeleteMapping("/deleteByCartDetailId/{detailId}")
    public CartResponse delete(@PathVariable("detailId") final Long detailId) {
        return cartService.deleteByCartDetailId(detailId);
    }

    @GetMapping("/findAll")
    public List<CartResponse> findAll()
    {
        return cartService.findAll();
    }

    @GetMapping("/findById/{id}")
    public CartResponse findById(@PathVariable("id") final Long id) {
        return cartService.findById(id);
    }

    @GetMapping("/findByUserId/{userId}")
    public CartResponse findByUserId(@PathVariable("userId") final Long userId) {
        return cartService.findByUserId(userId);
    }

}
