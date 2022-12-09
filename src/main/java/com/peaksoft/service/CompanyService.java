package com.peaksoft.service;

import com.peaksoft.dto.request.CompanyRequest;
import com.peaksoft.dto.response.CompanyResponse;

import java.util.List;


public interface CompanyService {

    List<CompanyResponse> getAllCompany();

    CompanyResponse getCompanyById(Long id);

    CompanyResponse saveCompany(CompanyRequest companyRequest);

    CompanyResponse updateCompany(Long id, CompanyRequest companyRequest);

    CompanyResponse deleteCompanyById(Long id);
}
