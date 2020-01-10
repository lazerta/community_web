package com.shawn.community.user.repository;

import com.shawn.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserRepository extends JpaRepository<User, String>, QueryByExampleExecutor<User> {
    User findByMobileAndPassword(String mobile, String password);
    @Modifying
    @Query("update  User  u set u.fanCount = u.fanCount + :amount where  u.id = :id")
    void addFanCount(String id, int amount);

    @Modifying
    @Query("update  User  u set u.followCount = u.fanCount + :amount where  u.id = :id")
    void addFollowCount(String id, int amount);


}
