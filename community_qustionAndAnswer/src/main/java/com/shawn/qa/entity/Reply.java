package com.shawn.qa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_reply")
public class Reply implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "problem_id")
    private String problemId;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "alias")
    private String alias;



}
