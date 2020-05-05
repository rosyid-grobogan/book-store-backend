package com.rosyid.book.store.shipment.service.implement;

import static com.rosyid.book.store.shipment.util.ShipmentModelMapper.constructModel;
import com.rosyid.book.store.account.entity.User;
import com.rosyid.book.store.account.repository.UserRepository;
import com.rosyid.book.store.shipment.entity.EnumShipmentStatus;
import com.rosyid.book.store.shipment.entity.Shipment;
import com.rosyid.book.store.shipment.payload.request.ShipmentRequest;
import com.rosyid.book.store.shipment.payload.request.ShipmentRequestUpdate;
import com.rosyid.book.store.shipment.payload.response.ShipmentResponse;
import com.rosyid.book.store.shipment.persistence.ShipmentEntityPersistence;
import com.rosyid.book.store.shipment.repository.ShipmentRepository;
import com.rosyid.book.store.shipment.service.ShipmentService;
import com.rosyid.book.store.transaction.entity.EnumTransactionStatus;
import com.rosyid.book.store.transaction.entity.Transaction;
import com.rosyid.book.store.transaction.repository.TransactionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public ShipmentResponse saveOrUpdate(ShipmentResponse entity)
    {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ShipmentResponse create(ShipmentRequest request)
    {
        // validate user
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + request.getUserId() + " not found");

        // validate transaction
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElse(null);
        if (transaction == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Transaction with id: " + request.getUserId() + " not found");
        if (!transaction.getTransactionStatus().equals(EnumTransactionStatus.SETTLED))
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Transaction with id: " + request.getUserId() + " is not SETTLED");

        Shipment shipment = new Shipment();
        shipment.setAddress(request.getAddress());
        shipment.setCourier(request.getCourier());
        shipment.setShipmentStatus(EnumShipmentStatus.ORDERED);
        shipment.setStatus(ShipmentEntityPersistence.Status.ACTIVE);
        shipment.setTransaction(transaction);
        shipment.setUser(user);

        shipment = shipmentRepository.save(shipment);
        return constructModel(shipment);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ShipmentResponse update(ShipmentRequestUpdate request)
    {
        Shipment shipment = shipmentRepository.findById(request.getShipmentId()).orElse(null);
        if (shipment == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Shipment with id: " + request.getShipmentId() + " not found");

        if (request.getShipmentStatus().equals(EnumShipmentStatus.ORDERED))
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Shipment with id: " + request.getShipmentId() + " is already ORDERED");

        if (StringUtils.isNotBlank(request.getTrackingNumber()))
            shipment.setTrackingNumber(request.getTrackingNumber());
        shipment.setShipmentStatus(request.getShipmentStatus());

        shipment = shipmentRepository.save(shipment);
        return constructModel(shipment);
    }

    @Override
    public ShipmentResponse delete(ShipmentResponse entity)
    {
        return null;
    }

    @Override
    public ShipmentResponse deleteById(Long id)
    {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ShipmentResponse findById(Long id)
    {
        return constructModel(shipmentRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ShipmentResponse> findAll()
    {
        return constructModel(shipmentRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll() {
        return shipmentRepository.count();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ShipmentResponse> findByUserId(Long userId)
    {
        return constructModel(shipmentRepository.findByUserId(userId));
    }
}
