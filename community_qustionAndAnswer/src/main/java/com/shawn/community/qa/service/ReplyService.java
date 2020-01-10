package com.shawn.community.qa.service;

import com.shawn.community.qa.entity.Reply;
import com.shawn.community.qa.repository.ReplyRepository;
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
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository repository;
    private final IdWorker idWorker;

    private ExampleMatcher getSearchCriteria(Reply question) {
        StringUtil.emptyToNull(question);
        return ExampleMatcher.matchingAll().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }



    public List<Reply> findAll() {
        return repository.findAll();
    }

    public Page<Reply> findSearch(int page, int size, Reply question) {
        ExampleMatcher criteria = getSearchCriteria(question);
        return repository.findAll(Example.of(question, criteria), PageRequest.of(page - 1, size));
    }
    public List<Reply> findSearch(Reply question) {
        ExampleMatcher criteria = getSearchCriteria(question);
        return repository.findAll(Example.of(question, criteria));
    }
    public Reply findById(String id) {
        return repository.findById(id).get();
    }
    public void save(Reply question) {
        question.setId(idWorker.nextId() + "");
        repository.save(question);
    } 
    
    public void update(Reply question) {
        repository.save(question);
    }
    public void deleteById(String id){
        repository.deleteById(id);
    }

   
}
