package com.shawn.recruit.repository;

import com.shawn.recruit.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {

    List<Company> findByIsPopular(Boolean isPopular);
}
