package com.shawn.comunity.friend.entity;

import com.shawn.comunity.friend.Const.FriendStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_friend")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Friend.class)
public class Friend implements Serializable {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "friend_id")
    private String friendId;
    //  false mean like the other person
    //true like each other
    // null like no one
    @Column(name = "is_like_each_other")
    private Integer isLikeEachOther = FriendStatus.UNKNOWN;


}
