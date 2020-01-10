package com.shawn.comunity.friend.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_not_like")
@Builder
@IdClass(NotLike.class)
public class NotLike implements Serializable {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "friend_id")
    private String friendId;
}
