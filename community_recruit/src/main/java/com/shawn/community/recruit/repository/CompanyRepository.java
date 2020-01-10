package com.shawn.community.recruit.repository;

import com.shawn.community.recruit.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String>, QueryByExampleExecutor<Company> {

    List<Company> findByIsPopular(Boolean isPopular);
}
