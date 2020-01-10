package com.shawn.community.article.constance;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReviewStatusEnumConvector implements AttributeConverter<ReviewStatusEnum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReviewStatusEnum reviewStatusEnum) {
        return reviewStatusEnum.getCode();
    }


    @Override
    public ReviewStatusEnum convertToEntityAttribute(Integer integer) {

        for (ReviewStatusEnum statusEnum : ReviewStatusEnum.values()) {
            if (statusEnum.getCode().equals(integer)) {
                return statusEnum;
            }
        }
        throw  new IllegalArgumentException("Integer has not mapping enum");
    }
}
