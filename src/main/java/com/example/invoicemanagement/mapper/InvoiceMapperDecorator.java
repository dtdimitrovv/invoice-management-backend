package com.example.invoicemanagement.mapper;

import com.example.invoicemanagement.model.entity.Invoice;
import com.example.invoicemanagement.model.enumeration.CompanyType;
import com.example.invoicemanagement.model.http.request.InvoiceOperationRequest;
import com.example.invoicemanagement.model.http.response.InvoiceContentResponse;
import com.example.invoicemanagement.model.http.response.InvoiceResponse;
import com.example.invoicemanagement.repository.InvoiceRepository;
import com.example.invoicemanagement.service.company.CompanyService;
import com.example.invoicemanagement.util.NumberVerbalConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class InvoiceMapperDecorator implements InvoiceMapper {
    private static final double DIVIDER_TO_EURO = 1.95583;

    @Autowired
    @Qualifier("delegate")
    private InvoiceMapper delegate;

    private InvoiceRepository invoiceRepository;

    private CompanyService companyService;

    @Override
    public Invoice map(InvoiceOperationRequest invoiceOperationRequest) {
        Invoice mapped = this.delegate.map(invoiceOperationRequest);
        mapped.setSerialNumber(this.invoiceRepository.getNextSerialNumber());
        mapped.setProvider(this.companyService.findByType(CompanyType.SUPPLIER));
        mapped.setClient(this.companyService.findById(invoiceOperationRequest.getClientId()));

        return mapped;
    }

    @Override
    public InvoiceResponse map(Invoice invoice) {
        InvoiceResponse mapped = this.delegate.map(invoice);

        double totalPriceWithoutVatInBulgarianLev = mapped.getContents()
                .stream()
                .map(InvoiceContentResponse::totalPriceInBulgarianLev)
                .reduce(0D, Double::sum);
        mapped.setTotalPriceWithoutVatInBulgarianLev(totalPriceWithoutVatInBulgarianLev);

        double totalPriceWithVatInBulgarianLev = totalPriceWithoutVatInBulgarianLev * 1.2;
        mapped.setTotalPriceWithVatInBulgarianLev(totalPriceWithVatInBulgarianLev);

        double totalPriceWithVatInEuro = Math.round((totalPriceWithVatInBulgarianLev / DIVIDER_TO_EURO) * 100D) / 100D;
        mapped.setTotalPriceWithVatInEuro(totalPriceWithVatInEuro);

        mapped.setVatSumInBulgarianLev(totalPriceWithoutVatInBulgarianLev * 0.2);

        mapped.setVerbalTotalPriceWithVatInBulgarianLev(NumberVerbalConverter.toWords(totalPriceWithVatInBulgarianLev, NumberVerbalConverter.Currency.BGN));
        mapped.setVerbalTotalPriceWithVatInEuro(NumberVerbalConverter.toWords(totalPriceWithVatInEuro, NumberVerbalConverter.Currency.EUR));

        return mapped;
    }

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
