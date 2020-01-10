package com.shawn.community.qa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_question")
public class Question implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.DATE)
    private Date updateTime;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "alias")
    private String alias;

    @Column(name = "visits_count")
    private Long visitsCount;

    @Column(name = "upvote_count")
    private Long upVoteCount;

    @Column(name = "reply_count")
    private Long replyCount;

    @Column(name = "is_answered")
    private Boolean isAnswered = false;

    @Column(name = "replier_name")
    private String replierName;

    @Column(name = "replied_time")
    @Temporal(TemporalType.DATE)
    private Date repliedTime;


}
