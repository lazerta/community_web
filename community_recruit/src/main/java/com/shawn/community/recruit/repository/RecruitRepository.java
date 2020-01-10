package com.shawn.community.recruit.repository;

import com.shawn.community.recruit.constance.StateEnum;
import com.shawn.community.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, String>, QueryByExampleExecutor<Recruit> {

    List<Recruit> findTop6ByStateOrderByCreateTimeDesc(StateEnum state);

    List<Recruit> findTop6ByStateNotOrderByCreateTimeDesc(StateEnum state);

}
