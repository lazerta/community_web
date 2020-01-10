package com.shawn.community.search.service;

import com.shawn.community.search.entity.Article;
import com.shawn.community.search.repository.ArticleSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleSearchService {
    private final ArticleSearchRepository searchRepository;

    public void save(Article article) {
        searchRepository.save(article);
    }

    public Page<Article> findByTitleLike(String keywords, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return searchRepository.findByTitleOrContentLike(keywords, keywords,
                pageRequest);
    }
}

