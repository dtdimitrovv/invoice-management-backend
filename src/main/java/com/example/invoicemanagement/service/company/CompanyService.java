package com.example.invoicemanagement.service.company;

import com.example.invoicemanagement.model.entity.Company;
import com.example.invoicemanagement.model.enumeration.CompanyType;
import com.example.invoicemanagement.model.http.request.CompanyCreationRequest;
import com.example.invoicemanagement.model.http.response.CompanyResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    Company findByType(CompanyType companyType);

    Company findById(Long id);

    CompanyResponse create(CompanyCreationRequest companyCreationRequest);

    void delete(Long id);

    List<CompanyResponse> getAll();
}
