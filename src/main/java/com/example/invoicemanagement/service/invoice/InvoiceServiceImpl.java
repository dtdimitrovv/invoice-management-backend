package com.example.invoicemanagement.service.invoice;

import com.example.invoicemanagement.exception.InvoiceNotModifiableException;
import com.example.invoicemanagement.exception.NonExistingInvoiceException;
import com.example.invoicemanagement.mapper.InvoiceMapper;
import com.example.invoicemanagement.model.entity.Invoice;
import com.example.invoicemanagement.model.http.request.InvoiceOperationRequest;
import com.example.invoicemanagement.model.http.response.InvoiceResponse;
import com.example.invoicemanagement.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public InvoiceResponse create(InvoiceOperationRequest invoiceOperationRequest) {
        Invoice mapped = this.invoiceMapper.map(invoiceOperationRequest);
        Invoice saved = this.invoiceRepository.save(mapped);

        return this.invoiceMapper.map(saved);
    }

    @Override
    public InvoiceResponse update(Long id, InvoiceOperationRequest invoiceOperationRequest) {
        Invoice invoice = this.invoiceRepository.findById(id).orElseThrow(NonExistingInvoiceException::new);
        this.throwIfInvoiceIsNotInTheSameMonth(invoice);
        this.invoiceMapper.map(invoiceOperationRequest, invoice);

        return this.invoiceMapper.map(this.invoiceRepository.save(invoice));
    }

    @Override
    public Page<InvoiceResponse> get(Pageable pageable) {
        return this.invoiceRepository.findAll(pageable).map(this.invoiceMapper::map);
    }

    @Override
    public InvoiceResponse getById(Long id) {
        return this.invoiceRepository.findById(id).map(this.invoiceMapper::map).orElseThrow(NonExistingInvoiceException::new);
    }

    private void throwIfInvoiceIsNotInTheSameMonth(Invoice invoice) {
        if (!invoice.getIssueDate().getMonth().equals(LocalDate.now().getMonth())) {
            throw new InvoiceNotModifiableException();
        }
    }
}
