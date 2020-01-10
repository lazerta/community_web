package com.shawn.community.meetup.repository;

import com.shawn.community.meetup.entity.Meetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MeetUpRepository extends JpaRepository<Meetup,String>, QueryByExampleExecutor<Meetup> {

}
