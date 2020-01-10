package com.shawn.comunity.friend.repository;

import com.shawn.comunity.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendRepository extends JpaRepository<Friend, String> {

    @Modifying
    @Query("update Friend  friend set friend.isLikeEachOther = :islike where  friend.userId =:userId and friend.friendId= :friendId")
    public void updateLike(String userId, String friendId, Integer islike);


    boolean existsByUserIdAndFriendId(String userId, String friendId);

    @Modifying
    @Query("update Friend  f set f.isLikeEachOther = :friendStatus where  f.userId =:userId AND f.friendId = :friendId")
    void updateLikeStatus(String userId, String friendId, Integer friendStatus);
    void deleteByUserIdAndFriendId(String userId, String friendId);
}
