package com.example.invoicemanagement.mapper;

import com.example.invoicemanagement.model.entity.Company;
import com.example.invoicemanagement.model.http.request.CompanyCreationRequest;
import com.example.invoicemanagement.model.http.response.CompanyResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CompanyMapper {
    @Mapping(target = "type",expression = "java(com.example.invoicemanagement.model.enumeration.CompanyType.CUSTOMER)")
    Company map(CompanyCreationRequest companyCreationRequest);

    CompanyResponse map(Company company);
}
