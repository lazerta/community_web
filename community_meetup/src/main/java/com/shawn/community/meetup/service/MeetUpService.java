package com.shawn.community.meetup.service;

import com.shawn.community.meetup.entity.Meetup;
import com.shawn.community.meetup.repository.MeetUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;
import util.StringUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetUpService {
    private final MeetUpRepository repository;
    private final IdWorker idWorker;

    private ExampleMatcher getSearchCriteria(Meetup meetUp) {
        StringUtil.emptyToNull(meetUp);
        return ExampleMatcher.matchingAll().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    public List<Meetup> findAll() {
        return repository.findAll();
    }

    public Page<Meetup> findSearch(int page, int size, Meetup meetUp) {
        ExampleMatcher criteria = getSearchCriteria(meetUp);
        return repository.findAll(Example.of(meetUp, criteria), PageRequest.of(page - 1, size));
    }

    public List<Meetup> findSearch(Meetup meetUp) {
        ExampleMatcher criteria = getSearchCriteria(meetUp);
        return repository.findAll(Example.of(meetUp, criteria));
    }

    public Meetup findById(String id) {
        return repository.findById(id).get();
    }

    public void save(Meetup meetUp) {
        meetUp.setId(idWorker.nextId() + "");
        repository.save(meetUp);
    }

    public void update(Meetup meetUp) {
        repository.save(meetUp);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
