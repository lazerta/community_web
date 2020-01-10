package com.shawn.comunity.friend.service;

import com.shawn.comunity.friend.Const.FriendStatus;
import com.shawn.comunity.friend.entity.Friend;
import com.shawn.comunity.friend.entity.NotLike;
import com.shawn.comunity.friend.repository.FriendRepository;
import com.shawn.comunity.friend.repository.NotLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {
    private final FriendRepository friendRepository;
    private final NotLikeRepository notLikeRepository;


    public boolean addFriend(String userId, String friendId) {
        // a is b's friend
        if (friendRepository.existsByUserIdAndFriendId(userId, friendId)) {
            return true;
        }
        Friend friend = Friend.builder()
                .userId(userId)
                .friendId(friendId)
                .isLikeEachOther(FriendStatus.NOT_MUTUAL_FRIEND)
                .build();
        friendRepository.save(friend);
        // b is a's friend
        if (IsMutualFriend(friendId, userId)) {

            friendRepository.updateLikeStatus(userId, friendId, FriendStatus.MUTUAL_FRIEND);
            // update both status
            friendRepository.updateLike(userId, friendId, FriendStatus.MUTUAL_FRIEND);
            friendRepository.updateLike(friendId, userId, FriendStatus.MUTUAL_FRIEND);

        }
        return false;
    }


    private boolean IsMutualFriend(String friendId, String userId) {
        return friendRepository.existsByUserIdAndFriendId(friendId, userId);
    }

    public void addNotLike(String userId, String friendId) {
        NotLike notLike = NotLike.builder().userId(userId)
                .friendId(friendId)
                .build();
        notLikeRepository.save(notLike);
    }

    @Transactional
    public void deleteByFriendID(String userId, String friendid) {
        friendRepository.deleteByUserIdAndFriendId(userId, friendid);
        friendRepository.updateLike(friendid, userId, FriendStatus.NOT_MUTUAL_FRIEND);
        addNotLike(userId, friendid);
    }
}
