package com.shawn.community.recruit.constance;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateEnum {
    CLOSED(0), OPEN(1), RECOMMEND(2);
    private Integer code;

    }


