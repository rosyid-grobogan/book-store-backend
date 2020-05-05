package com.rosyid.book.store.transaction.service.implement;

import static com.rosyid.book.store.transaction.util.TransactionModelMapper.constructModel;

import com.rosyid.book.store.account.entity.User;
import com.rosyid.book.store.account.repository.UserRepository;
import com.rosyid.book.store.transaction.entity.*;
import com.rosyid.book.store.transaction.payload.request.TransactionRequest;
import com.rosyid.book.store.transaction.payload.request.TransactionRequestUpdate;
import com.rosyid.book.store.transaction.payload.response.TransactionResponse;
import com.rosyid.book.store.transaction.persistence.TransactionEntityPersistence;
import com.rosyid.book.store.transaction.repository.CartDetailRepository;
import com.rosyid.book.store.transaction.repository.TransactionDetailRepository;
import com.rosyid.book.store.transaction.repository.TransactionRepository;
import com.rosyid.book.store.transaction.service.TransactionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;

/**
 * Transaction Workflow
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Override
    public TransactionResponse saveOrUpdate(TransactionResponse entity) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TransactionResponse save(TransactionRequest request)
    {
        // validate user
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + request.getUserId() + " not found");

        // validate cart detail
        List<CartDetail> cartDetails = cartDetailRepository.findByIds(request.getCartDetailIds());
        Set<TransactionDetail> transactionDetails = new HashSet<>();
        if (cartDetails == null || cartDetails.size() == 0)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Cart details not found");

        Transaction transaction = new Transaction();
        transaction.setInvoiceNumber(UUID.randomUUID().toString());
        transaction.setPaymentMethod(EnumPaymentMethod.BANK_TRANSFER); // if many options, it should be from request
        transaction.setStatus(TransactionEntityPersistence.Status.ACTIVE);
        transaction.setTransactionStatus(EnumTransactionStatus.PENDING);
        transaction.setUser(user);
        transaction = transactionRepository.save(transaction);

        // detail
        for (CartDetail data : cartDetails) {
            TransactionDetail transactionDetail = new TransactionDetail();
            transactionDetail.setProduct(data.getProduct());
            transactionDetail.setCartDetail(data);
            transactionDetail.setPrice(data.getProduct().getPrice());
            transactionDetail.setTransaction(transaction);
            transactionDetail = transactionDetailRepository.save(transactionDetail);
            transactionDetails.add(transactionDetail);

            // update cart detail
            data.setCartDetailStatus(CartDetail.CartDetailStatus.TRANSACTED);
            cartDetailRepository.save(data);
        }
        transaction.setTransactionDetails(transactionDetails);
        return constructModel(transaction);
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TransactionResponse update(TransactionRequestUpdate request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElse(null);
        if (transaction == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Transaction with id: " + request.getTransactionId() + " not found");

        if (!transaction.getTransactionStatus().equals(EnumTransactionStatus.SETTLED)) {
            if (StringUtils.isNotBlank(request.getReceiptImageUrl()))
                transaction.setReceiptImageUrl(request.getReceiptImageUrl());
            if (request.getTransactionStatus().equals(EnumTransactionStatus.SETTLED))
                transaction.setPaymentAt(new Date());
            transaction.setTransactionStatus(request.getTransactionStatus());
            transaction = transactionRepository.save(transaction);
        } else
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Transaction with id: " + transaction.getId() + " already SETTLED");
        return constructModel(transaction);
    }

    @Override
    public TransactionResponse delete(TransactionResponse entity) {
        return null;
    }

    @Override
    public TransactionResponse deleteById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public TransactionResponse findById(Long id) {
        return constructModel(transactionRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TransactionResponse> findAll() {
        return constructModel(transactionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll() {
        return transactionRepository.count();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TransactionResponse> findByUserId(Long userId) {
        return constructModel(transactionRepository.findByUserId(userId));
    }

}
