package com.rosyid.book.store.transaction.service.implement;

import static com.rosyid.book.store.transaction.util.CartModelMapper.constructModel;

import com.rosyid.book.store.account.entity.User;
import com.rosyid.book.store.account.repository.UserRepository;
import com.rosyid.book.store.catalog.entity.Product;
import com.rosyid.book.store.catalog.repository.ProductRepository;
import com.rosyid.book.store.transaction.entity.Cart;
import com.rosyid.book.store.transaction.entity.CartDetail;
import com.rosyid.book.store.transaction.payload.request.CartRequest;
import com.rosyid.book.store.transaction.payload.response.CartResponse;
import com.rosyid.book.store.transaction.persistence.TransactionEntityPersistence;
import com.rosyid.book.store.transaction.repository.CartDetailRepository;
import com.rosyid.book.store.transaction.repository.CartRepository;
import com.rosyid.book.store.transaction.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService
{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CartResponse saveOrUpdate(CartResponse entity) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CartResponse saveOrUpdate(CartRequest request)
    {
        // validate user
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + request.getUserId() + " not found");

        Cart cart = cartRepository.findByUserId(user.getId());
        Set<CartDetail> cartDetails = new HashSet<>();
        // initialize
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);

            // validate book
            Product product = productRepository.findById(request.getProductId()).orElse(null);
            if (product == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book with id: " + request.getProductId() + " not found");

            // detail
            cartDetails.add(saveCartDetail(cart, product));
        } else {
            // update
            Product product = productRepository.findById(request.getProductId()).orElse(null);
            if (product == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book with id: " + request.getProductId() + " not found");

            List<CartDetail> currentCartDetails = cartDetailRepository
                    .findByUserIdAndProductIdAndDetailStatus(user.getId(), product.getId(), CartDetail.CartDetailStatus.CARTED);
            if (currentCartDetails == null || currentCartDetails.size() == 0)
                cartDetails.add(saveCartDetail(cart, product));
        }
        cart.setCartDetails(cartDetails);
        return constructModel(cart);
    }

    private CartDetail saveCartDetail(Cart cart, Product product) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProduct(product);
        cartDetail.setCart(cart);
        cartDetail.setCartDetailStatus(CartDetail.CartDetailStatus.CARTED);
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CartResponse deleteByCartDetailId(Integer detailId) {
        CartDetail cartDetail = cartDetailRepository.findById(detailId).orElse(null);
        if (cartDetail == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Cart Detail with id: " + detailId + " not found");
        cartDetail.setStatus(TransactionEntityPersistence.Status.INACTIVE);
        cartDetail = cartDetailRepository.save(cartDetail);
        return constructModel(cartDetail.getCart());
    }

    @Override
    public CartResponse delete(CartResponse entity) {
        return null;
    }

    @Override
    public CartResponse deleteById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CartResponse findById(Long id) {
        return constructModel(cartRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CartResponse> findAll() {
        return constructModel(cartRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll() {
        return cartRepository.count();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CartResponse findByUserId(Long userId) {
        return constructModel(cartRepository.findByUserId(userId));
    }
}
