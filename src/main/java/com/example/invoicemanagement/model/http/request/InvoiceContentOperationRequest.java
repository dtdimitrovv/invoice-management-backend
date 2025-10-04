package com.example.invoicemanagement.model.http.request;

public record InvoiceContentOperationRequest(
        String serviceName,
        double unitPrice,
        int quantity,
        double discount
) {
}
