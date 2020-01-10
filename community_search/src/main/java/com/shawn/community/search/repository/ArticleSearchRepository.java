package com.shawn.community.search.repository;

import com.shawn.community.search.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchRepository extends ElasticsearchRepository<Article,String> {
    public Page<Article> findByTitleOrContentLike(String title, String
            content, Pageable pageable);
}
