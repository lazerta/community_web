package com.shawn.recruit.service;

import com.shawn.recruit.common.StateEnum;
import com.shawn.recruit.entity.Recruit;
import com.shawn.recruit.repository.RecruitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class RecruitService {
    private RecruitRepository repository;


    public List<Recruit> recommendedRecruitList() {
        return repository.findTop6ByStateOrderByCreateTimeDesc(StateEnum.RECOMMEND);
    }

    public List<Recruit> latestRecruitList() {
       return repository.findTop6ByStateNotOrderByCreateTimeDesc(StateEnum.CLOSED);
    }
}
