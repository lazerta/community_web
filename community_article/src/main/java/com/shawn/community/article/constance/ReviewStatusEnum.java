package com.shawn.community.article.constance;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReviewStatusEnum {
    UNDER_REVIEW(0),
    REVIEWED(1);
    private Integer code;


}
