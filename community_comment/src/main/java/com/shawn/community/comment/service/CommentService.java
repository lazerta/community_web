package com.shawn.community.comment.service;

import com.shawn.community.comment.entity.Comment;
import com.shawn.community.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final IdWorker idWorker;
    private final MongoTemplate template;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(String id) {
        return commentRepository.findById(id).get();
    }

    public void save(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        comment.setPublishTime(new Date());
        comment.setVisitCount(0);
        comment.setShare(0);
        comment.setThumbUp(0);
        comment.setReplyCount(0);
        comment.setState("1");
        commentRepository.save(comment);
//        reply to other's comment
        if (existParent(comment.getParentId())) {
            updateFirst(comment.getParentId(), "replyCount", 1);
        }
    }

    private boolean existParent(String parentId) {
        return (parentId != null && !parentId.isBlank());
    }

    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    public Page<Comment> findByParentId(String parentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return commentRepository.findByParentId(parentId, pageRequest);
    }

    public void updateThumbUp(String id) {
        updateFirst(id, "thumbsUp", 1);
    }

    private void updateFirst(String id, String field, int addAmount) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc(field, addAmount);
        template.updateFirst(query, update, "comment_db");
    }
}
