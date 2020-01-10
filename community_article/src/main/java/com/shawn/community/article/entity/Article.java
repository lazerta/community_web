package com.shawn.community.article.entity;
import com.shawn.community.article.constance.ReviewStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_article")
public class Article implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "column_id")
    private String columnId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "is_top")
    private Boolean isTop;

    @Column(name = "visits")
    private Integer visits =0;

    @Column(name = "thumb_up")
    private Integer thumbUp =0;

    @Column(name = "comment_count")
    private Integer commentCount = 0;

    @Column(name = "review_status")
    private ReviewStatusEnum reviewState;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private String type;

}
