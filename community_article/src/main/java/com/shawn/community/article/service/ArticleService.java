package com.shawn.community.article.service;

import com.shawn.community.article.entity.Article;
import com.shawn.community.article.repository.ArticleRepository;
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
public class ArticleService {
    private final ArticleRepository repository;
    private final IdWorker idWorker;

    private ExampleMatcher getSearchCriteria(Article article) {
        StringUtil.emptyToNull(article);
        return ExampleMatcher.matchingAll().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    }


    public int updateReviewState(String id) {
        return repository.updateReviewState(id);
    }

    public void updateThumbsUp(String id) {
        repository.updateThumbsUp(id);
    }


    public List<Article> findAll() {
        return repository.findAll();
    }

    public Page<Article> findSearch(int page, int size, Article article) {
        ExampleMatcher criteria = getSearchCriteria(article);
        return repository.findAll(Example.of(article, criteria), PageRequest.of(page - 1, size));
    }

    public List<Article> findSearch(Article article) {
        ExampleMatcher criteria = getSearchCriteria(article);
        return repository.findAll(Example.of(article, criteria));
    }

    public Article findById(String id) {
        return repository.findById(id).get();
    }

    public void save(Article article) {
        article.setId(idWorker.nextId() + "");
        repository.save(article);
    }

    public void update(Article article) {
        repository.save(article);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
