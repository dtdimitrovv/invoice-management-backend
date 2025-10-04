package com.example.invoicemanagement.mapper;

import com.example.invoicemanagement.model.entity.InvoiceContent;
import com.example.invoicemanagement.model.http.request.InvoiceContentOperationRequest;
import com.example.invoicemanagement.model.http.response.InvoiceContentResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface InvoiceContentMapper {
    InvoiceContent map(InvoiceContentOperationRequest invoiceContentOperationRequest);

    List<InvoiceContent>map(List<InvoiceContentOperationRequest>invoiceContentOperationRequests);

    @Mapping(target = "totalPriceInBulgarianLev",expression = "java(invoiceContent.getQuantity() * (invoiceContent.getUnitPrice() * (1- invoiceContent.getDiscount() / 100)))")
    InvoiceContentResponse map(InvoiceContent invoiceContent);
}
