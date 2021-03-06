package com.rosyid.book.store.shipment.controller;


import com.rosyid.book.store.shipment.payload.request.ShipmentRequest;
import com.rosyid.book.store.shipment.payload.request.ShipmentRequestUpdate;
import com.rosyid.book.store.shipment.payload.response.ShipmentResponse;
import com.rosyid.book.store.shipment.service.ShipmentService;
import io.swagger.annotations.Api;
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
@RequestMapping("/api/v1/user/shipments")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ShipmentRestController
{

    @Autowired
    private ShipmentService shipmentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/create")
    public ShipmentResponse create(@RequestBody @Valid ShipmentRequest request,
                                   BindingResult result,
                                   HttpServletResponse response) throws IOException
    {
        ShipmentResponse shipmentModel = new ShipmentResponse();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return shipmentModel;
        } else
            return shipmentService.create(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/update")
    public ShipmentResponse update(@RequestBody @Valid ShipmentRequestUpdate request,
                                   BindingResult result,
                                   HttpServletResponse response) throws IOException
    {
        ShipmentResponse shipmentModel = new ShipmentResponse();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return shipmentModel;
        } else
            return shipmentService.update(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/findAll")
    public List<ShipmentResponse> findAll()
    {
        return shipmentService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/findById/{id}")
    public ShipmentResponse findById(@PathVariable("id") final Long id)
    {
        return shipmentService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/findByUserId/{userId}")
    public List<ShipmentResponse> findByUserId(@PathVariable("userId") final Long userId)
    {
        return shipmentService.findByUserId(userId);
    }
}
