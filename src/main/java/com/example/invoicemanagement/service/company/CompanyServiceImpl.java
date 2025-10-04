package com.example.invoicemanagement.service.company;

import com.example.invoicemanagement.exception.NonExistingCompanyException;
import com.example.invoicemanagement.mapper.CompanyMapper;
import com.example.invoicemanagement.model.entity.Company;
import com.example.invoicemanagement.model.enumeration.CompanyType;
import com.example.invoicemanagement.model.http.request.CompanyCreationRequest;
import com.example.invoicemanagement.model.http.response.CompanyResponse;
import com.example.invoicemanagement.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Company findByType(CompanyType companyType) {
        return this.companyRepository.findByType(companyType)
                .stream()
                .findFirst()
                .orElseThrow(NonExistingCompanyException::new);
    }

    @Override
    public Company findById(Long id) {
        return this.companyRepository.findById(id)
                .orElseThrow(NonExistingCompanyException::new);
    }

    @Override
    public CompanyResponse create(CompanyCreationRequest companyCreationRequest) {
        Company mapped = this.companyMapper.map(companyCreationRequest);
        Company saved = this.companyRepository.save(mapped);

        return this.companyMapper.map(saved);
    }

    @Override
    public void delete(Long id) {
        this.companyRepository.deleteById(id);
    }

    @Override
    public List<CompanyResponse> getAll() {
        return companyRepository.findAll()
                .stream()
                .map(this.companyMapper::map)
                .toList();
    }
}
