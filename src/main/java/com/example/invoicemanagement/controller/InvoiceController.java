package com.example.invoicemanagement.controller;

import com.example.invoicemanagement.model.http.request.InvoiceOperationRequest;
import com.example.invoicemanagement.model.http.response.InvoiceResponse;
import com.example.invoicemanagement.service.invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping
    public Page<InvoiceResponse> get(Pageable pageable) {
        return this.invoiceService.get(pageable);
    }

    @GetMapping("/{id}")
    public InvoiceResponse getById(@PathVariable Long id) {
        return this.invoiceService.getById(id);
    }

    @PostMapping
    public InvoiceResponse create(@RequestBody InvoiceOperationRequest invoiceOperationRequest) {
        return this.invoiceService.create(invoiceOperationRequest);
    }

    @PutMapping("/{id}")
    public InvoiceResponse update(@PathVariable Long id, @RequestBody InvoiceOperationRequest invoiceOperationRequest) {
        return this.invoiceService.update(id, invoiceOperationRequest);
    }
}
