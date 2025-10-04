package com.example.invoicemanagement.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class InvoiceContent {
    private String serviceDescription;

    private double unitPrice;

    private int quantity;

    private double discount;
}