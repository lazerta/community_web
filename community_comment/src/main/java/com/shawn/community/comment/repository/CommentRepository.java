package com.shawn.community.comment.repository;


import com.shawn.community.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
    public Page<Comment> findByParentId(String parentId, Pageable pageable);

}
