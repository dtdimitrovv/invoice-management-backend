package com.example.invoicemanagement.repository;

import com.example.invoicemanagement.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query(value = "SELECT nextval('invoice_serial_seq')", nativeQuery = true)
    Long getNextSerialNumber();
}
