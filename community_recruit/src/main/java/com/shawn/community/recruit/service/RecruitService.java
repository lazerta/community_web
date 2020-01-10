package com.shawn.community.recruit.service;

import com.shawn.community.recruit.constance.StateEnum;
import com.shawn.community.recruit.entity.Recruit;
import com.shawn.community.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;
import util.StringUtil;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class RecruitService {
    private final RecruitRepository repository;
    private final IdWorker idWorker;


    private ExampleMatcher getSearchCriteria(Recruit recruit) {
        StringUtil.emptyToNull(recruit);
        return ExampleMatcher.matchingAll().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    public List<Recruit> recommendedRecruitList() {
        return repository.findTop6ByStateOrderByCreateTimeDesc(StateEnum.RECOMMEND);
    }

    public List<Recruit> latestRecruitList() {
        return repository.findTop6ByStateNotOrderByCreateTimeDesc(StateEnum.CLOSED);
    }

    public List<Recruit> findAll() {
        return repository.findAll();
    }

    public Page<Recruit> findSearch(Recruit recruit, int page, int size) {
        ExampleMatcher matcher = getSearchCriteria(recruit);
        PageRequest pageRequest = PageRequest.of(page - 1, size);

        return repository.findAll(Example.of(recruit, matcher), pageRequest);
    }

    public List<Recruit> findSearch(Recruit recruit) {
        ExampleMatcher matcher = getSearchCriteria(recruit);
        return repository.findAll(Example.of(recruit, matcher));
    }

    public Recruit findById(String id) {
        return repository.findById(id).get();
    }

    public void save(Recruit recruit) {
        recruit.setId(idWorker.nextId() + "");
        repository.save(recruit);
    }

    public void update(Recruit recruit) {
        repository.save(recruit);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

}
