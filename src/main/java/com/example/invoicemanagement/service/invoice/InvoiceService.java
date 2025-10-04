package com.example.invoicemanagement.service.invoice;

import com.example.invoicemanagement.model.http.request.InvoiceOperationRequest;
import com.example.invoicemanagement.model.http.response.InvoiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    InvoiceResponse create(InvoiceOperationRequest invoiceOperationRequest);

    InvoiceResponse update(Long id, InvoiceOperationRequest invoiceOperationRequest);

    Page<InvoiceResponse> get(Pageable pageable);

    InvoiceResponse getById(Long id);
}
