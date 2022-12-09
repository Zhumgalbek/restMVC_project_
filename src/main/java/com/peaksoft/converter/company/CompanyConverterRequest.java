package com.peaksoft.converter.company;

import com.peaksoft.dto.request.CompanyRequest;
import com.peaksoft.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverterRequest {

    public Company create(CompanyRequest companyRequest) {
        if (companyRequest == null) return null;
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        return company;
    }

    public void update(Company company, CompanyRequest companyRequest) {
        if (companyRequest.getCompanyName() != null) {
            company.setCompanyName(companyRequest.getCompanyName());
        }
        if (companyRequest.getLocatedCountry() != null) {
            company.setLocatedCountry(companyRequest.getLocatedCountry());
        }
    }
}
