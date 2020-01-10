package com.shawn.community.qa.repository;

import com.shawn.community.qa.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, String> {
}
