package com.shawn.recruit.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StateEnumConvertor implements AttributeConverter<StateEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StateEnum stateEnum) {
        return stateEnum.getCode();
    }

    @Override
    public StateEnum convertToEntityAttribute(Integer code) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (stateEnum.getCode().equals(code)) {
                return stateEnum;
            }

        }
        throw new IllegalArgumentException("code does not have a corresponding enum");
    }
}
