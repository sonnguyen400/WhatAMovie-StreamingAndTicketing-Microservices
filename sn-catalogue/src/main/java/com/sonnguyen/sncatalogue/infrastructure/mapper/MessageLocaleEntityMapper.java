package com.sonnguyen.sncatalogue.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sncatalogue.domain.MessageLocale;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.MessageLocaleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageLocaleEntityMapper extends EntityMapper<MessageLocale, MessageLocaleEntity> {
}
