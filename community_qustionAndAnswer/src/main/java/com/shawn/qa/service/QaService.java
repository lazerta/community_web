package com.shawn.qa.service;

import com.shawn.qa.entity.Question;
import com.shawn.qa.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional(rollbackOn = Exception.class)
@AllArgsConstructor
public class QaService {
    private QuestionRepository repository;

    public Page<Question> findLatestProblemsOfLabel(String labelId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findLatestProblemsOfLabel(labelId, pageable);
    }
}
