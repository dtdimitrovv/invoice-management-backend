package com.example.invoicemanagement.model.entity;

import com.example.invoicemanagement.model.enumeration.CompanyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company extends BaseEntity {
    private String name;

    private String address;

    private String identityNumber;

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    private String vatNumber;

    private String responsibleOfficerName;

    private String bankIdentifierCode;

    private String bankName;

    private String iban;

    private boolean deleted;
}
