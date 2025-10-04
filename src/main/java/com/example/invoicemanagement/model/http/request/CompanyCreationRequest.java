package com.example.invoicemanagement.model.http.request;

import jakarta.validation.constraints.NotNull;

public record CompanyCreationRequest(
        @NotNull
        String name,
        @NotNull
        String address,
        @NotNull
        String identityNumber,
        @NotNull
        String vatNumber,
        @NotNull
        String responsibleOfficerName,
        @NotNull
        String bankIdentifierCode,
        @NotNull
        String bankName,
        @NotNull
        String iban
) {
}
