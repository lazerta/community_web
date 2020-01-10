package com.shawn.community.base.service;


import com.shawn.community.base.entity.Label;
import com.shawn.community.base.repository.LabelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import util.IdWorker;
import util.StringUtil;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional(rollbackOn = Exception.class)
public class LabelService {
    private LabelRepository repository;
    private IdWorker idWorker;


    private ExampleMatcher getSearchCriteria(Label label) {
        StringUtil.emptyToNull(label);
        return ExampleMatcher.matchingAll().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    public List<Label> findAll() {
        return repository.findAll();
    }

    public Label findById(String id) {
        return repository.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        repository.save(label);
    }

    public void update(Label label) {
        repository.save(label);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        ExampleMatcher criteria = getSearchCriteria(label);

        return repository.findAll(Example.of(label, criteria));

    }


    public Page<Label> findSearchWithPage( Label label, int page, int size) {
        ExampleMatcher criteria = getSearchCriteria(label);
        Pageable pageable = PageRequest.of(page-1, size);
        return repository.findAll(Example.of(label, criteria), pageable);
    }
}
