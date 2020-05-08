package com.rosyid.book.store.transaction.controller;

import com.rosyid.book.store.account.repository.UserRepository;
import com.rosyid.book.store.transaction.payload.request.CartRequest;
import com.rosyid.book.store.transaction.payload.response.CartResponse;
import com.rosyid.book.store.transaction.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/v1/user/carts")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class CartRestController
{
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{detailId}")
    public CartResponse delete(@PathVariable("detailId") final Long detailId) {
        return cartService.deleteByCartDetailId(detailId);
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @GetMapping()
//    public List<CartResponse> findAll()
//    {
//        return cartService.findAll();
//    }

 //   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping()
    public CartResponse getAll()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  currentUserName = authentication.getName();
        Long userId = userRepository.findIdByUsername(currentUserName);

        return cartService.findByUserId(userId);
    }

//    @GetMapping("/findByUserId/{userId}")
//    public CartResponse findByUserId(@PathVariable("userId") final Long userId) {
//        return cartService.findByUserId(userId);
//    }

}
