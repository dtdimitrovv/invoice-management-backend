package com.example.invoicemanagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
public class Invoice extends BaseEntity {
    @Column(unique = true)
    private Long serialNumber;

    private LocalDate issueDate;

    private String issueLocation;

    @ManyToOne
    private Company provider;

    @ManyToOne
    private Company client;

    private String reasonForSkippingVat;

    @ElementCollection
    @CollectionTable(
            name = "invoice_contents",
            joinColumns = @JoinColumn(name = "invoice_id")
    )
    private List<InvoiceContent> contents;

    public Invoice() {
        this.contents = new ArrayList<>();
    }
}
