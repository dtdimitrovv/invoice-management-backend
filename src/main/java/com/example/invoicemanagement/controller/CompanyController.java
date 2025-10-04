package com.example.invoicemanagement.controller;

import com.example.invoicemanagement.model.http.request.CompanyCreationRequest;
import com.example.invoicemanagement.model.http.response.CompanyResponse;
import com.example.invoicemanagement.service.company.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponse> getAll() {
        return this.companyService.getAll();
    }

    @PostMapping
    public CompanyResponse create(@Valid @RequestBody CompanyCreationRequest companyCreationRequest) {
        return this.companyService.create(companyCreationRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.companyService.delete(id);
    }
}
