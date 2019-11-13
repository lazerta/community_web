package com.shawn.community.base.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.shawn.community.base.entity.Label;
import com.shawn.community.base.repository.LabelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class LabelService {
    private LabelRepository repository;
    private IdWorker idWorker;
    @PersistenceContext
    private EntityManager manager;

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
        String name = label.getLabelName();
        String state = label.getState();
        if (name == null || name.isBlank()) {
            label.setLabelName(null);
        }
        if (state == null || state.isBlank()) {
            label.setState(null);
        }
        ExampleMatcher criteria = ExampleMatcher.matchingAll().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return repository.findAll(Example.of(label,criteria));

    }
}
