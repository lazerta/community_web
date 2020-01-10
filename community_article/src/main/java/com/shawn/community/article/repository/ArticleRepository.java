package com.shawn.community.article.repository;

import com.shawn.community.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, String> {

    @Modifying
    @Query("update Article set reviewState = 1  where id = :id")
    int updateReviewState(String id);

    @Modifying
    @Query("update Article  set thumbUp = thumbUp+1 where  id =:id")
    void updateThumbsUp(String id);
}
