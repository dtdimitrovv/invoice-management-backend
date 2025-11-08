package com.example.invoicemanagement.mapper;

import com.example.invoicemanagement.model.entity.Invoice;
import com.example.invoicemanagement.model.http.request.InvoiceOperationRequest;
import com.example.invoicemanagement.model.http.response.InvoiceContentResponse;
import com.example.invoicemanagement.model.http.response.InvoiceResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = InvoiceContentMapper.class
)
@DecoratedWith(InvoiceMapperDecorator.class)
public interface InvoiceMapper {
    @Mapping(target = "serialNumber", ignore = true)
    @Mapping(target = "issueLocation", constant = "Пловдив")
    @Mapping(target = "issueDate", expression = "java(java.time.LocalDate.now())")
    Invoice map(InvoiceOperationRequest invoiceOperationRequest);

    @Mapping(target = "serialNumber", ignore = true)
    @Mapping(target = "issueLocation", ignore = true)
    Invoice map(InvoiceOperationRequest invoiceOperationRequest, @MappingTarget Invoice invoice);

    InvoiceResponse map(Invoice invoice);
}
