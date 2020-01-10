package com.shawn.community.qa.service;

import com.shawn.community.qa.entity.Question;
import com.shawn.community.qa.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import util.IdWorker;
import util.StringUtil;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository repository;
    private final IdWorker idWorker;

    private ExampleMatcher getSearchCriteria(Question question) {
        StringUtil.emptyToNull(question);
        return ExampleMatcher.matchingAll().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }

    public Page<Question> findLatestProblemsOfLabel(String labelId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findLatestProblemsOfLabel(labelId, pageable);
    }

    public Page<Question> findMostPopularQuestionsOfLabel(String labelId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findMostPopularQuestionsOfLabel(labelId, pageable);
    }

    public Page<Question> findNotReplyQuestionOfLabel(String labelId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findNotReplyQuestionOfLabel(labelId, pageable);
    }

    public List<Question> findAll() {
        return repository.findAll();
    }

    public Page<Question> findSearch(int page, int size, Question question) {
        ExampleMatcher criteria = getSearchCriteria(question);
        return repository.findAll(Example.of(question, criteria), PageRequest.of(page - 1, size));
    }
    public List<Question> findSearch(Question question) {
        ExampleMatcher criteria = getSearchCriteria(question);
        return repository.findAll(Example.of(question, criteria));
    }
    public Question findById(String id) {
        return repository.findById(id).get();
    }
    public void save(Question question) {
        question.setId(idWorker.nextId() + "");
        repository.save(question);
    }

    public void update(Question question) {
        repository.save(question);
    }
    public void deleteById(String id){
        repository.deleteById(id);
    }


}
