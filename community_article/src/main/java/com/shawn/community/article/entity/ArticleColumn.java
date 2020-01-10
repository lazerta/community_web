package com.shawn.community.article.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_column")
public class ArticleColumn {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "summary")
    private String summary;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "check_time")
    private Date checkTime;

    @Column(name = "state")
    private String state;



}
