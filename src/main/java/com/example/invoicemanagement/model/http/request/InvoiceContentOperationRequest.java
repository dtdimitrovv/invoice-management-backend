package com.example.invoicemanagement.model.http.request;

public record InvoiceContentOperationRequest(
        String serviceDescription,
        double unitPrice,
        int quantity,
        double discount
) {
}
