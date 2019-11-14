package com.shawn.recruit.service;

import com.shawn.recruit.entity.Company;
import com.shawn.recruit.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@AllArgsConstructor
@Service
@Transactional(rollbackOn = Exception.class)
public class CompanyService {
    private CompanyRepository repository;


    public List<Company> getPopularList() {
        return repository.findByIsPopular(true);
    }
}
