package com.example.invoicemanagement.model.http.response;

import com.example.invoicemanagement.model.enumeration.CompanyType;

public record CompanyResponse(
        Long id,
        String name,
        String address,
        String identityNumber,
        CompanyType type,
        String vatNumber,
        String responsibleOfficerName,
        String bankIdentifierCode,
        String bankName,
        String iban
) {
}
