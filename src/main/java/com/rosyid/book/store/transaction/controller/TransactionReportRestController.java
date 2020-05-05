package com.rosyid.book.store.transaction.controller;

import static com.rosyid.book.store.transaction.util.EndpointConstant.PAGE;
import static com.rosyid.book.store.transaction.util.EndpointConstant.PER_PAGE;

import com.rosyid.book.store.transaction.payload.response.TransactionResponse;
import com.rosyid.book.store.transaction.service.TransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/api/v1/admin/report/transactions")
public class TransactionReportRestController
{
    @Autowired
    private TransactionService transactionService;

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/load")
    public Page<TransactionResponse> load(
            @RequestParam(value = PAGE, required = false) Integer page,
            @RequestParam(value = PER_PAGE, required = false) Integer perPage,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "invoiceNumber", required = false) String invoiceNumber)
    {
        return transactionService.findByUserOrInvoice(fullName, invoiceNumber, page, perPage);
    }
}
