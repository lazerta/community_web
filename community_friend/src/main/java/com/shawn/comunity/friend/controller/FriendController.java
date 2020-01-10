package com.shawn.comunity.friend.controller;

import Const.Const;
import annotation.LoginRequired;
import com.shawn.comunity.friend.Const.FriendStatus;
import com.shawn.comunity.friend.client.UserClient;
import com.shawn.comunity.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import util.Util;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;
    private final UserClient userClient;

    @PutMapping("/like/{friendId}/{friendStatus}")
    @LoginRequired
    public Result addFriend(@PathVariable String friendId, @PathVariable Integer friendStatus, HttpServletRequest request) {
        if (friendStatus == null) {
            Result.error("invalid argument");
        }
        Claims claim = (Claims) request.getAttribute(Const.CLAIM);
        String userId = claim.getId();
        if (FriendStatus.MUTUAL_FRIEND.equals(friendStatus)) {

            return addToFriend(userId, friendId);
        }
        if (FriendStatus.NOT_MUTUAL_FRIEND.equals(friendStatus)) {
            userClient.addFanAndFollowCount(userId, friendId, 1);
            friendService.addNotLike(claim.getId(), friendId);
            return Result.success();
        }

        return Result.error("invalid parameter");
    }

    @DeleteMapping("/{friendid}")
    public Result deleteByFriendId(@PathVariable String friendid, HttpServletRequest request) {
        if (!Util.isUserLogin(request)) {
            return Result.error(StatusCode.UNAUTHORIZED);
        }
        Claims claims = (Claims) request.getAttribute(Const.USER_TOKEN);
        friendService.deleteByFriendID(claims.getId(), friendid);
        return Result.success();
    }


    private Result addToFriend(String userId, String friendId) {
        // can't like twice
        boolean isDuplicate = friendService.addFriend(userId, friendId);
        if (isDuplicate) {
            return Result.error("Can't add twice");
        }
        return Result.success();
    }

    @DeleteMapping("/{friendId}")
    @LoginRequired
    public Result deleteFriend(@PathVariable String friendId, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute(Const.CLAIM);
        String userId = claims.getId();
        friendService.deleteByFriendID(userId, friendId);
        return Result.success();

    }
}
