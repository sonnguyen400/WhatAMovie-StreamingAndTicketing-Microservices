package com.sonnguyen.common.data.persistence.mapper;

import com.sonnguyen.common.data.persistence.entity.MessageLocaleEntity;
import com.sonnguyen.common.model.domain.MessageLocale;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageLocaleEntityMapper extends EntityMapper<MessageLocale, MessageLocaleEntity> {
}
