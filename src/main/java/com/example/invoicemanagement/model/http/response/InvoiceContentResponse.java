package com.example.invoicemanagement.model.http.response;

public record InvoiceContentResponse(
        String serviceDescription,
        double unitPrice,
        int quantity,
        double discount,
        double totalPriceInBulgarianLev
) {
}
