package com.shawn.community.recruit.service;

import com.shawn.community.recruit.entity.Company;
import com.shawn.community.recruit.repository.CompanyRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;
import util.StringUtil;

import javax.transaction.Transactional;
import java.util.List;


@AllArgsConstructor
@Service
@Transactional(rollbackOn = Exception.class)
public class CompanyService {
    private CompanyRepository repository;
    private IdWorker idWorker;


    private ExampleMatcher getSearchCriteria(Company company) {
        StringUtil.emptyToNull(company);
        return ExampleMatcher.matchingAll().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    public List<Company> getPopularList() {
        return repository.findByIsPopular(true);
    }

    public Company findById(String id) {
        return repository.findById(id).get();
    }


    public Page<Company> findSearch(int page, int size, Company company) {
        ExampleMatcher criteria = getSearchCriteria(company);
        return repository.findAll(Example.of(company, criteria), PageRequest.of(page - 1, size));

    }

    public List<Company> findAll() {
        return repository.findAll();
    }


    public List<Company> findSearch(Company company) {
        ExampleMatcher criteria = getSearchCriteria(company);
        return repository.findAll(Example.of(company, criteria));
    }

    public void save(Company company) {
        company.setId(idWorker.nextId() + "");
        repository.save(company);
    }

    public void update(Company company) {
        repository.save(company);
    }
    public void  deleteById(String id){
        repository.deleteById(id);
    }
}
