package com.example.invoicemanagement.model.http.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class InvoiceResponse {
    private Long id;
    private Long serialNumber;
    private LocalDate issueDate;
    private String issueLocation;
    private CompanyResponse provider;
    private CompanyResponse client;
    private String reasonForSkippingVat;
    private List<InvoiceContentResponse> contents;
    private double totalPriceWithoutVatInBulgarianLev;
    private double totalPriceWithVatInBulgarianLev;
    private double totalPriceWithVatInEuro;
    private double vatSumInBulgarianLev;
    private String verbalTotalPriceWithVatInBulgarianLev;
    private String verbalTotalPriceWithVatInEuro;
}
