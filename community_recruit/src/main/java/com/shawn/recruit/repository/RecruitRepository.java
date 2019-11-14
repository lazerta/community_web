package com.shawn.recruit.repository;

import com.shawn.recruit.common.StateEnum;
import com.shawn.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit,String> {

    List<Recruit> findTop6ByStateOrderByCreateTimeDesc(StateEnum state);
   List<Recruit> findTop6ByStateNotOrderByCreateTimeDesc(StateEnum state);

}
