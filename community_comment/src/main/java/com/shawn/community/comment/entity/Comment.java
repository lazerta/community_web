package com.shawn.community.comment.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
    @Id
    private String _id;
    private String content;
    private Date publishTime;
    private String userId;
    private String alias;
    private Integer visitCount;
    private Integer thumbUp;
    private Integer share;
    private Integer replyCount;
    private String state;
    private String parentId;
}
