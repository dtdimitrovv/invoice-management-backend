package com.example.invoicemanagement.model.http.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InvoiceOperationRequest {
    private Long clientId;
    private String reasonForSkippingVat;
    private List<InvoiceContentOperationRequest> contents;

    public InvoiceOperationRequest() {
        this.contents = new ArrayList<>();
    }
}
