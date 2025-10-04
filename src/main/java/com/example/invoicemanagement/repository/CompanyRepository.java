package com.example.invoicemanagement.repository;

import com.example.invoicemanagement.model.entity.Company;
import com.example.invoicemanagement.model.enumeration.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByType(CompanyType companyType);

    List<Company> findAll();
}
