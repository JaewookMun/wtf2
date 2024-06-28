package com.wtf2.erp.company.repository;

import com.wtf2.erp.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByGuid(String guid);
    List<Company> findByName(String name);
}