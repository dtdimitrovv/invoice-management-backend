package com.example.invoicemanagement.repository;

import com.example.invoicemanagement.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findTopBySerialNumberNotNullOrderBySerialNumberDesc();
}
